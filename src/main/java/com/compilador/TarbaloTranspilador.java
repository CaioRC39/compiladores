package com.compilador;

import java.util.List;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.TerminalNode;
import com.compilador.tarbalo.TarbaloBaseVisitor;
import com.compilador.tarbalo.TarbaloParser;

public class TarbaloTranspilador extends TarbaloBaseVisitor<String> {

    private TabelaSimbolos tabela;
    private StringBuilder funcoesGeradas = new StringBuilder();
    private StringBuilder cnjtsGerados = new StringBuilder();
    private StringBuilder regsGerados = new StringBuilder();
    private StringBuilder globaisGeradas = new StringBuilder();
    private StringBuilder blocosEstaticos = new StringBuilder(); // static { } para inicialização 2D+
    private AnalisadorSemantico analisador; // setado no construtor
    private int sliceTmpCounter = 0; // contador para nomes únicos de variáveis temporárias em slices
    private int escopoProfundidade = 0; // 0 = escopo global, >0 = dentro de blocos/estruturas

    // Rastrear helpers de slice necessários
    private java.util.Set<String> sliceHelpersNecessarios = new java.util.HashSet<>();

    public TarbaloTranspilador(TabelaSimbolos tabela, AnalisadorSemantico analisador) {
        this.tabela = tabela;
        this.analisador = analisador;
    }

    public String transpilar(List<TarbaloParser.BlocoContext> blocos) {
        funcoesGeradas = new StringBuilder();
        cnjtsGerados = new StringBuilder();
        regsGerados = new StringBuilder();
        globaisGeradas = new StringBuilder();
        blocosEstaticos = new StringBuilder();
        sliceHelpersNecessarios = new java.util.HashSet<>();
        escopoProfundidade = 0;
        StringBuilder principal = new StringBuilder();

        for (TarbaloParser.BlocoContext bloco : blocos) {
            String codigo = visit(bloco);
            if (codigo != null && !codigo.isBlank()) {
                principal.append(indentar(codigo, "        "));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("import java.util.Scanner;\n");
        sb.append("import java.util.Locale;\n");
        sb.append("import java.util.ArrayList;\n");
        sb.append("import java.util.Collections;\n");
        sb.append("import java.util.Arrays;\n\n");
        sb.append("public class ProgramaSaida {\n");
        sb.append("    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);\n\n");
        sb.append(globaisGeradas.toString());
        if (!blocosEstaticos.isEmpty()) {
            sb.append("\n    static {\n");
            sb.append(blocosEstaticos.toString());
            sb.append("    }\n");
        }
        sb.append(funcoesGeradas.toString());
        sb.append("    public static void main(String[] args) {\n");
        sb.append(principal.toString());
        sb.append("    }\n");
        sb.append(cnjtsGerados.toString());
        sb.append(regsGerados.toString());
        // Gerar helpers de slice necessários
        for (String helper : sliceHelpersNecessarios) {
            sb.append(generarSliceHelper(helper));
        }
        sb.append("}\n");
        return sb.toString();
    }


    private String mapearTipo(String tipoTarbalo) {
        // Extrair o tipo base e os colchetes
        String base = tipoTarbalo.replaceAll("\\[\\]", "");
        int dimensoes = (tipoTarbalo.length() - base.length()) / 2;

        String javaBase = switch (base) {
            case "int"    -> "int";
            case "qbd"    -> "double";
            case "txt"    -> "String";
            case "ltr"    -> "char";
            case "lgc"    -> "boolean";
            case "vazio"  -> "void";
            case "Erro"   -> "Exception";
            default -> {
                // Verificar se é um cnjt — mapear para o tipo base do cnjt
                Simbolo s = tabela.buscarComMemoria(base);
                if (s != null && s.getCategoria() == Simbolo.Categoria.CNJT) {
                    yield mapearTipo(s.getTipo()); // recursivo: mapeia o tipo base
                }
                // Verificar se é um regs — retornar nome do struct (já é o Java class name)
                if (s != null && s.getCategoria() == Simbolo.Categoria.REGS) {
                    yield base;
                }
                yield "Object";
            }
        };

        // Sem dimensões → tipo primitivo. Com dimensões → ArrayList<>.
        if (dimensoes == 0) return javaBase;
        return tipoArrayList(boxarTipo(javaBase), dimensoes);
    }

    // Box type: "int" → "Integer", "double" → "Double", etc.
    private String boxarTipo(String javaBase) {
        return switch (javaBase) {
            case "int"     -> "Integer";
            case "double"  -> "Double";
            case "char"    -> "Character";
            case "boolean" -> "Boolean";
            case "void"    -> "Object";
            default        -> javaBase; // String, Exception, Object
        };
    }

    // Wrap in N levels of ArrayList: ("Integer", 1) → "ArrayList<Integer>"
    private String tipoArrayList(String boxedType, int dimensoes) {
        String result = boxedType;
        for (int i = 0; i < dimensoes; i++) {
            result = "ArrayList<" + result + ">";
        }
        return result;
    }

    // Default value for a base Tarbalo type
    private String valorPadrao(String tipoBase) {
        return switch (tipoBase) {
            case "int"     -> "0";
            case "qbd"     -> "0.0";
            case "ltr"     -> "'\\0'";
            case "lgc"     -> "false";
            default        -> "null"; // txt, Object
        };
    }

    // Build allocation expression for sized ArrayList.
    // For 2D+, generates explicit loop to avoid shared references.
    // Returns the allocation code (may be multi-line for 2D+).
    private String alocacaoArrayList(String tipoBase, List<TarbaloParser.DimensaoContext> dimensoes,
                                      String nomeVar, String indent) {
        if (dimensoes.isEmpty()) return "new ArrayList<>()";

        // Check if first dimension has a size
        boolean primeiraComTamanho = !dimensoes.get(0).expressao().isEmpty();

        if (!primeiraComTamanho) {
            // All dimensions dynamic → just new ArrayList<>()
            return "new ArrayList<>()";
        }

        int ndims = dimensoes.size();

        if (ndims == 1) {
            // 1D: new ArrayList<>(Collections.nCopies(size, default))
            String tam = visit(dimensoes.get(0).expressao(0));
            return "new ArrayList<>(Collections.nCopies(" + tam + ", " + valorPadrao(tipoBase) + "))";
        }

        // 2D+: generate explicit loop to avoid shared references
        // ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        // for (int __i = 0; __i < rows; __i++) {
        //     mat.add(new ArrayList<>(Collections.nCopies(cols, 0)));
        // }
        StringBuilder sb = new StringBuilder();
        sb.append("new ArrayList<>()");
        String loopVar = "__i" + (sliceTmpCounter++); // unique name
        String tam0 = visit(dimensoes.get(0).expressao(0));

        sb.append(";\n");
        sb.append(indent).append("for (int ").append(loopVar).append(" = 0; ")
           .append(loopVar).append(" < ").append(tam0).append("; ").append(loopVar).append("++) {\n");

        if (ndims == 2) {
            // 2D: Direct — mat.add(new ArrayList<>(Collections.nCopies(cols, 0)))
            sb.append(indent).append("    ").append(nomeVar).append(".add(");
            String tam1 = visit(dimensoes.get(1).expressao(0));
            sb.append("new ArrayList<>(Collections.nCopies(").append(tam1).append(", ").append(valorPadrao(tipoBase)).append("))");
            sb.append(");\n");
        } else {
            // 3D+: innerAlloc é multi-linha — usar variável temporária
            String tmpVar = "__tmp" + (sliceTmpCounter++);
            String innerTipo = tipoBase;
            for (int d = 1; d < ndims; d++) innerTipo += "[]";
            String innerTipoJava = mapearTipo(innerTipo);
            List<TarbaloParser.DimensaoContext> innerDims = dimensoes.subList(1, ndims);
            // Passar tmpVar como nome para a recursão interna
            String innerAlloc = alocacaoArrayList(tipoBase, innerDims, tmpVar, indent + "    ");
            sb.append(indent).append("    ").append(innerTipoJava).append(" ").append(tmpVar).append(" = ").append(innerAlloc).append("\n");
            sb.append(indent).append("    ").append(nomeVar).append(".add(").append(tmpVar).append(");\n");
        }
        sb.append(indent).append("}");

        return sb.toString();
    }

    private String indentar(String codigo, String espacos) {
        if (codigo == null || codigo.isBlank()) return "";
        StringBuilder sb = new StringBuilder();
        for (String linha : codigo.split("\n", -1)) {
            sb.append(linha.isBlank() ? "\n" : espacos + linha + "\n");
        }
        return sb.toString();
    }

    // Verifica se o acesso ao vetor contém pelo menos uma dimensão com PONTOPONTO
    private boolean possuiSlice(TarbaloParser.VariavelContext var) {
        for (TarbaloParser.DimensaoContext dim : var.dimensao()) {
            if (dim.PONTOPONTO() != null) return true;
        }
        return false;
    }

    // Retorna o método do scanner de acordo com o tipo Tarbalo
    private String getScannerMethod(String tipoTarbalo) {
        if (tipoTarbalo == null) return "scanner.nextLine()";
        return switch (tipoTarbalo) {
            case "int"    -> "scanner.nextInt()";
            case "qbd"    -> "scanner.nextDouble()";
            case "lgc"    -> "scanner.nextBoolean()";
            case "ltr"    -> "scanner.next().charAt(0)";
            default       -> "scanner.nextLine()";
        };
    }

    // ======================================================================
    // (visitPrograma removido — código morto, transpilar() é usado em vez disso)
    // ======================================================================

    // ======================================================================
    // 2. visitBloco
    // ======================================================================
    @Override
    public String visitBloco(TarbaloParser.BlocoContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (TarbaloParser.ComandoContext cmdCtx : ctx.comando()) {
            escopoProfundidade++;
            String linha = visit(cmdCtx);
            escopoProfundidade--;
            if (linha != null && !linha.isBlank()) {
                sb.append(linha);
                if (!linha.endsWith("\n")) sb.append("\n");
            }
        }
        return sb.toString();
    }

    // ======================================================================
    // 3. visitDeclaracaoVariavel
    // ======================================================================
    @Override
    public String visitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx) {
        String tipoTarbalo = ctx.tipoVariavel().getText();
        String tipoJava    = mapearTipo(tipoTarbalo);
        String nomeVar     = ctx.ID().getText();

        String corpo;
        if (ctx.ATRIBUICAO() != null) {
            String valor = visit(ctx.expressao());
            corpo = tipoJava + " " + nomeVar + " = " + valor + ";";
        } else {
            corpo = tipoJava + " " + nomeVar + ";";
        }

        // escopoProfundidade == 1 → primeiro nível do bloco top-level → campo estático da classe
        if (escopoProfundidade <= 1) {
            globaisGeradas.append("    static ").append(corpo).append("\n");
            return "";
        }
        // escopoProfundidade > 1 → dentro de estrutura de controle → variável local
        return corpo + "\n";
    }

    // ======================================================================
    // 4. visitVariavel  (substitui visitSelecaoVariavel + visitAcessoVetor)
    // ======================================================================
    @Override
    public String visitVariavel(TarbaloParser.VariavelContext ctx) {
        String nome = ctx.ID().getText();
        if (ctx.dimensao().isEmpty()) return nome;

        // Verificar se tem slice
        boolean temSlice = false;
        for (TarbaloParser.DimensaoContext d : ctx.dimensao()) {
            if (d.PONTOPONTO() != null) { temSlice = true; break; }
        }

        if (!temSlice) {
            // Acesso indexado normal: v[0] → v.get(0), mat[1][2] → mat.get(1).get(2)
            StringBuilder sb = new StringBuilder(nome);
            for (TarbaloParser.DimensaoContext d : ctx.dimensao()) {
                sb.append(".get(").append(visit(d.expressao(0))).append(")");
            }
            return sb.toString();
        }

        // Slice: gera chamada helper sliceNd_tipo(...)
        Simbolo sim = tabela.buscarComMemoria(nome);
        String tipoBase = (sim != null && sim.getCategoria() == Simbolo.Categoria.VETOR)
            ? sim.getTipoBase() : "int";
        String sufixo = getSufixoTipo(tipoBase);
        int ndims = ctx.dimensao().size();
        StringBuilder args = new StringBuilder(nome);
        for (int i = 0; i < ndims; i++) {
            TarbaloParser.DimensaoContext d = ctx.dimensao(i);
            if (d.PONTOPONTO() != null) {
                String inicio = visit(d.expressao(0));
                String fim;
                if (d.expressao(1) != null) {
                    fim = "(" + visit(d.expressao(1)) + ") + 1";
                } else {
                    // Slice aberto [expr..] → usa tamanho da dimensão correta
                    StringBuilder dimAccess = new StringBuilder(nome);
                    for (int j = 0; j < i; j++) {
                        dimAccess.append(".get(").append(visit(ctx.dimensao(j).expressao(0))).append(")");
                    }
                    fim = dimAccess.toString() + ".size()";
                }
                args.append(", ").append(inicio).append(", ").append(fim);
            } else {
                String idx = visit(d.expressao(0));
                args.append(", ").append(idx).append(", (").append(idx).append(") + 1");
            }
        }
        String helperName = "slice" + ndims + "d" + sufixo;
        sliceHelpersNecessarios.add(helperName);
        return helperName + "(" + args + ")";
    }

    /** Extrai o nome-base (sem índices) de uma variavel. */
    private String nomeBaseDeVariavel(TarbaloParser.VariavelContext ctx) {
        if (ctx == null) return null;
        return ctx.ID().getText();
    }

    // ======================================================================
    // 5. visitAtribuicao  (CORRIGIDO)
    //    - usa visit(ctx.variavel()) em vez de ctx.getChild(1)  
    //    - usa visitOperadorAtribuicaoComposta() em vez de getText() frágil
    //    - verifica declaração da variável
    // ======================================================================
    @Override
    public String visitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        int linha = ctx.start.getLine();

        // Atribuição a campo de struct: p::nome : "Maria".
        if (ctx.acessoElem() != null) {
            String alvo = visit(ctx.acessoElem());
            String valor = visit(ctx.valorAtribuicao());
            if (ctx.operadorAtribuicaoComposta() != null) {
                String opCompleto = ctx.operadorAtribuicaoComposta().getText();
                String op = opCompleto.charAt(1) + ""; // :+ → +, :- → -, etc.
                return alvo + " = " + alvo + " " + op + " " + valor + ";";
            }
            return alvo + " = " + valor + ";";
        }

        TarbaloParser.VariavelContext sel = ctx.variavel();
        String nomeVar = nomeBaseDeVariavel(sel);

        // (verificação de declaração já feita pelo AnalisadorSemântico)

        // Slice do lado esquerdo
        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            String nomeVetor = sel.ID().getText();
            int ndims = sel.dimensao().size();
            String[] idxVars = new String[ndims];
            int sliceCount = 0;
            String indent = "";

            // Coletar informações de cada dimensão
            StringBuilder sbPre = new StringBuilder();  // declarações antes dos loops
            StringBuilder sb = new StringBuilder();
            String primeiroIdxSlice = null;
            String sliceInicio = null;
            for (int i = 0; i < ndims; i++) {
                TarbaloParser.DimensaoContext dim = sel.dimensao(i);
                if (dim.PONTOPONTO() != null) {
                    String varName = "__tarbalo_i" + (sliceCount > 0 ? String.valueOf(sliceCount) : "");
                    idxVars[i] = varName;
                    if (primeiroIdxSlice == null) {
                        primeiroIdxSlice = varName;
                        sliceInicio = visit(dim.expressao(0));
                    }
                    String inicio = visit(dim.expressao(0));
                    String fim;
                    if (dim.expressao(1) != null) {
                        fim = "(" + visit(dim.expressao(1)) + ") + 1";
                    } else {
                        // Slice aberto: usar tamanho da dimensão
                        StringBuilder dimAccess = new StringBuilder(nomeVetor);
                        for (int j = 0; j < i; j++) dimAccess.append(".get(0)");
                        fim = dimAccess.toString() + ".size()";
                    }
                    sb.append(indent).append("for (int ").append(varName).append(" = ").append(inicio)
                        .append("; ").append(varName).append(" < ").append(fim)
                        .append("; ").append(varName).append("++) {\n");
                    indent += "    ";
                    sliceCount++;
                } else {
                    idxVars[i] = visit(dim.expressao(0));
                }
            }

            // Gerar o acesso ao elemento de destino: v.get(idx0).set(idx1, val)
            // Build get chain for all dimensions except the last
            StringBuilder getPrefix = new StringBuilder(nomeVetor);
            for (int i = 0; i < ndims - 1; i++) {
                getPrefix.append(".get(").append(idxVars[i]).append(")");
            }
            String lastIdx = idxVars[ndims - 1];

            // Verificar se é inicialização literal
            String tempVar = null;
            String tempIdx = null;
            if (ctx.valorAtribuicao().inicializacaoVetor() != null) {
                String valores = visit(ctx.valorAtribuicao().inicializacaoVetor());
                Simbolo sim = tabela.buscarComMemoria(nomeVetor);
                String tipoBase = sim != null ? sim.getTipoBase() : "int";
                String boxedTipo = boxarTipo(mapearTipo(tipoBase));
                tempVar = "__tarbalo_tmp_" + (sliceTmpCounter++);
                // Declarar temp ANTES dos loops (no nível original de indentação)
                sbPre.append("    ArrayList<" + boxedTipo + "> " + tempVar + " = new ArrayList<>(" + valores + ");\n");
                // Usar offset 0-based para o temp array (primeiroIdxSlice - inicio)
                tempIdx = primeiroIdxSlice;
                if (sliceInicio != null) {
                    tempIdx = "(" + primeiroIdxSlice + " - " + sliceInicio + ")";
                }
                sb.append(indent).append(getPrefix.toString()).append(".set(").append(lastIdx).append(", ").append(tempVar).append(".get(").append(tempIdx).append("));\n");
            } else {
                String exprJava = visit(ctx.valorAtribuicao().expressao());
                if (ctx.operadorAtribuicaoComposta() != null) {
                    // Operador composto em slice: v[0..2] :+ 5 → v.set(idx, v.get(idx) + 5)
                    String opJava = visit(ctx.operadorAtribuicaoComposta());
                    String opSimples = opJava.charAt(0) + ""; // += → +, -= → -, etc.
                    sb.append(indent).append(getPrefix.toString()).append(".set(").append(lastIdx).append(", ")
                      .append(getPrefix.toString()).append(".get(").append(lastIdx).append(") ")
                      .append(opSimples).append(" ").append(exprJava).append(");\n");
                } else {
                    sb.append(indent).append(getPrefix.toString()).append(".set(").append(lastIdx).append(", ").append(exprJava).append(");\n");
                }
            }

            // Fechar chaves dos loops
            for (int i = sliceCount - 1; i >= 0; i--) {
                indent = indent.substring(4);
                sb.append(indent).append("}");
                if (i > 0) sb.append("\n");
            }

            return sbPre.toString() + sb.toString();
        }

        // Atribuição normal
        String valor = visit(ctx.valorAtribuicao());

        // Se o destino é acesso indexado em ArrayList → usar .set()
        if (!sel.dimensao().isEmpty() && !possuiSlice(sel)) {
            String nomeVetor = sel.ID().getText();
            int ndims = sel.dimensao().size();

            // Get the element type for casting (int → (double) for qbd arrays, etc.)
            Simbolo simVetor = tabela.buscarComMemoria(nomeVetor);
            String tipoBaseElem = simVetor != null ? simVetor.getTipoBase() : "int";
            String javaBaseElem = mapearTipo(tipoBaseElem);
            String cast = "";
            if (javaBaseElem.equals("double")) cast = "(double) ";
            else if (javaBaseElem.equals("int")) cast = "(int) ";

            if (ctx.operadorAtribuicaoComposta() != null) {
                // Operador composto: v[i] :+ val → v.set(i, v.get(i) + val)
                String opJava = visit(ctx.operadorAtribuicaoComposta());
                String opSimples = opJava.charAt(0) + ""; // += → +, -= → -, etc.
                // Build get chain: v.get(i).get(j)...
                StringBuilder getChain = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims; d++) {
                    getChain.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                // Build set chain: v.get(i).set(j, v.get(i).get(j) op val)
                StringBuilder setPrefix = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims - 1; d++) {
                    setPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));
                return setPrefix.toString() + ".set(" + lastIdx + ", " + getChain.toString() + " " + opSimples + " " + cast + valor + ");";
            } else {
                // Atribuição simples: v[i] = val → v.set(i, val)
                StringBuilder setPrefix = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims - 1; d++) {
                    setPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));
                return setPrefix.toString() + ".set(" + lastIdx + ", " + cast + valor + ");";
            }
        }

        // Variável simples (não indexada)
        String destino = visit(sel);
        String opJava = "=";
        if (ctx.operadorAtribuicaoComposta() != null) {
            opJava = visit(ctx.operadorAtribuicaoComposta());
        }
        return destino + " " + opJava + " " + valor + ";";
    }

    @Override
    public String visitValorAtribuicao(TarbaloParser.ValorAtribuicaoContext ctx) {
        if (ctx.expressao() != null) return visit(ctx.expressao());
        if (ctx.inicializacaoVetor() != null) {
            // inicializacaoVetor gera Arrays.asList(...) — funciona em contexto de atribuição
            return visit(ctx.inicializacaoVetor());
        }
        return "";
    }

    // ======================================================================
    // 6. visitLeitura  (CORRIGIDO)
    //    - usa visit(ctx.variavel()) para obter o destino Java correto
    //    - verifica nomeVar != null antes de consultar a tabela
    // ======================================================================
    @Override
    public String visitLeitura(TarbaloParser.LeituraContext ctx) {
        int linha = ctx.start.getLine();

        // Leitura para campo de struct: espia(p::nome).
        if (ctx.acessoElem() != null) {
            String alvo = visit(ctx.acessoElem());
            // Determinar tipo do campo para usar scanner correto
            String nomeEsq = ctx.acessoElem().ID(0).getText();
            String nomeDir = ctx.acessoElem().ID(1).getText();
            Simbolo simEsq = tabela.buscarComMemoria(nomeEsq);
            String tipoCampo = "txt"; // default
            if (simEsq != null) {
                String tipoStruct = simEsq.getTipo();
                // Se é vetor com dimensões, usar tipo base (ex: "Pessoa" de "Pessoa[]")
                if (simEsq.getCategoria() == Simbolo.Categoria.VETOR && !ctx.acessoElem().dimensao().isEmpty()) {
                    tipoStruct = simEsq.getTipoBase();
                }
                Simbolo sCampo = tabela.buscarCampo(tipoStruct, nomeDir);
                if (sCampo != null) tipoCampo = sCampo.getTipo();
            }
            String scannerMethod = getScannerMethod(tipoCampo);
            boolean consumirNewline = !tipoCampo.equals("txt");
            return alvo + " = " + scannerMethod + (consumirNewline ? ";\n        scanner.nextLine()" : "") + ";";
        }

        TarbaloParser.VariavelContext sel = ctx.variavel();

        // Verificar se é um slice (variavel com alguma dimensão contendo PONTOPONTO)
        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            String nomeVetor = sel.ID().getText();
            // Determinar o tipo base para usar o scanner correto
            Simbolo simVetor = tabela.buscarComMemoria(nomeVetor);
            String tipoBase = simVetor != null ? simVetor.getTipoBase() : null;
            String scannerMethod = getScannerMethod(tipoBase);

            // Coletar informações de cada dimensão
            int ndims = sel.dimensao().size();
            String[] idxVars = new String[ndims];
            int sliceCount = 0;
            String indent = "";

            // Primeira passada: gerar loops para dimensões com slice
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ndims; i++) {
                TarbaloParser.DimensaoContext dim = sel.dimensao(i);
                if (dim.PONTOPONTO() != null) {
                    String varName = "__tarbalo_i" + (sliceCount > 0 ? String.valueOf(sliceCount) : "");
                    idxVars[i] = varName;
                    String inicio = visit(dim.expressao(0));
                    String fim;
                    if (dim.expressao(1) != null) {
                        fim = "(" + visit(dim.expressao(1)) + ") + 1";
                    } else {
                        // Slice aberto [expr..] → usa tamanho da dimensão correta
                        StringBuilder dimAccess = new StringBuilder(nomeVetor);
                        for (int j = 0; j < i; j++) {
                            dimAccess.append(".get(").append(idxVars[j]).append(")");
                        }
                        fim = dimAccess.toString() + ".size()";
                    }
                    sb.append(indent).append("for (int ").append(varName).append(" = ").append(inicio)
                        .append("; ").append(varName).append(" < ").append(fim)
                        .append("; ").append(varName).append("++) {\n");
                    indent += "    ";
                    sliceCount++;
                } else {
                    idxVars[i] = visit(dim.expressao(0));
                }
            }

            // Gerar o acesso ao elemento: v.get(idx0).set(idx1, scanner...)
            StringBuilder getPrefix = new StringBuilder(nomeVetor);
            for (int i = 0; i < ndims - 1; i++) {
                getPrefix.append(".get(").append(idxVars[i]).append(")");
            }
            String lastIdx = idxVars[ndims - 1];

            sb.append(indent).append(getPrefix.toString()).append(".set(").append(lastIdx).append(", ").append(scannerMethod).append(");\n");
            // Consumir newline para tipos não-txt
            if (tipoBase != null && !tipoBase.equals("txt")) {
                sb.append(indent).append("scanner.nextLine();\n");
            }

            // Fechar chaves dos loops
            for (int i = sliceCount - 1; i >= 0; i--) {
                indent = indent.substring(4);
                sb.append(indent).append("}");
                if (i > 0) sb.append("\n");
            }

            return sb.toString();
        }

        // Leitura normal (variável simples ou elemento de vetor)
        String nomeVar = nomeBaseDeVariavel(sel);
        // (verificação de declaração já feita pelo AnalisadorSemântico)

        Simbolo simVar = nomeVar != null ? tabela.buscarComMemoria(nomeVar) : null;
        String tipoTarbalo = simVar != null ? simVar.getTipoBase() : null;
        String scannerMethod = getScannerMethod(tipoTarbalo);
        // Consumir \n restante após leitura de tipos não-txt para evitar conflito com nextLine()
        String consumirNewline = (tipoTarbalo != null && !tipoTarbalo.equals("txt"))
            ? " scanner.nextLine();"
            : "";

        // Se o destino é acesso indexado em ArrayList → usar .set()
        if (!sel.dimensao().isEmpty() && !possuiSlice(sel)) {
            int ndims = sel.dimensao().size();
            StringBuilder getPrefix = new StringBuilder(nomeVar);
            for (int d = 0; d < ndims - 1; d++) {
                getPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
            }
            String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));
            return getPrefix.toString() + ".set(" + lastIdx + ", " + scannerMethod + ");" + consumirNewline;
        }

        // Variável simples
        String destino = visit(sel);
        return destino + " = " + scannerMethod + ";" + consumirNewline;
    }

    // ======================================================================
    // 7. visitEscrita  (CORRIGIDO — itera todas as expressões)
    //    A gramática permite: escreva(expr1; expr2; ...).
    //    Cada expressão é concatenada uma na outra.
    // ======================================================================
    @Override
    public String visitEscrita(TarbaloParser.EscritaContext ctx) {
        if (ctx.expressao() == null) {
            return "System.out.println();";
        }
        return "System.out.println(" + visit(ctx.expressao()) + ");";
    }

    // ======================================================================
    // 8. visitCmdSe
    // ======================================================================
    @Override
    public String visitCmdSe(TarbaloParser.CmdSeContext ctx) {
        String condicao  = visit(ctx.expressao());
        String blocoSe   = visit(ctx.bloco(0));

        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condicao).append(") {\n");
        sb.append(indentar(blocoSe, "    "));
        sb.append("}");

        if (ctx.SENAO() != null) {
            String blocoSenao = visit(ctx.bloco(1));
            sb.append(" else {\n");
            sb.append(indentar(blocoSenao, "    "));
            sb.append("}");
        }

        return sb.toString();
    }

    // ======================================================================
    // HIERARQUIA DE EXPRESSÕES  (REESCRITA — sem visitChildren genérico)
    //
    //  A ordem da gramática é:
    //    expressao → expressaoXor → expressaoE → expressaoNegacao
    //      → expressaoConcatenacao → expressaoXor → expressaoE → expressaoNegacao
    //      → expressaoRelacional → expressaoAditiva → expressaoMultiplicativa
    //      → expressaoMultiplicativa → operando
    //
    //  Cada nível sabe exatamente qual operador usar, preservando a
    //  precedência que o ANTLR calculou na árvore.
    // ======================================================================

    /** expressao : expressaoConcatenacao (OU expressaoConcatenacao)* */
    @Override
    public String visitExpressao(TarbaloParser.ExpressaoContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoConcatenacao(0)));
        for (int i = 1; i < ctx.expressaoConcatenacao().size(); i++) {
            sb.append(" || ").append(visit(ctx.expressaoConcatenacao(i)));
        }
        return sb.toString();
    }

    /** expressaoXor : expressaoE (XOR expressaoE)* */
    @Override
    public String visitExpressaoXor(TarbaloParser.ExpressaoXorContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoE(0)));
        for (int i = 1; i < ctx.expressaoE().size(); i++) {
            sb.append(" ^ ").append(visit(ctx.expressaoE(i)));
        }
        return sb.toString();
    }

    /** expressaoE : expressaoNegacao (E expressaoNegacao)* */
    @Override
    public String visitExpressaoE(TarbaloParser.ExpressaoEContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoNegacao(0)));
        for (int i = 1; i < ctx.expressaoNegacao().size(); i++) {
            sb.append(" && ").append(visit(ctx.expressaoNegacao(i)));
        }
        return sb.toString();
    }

    /** expressaoNegacao : NAO expressaoNegacao | expressaoRelacional */
    @Override
    public String visitExpressaoNegacao(TarbaloParser.ExpressaoNegacaoContext ctx) {
        if (ctx.NAO() != null) {
            return "!" + visit(ctx.expressaoNegacao());
        }
        return visit(ctx.expressaoRelacional());
    }

    /** expressaoRelacional : expressaoAditiva (operadorRelacional expressaoAditiva)? */
    @Override
    public String visitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx) {
        String esq = visit(ctx.expressaoAditiva(0));
        if (ctx.operadorRelacional() != null) {
            String op  = ctx.operadorRelacional().getText();
            String dir = visit(ctx.expressaoAditiva(1));

            // Descobrir tipo dos operandos para traduzir corretamente
            String tipoEsq = analisador.consultarTipo(ctx.expressaoAditiva(0));
            String tipoDir = analisador.consultarTipo(ctx.expressaoAditiva(1));

            // Se algum dos tipos é String (txt), traduzir para compareTo/equals
            if (tipoEsq.equals("txt") || tipoDir.equals("txt")) {
                if (op.equals("=")) {
                    return esq + ".equals(" + dir + ")";
                } else if (op.equals("!=")) {
                    return "!" + esq + ".equals(" + dir + ")";
                } else {
                    // Operadores de ordenação: compareTo
                    String javaOp = traduzirOpRelacional(op);
                    return esq + ".compareTo(" + dir + ") " + javaOp + " 0";
                }
            }

            // Para outros tipos, traduzir '=' para '=='
            String javaOp = traduzirOpRelacional(op);
            return esq + " " + javaOp + " " + dir;
        }
        return esq;
    }

    private String traduzirOpRelacional(String op) {
        return op.equals("=") ? "==" : op;
    }

    /**
     * operadorRelacional : MENOR | MAIOR | MENORIGUAL | MAIORIGUAL | IGUAL | DIFERENTE
     *
     * Em Tarbalo '=' é igualdade → Java '=='.
     * Os restantes (<, >, <=, >=, !=) são idênticos em Java.
     */
    @Override
    public String visitOperadorRelacional(TarbaloParser.OperadorRelacionalContext ctx) {
        String op = ctx.getText();
        return op.equals("=") ? "==" : op;
    }

    /**
     * expressaoAditiva : expressaoMultiplicativa ((MAIS | MENOS) expressaoMultiplicativa)*
     *
     * Os tokens de operador ficam nas posições ímpares da lista de filhos.
     */
    @Override
    public String visitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoMultiplicativa(0)));
        int exprIdx = 1;
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            sb.append(" ").append(op).append(" ")
                    .append(visit(ctx.expressaoMultiplicativa(exprIdx++)));
        }
        return sb.toString();
    }

    /**
     * expressaoConcatenacao : expressaoXor (CONCAT expressaoXor)*
     *
     * CONCAT em Tarbalo é '&' → Java '+' (concatenação de strings).
     * Usa String.valueOf() para garantir concatenação (evitar adição numérica).
     */
    @Override
    public String visitExpressaoConcatenacao(TarbaloParser.ExpressaoConcatenacaoContext ctx) {
        if (!ctx.CONCAT().isEmpty()) {
            StringBuilder sb = new StringBuilder(valorComoString(visit(ctx.expressaoXor(0))));
            for (int i = 1; i < ctx.expressaoXor().size(); i++) {
                sb.append(" + ").append(valorComoString(visit(ctx.expressaoXor(i))));
            }
            return sb.toString();
        }
        return visit(ctx.expressaoXor(0));
    }

    /** Retorna o código Java como String — evita String.valueOf() redundante para literais/variáveis txt. */
    private String valorComoString(String exprJava) {
        if (exprJava == null) return "\"\"";
        if (exprJava.startsWith("\"")) return exprJava;
        return "String.valueOf(" + exprJava + ")";
    }

    /**
     * expressaoMultiplicativa : operando ((MULT | DIV | DIVINT | MOD) operando)*
     *
     * DIVINT em Tarbalo é '//' → Java '/' (divisão inteira quando ambos são int).
     */
    @Override
    public String visitExpressaoMultiplicativa(TarbaloParser.ExpressaoMultiplicativaContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.operando(0)));
        int exprIdx = 1;
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            if (op.equals("//")) {
                // DIVINT → cast para int garante divisão inteira mesmo com operandos mistos
                sb.insert(0, "(int)(");
                sb.append(" / ").append(visit(ctx.operando(exprIdx++))).append(")");
            } else {
                sb.append(" ").append(op).append(" ")
                        .append(visit(ctx.operando(exprIdx++)));
            }
        }
        return sb.toString();
    }

    /**
     * operando : NUM | NUMDEC | STRING | CHAR | VERDADEIRO | FALSO
     *          | variavel | chamadaFuncao | '(' expressao ')'
     *
     * Conversões necessárias:
     *   VDD  → true
     *   FAKE → false
     *   NUMDEC usa vírgula como separador decimal → substituir por ponto
     *   Parênteses: manter '(' e ')' explicitamente
     */
    @Override
    public String visitOperando(TarbaloParser.OperandoContext ctx) {
        if (ctx.NUM()         != null) return ctx.NUM().getText();
        if (ctx.NUMDEC()      != null) return ctx.NUMDEC().getText().replace(",", ".");
        if (ctx.STRING()      != null) return ctx.STRING().getText();
        if (ctx.CHAR()        != null) return ctx.CHAR().getText();
        if (ctx.VERDADEIRO()  != null) return "true";
        if (ctx.FALSO()       != null) return "false";
        if (ctx.acessoElem()  != null) return visit(ctx.acessoElem());
        if (ctx.variavel()    != null) return visit(ctx.variavel());
        if (ctx.chamadaFuncao()!= null) return visit(ctx.chamadaFuncao());
        if (ctx.chamadaConstrutor()!= null) return visit(ctx.chamadaConstrutor());
        if (ctx.expressao()   != null) return "(" + visit(ctx.expressao()) + ")";
        // Unário negativo: -operando
        if (ctx.MENOS()       != null) return "(-" + visit(ctx.operando()) + ")";
        return "";
    }

    // ======================================================================
    // Operador de Atribuição Composta  (NOVO)
    // ======================================================================
    @Override
    public String visitOperadorAtribuicaoComposta(TarbaloParser.OperadorAtribuicaoCompostaContext ctx) {
        return switch (ctx.getText()) {
            case ":+" -> "+=";
            case ":-" -> "-=";
            case ":*" -> "*=";
            case ":/" -> "/=";
            case ":%" -> "%=";
            default   -> "=";
        };
    }

    // ======================================================================
    // 10. visitCmdEnquanto (while)
    // ======================================================================
    @Override
    public String visitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        String condicao  = visit(ctx.expressao());
        String blocoLoop = visit(ctx.bloco());
        StringBuilder sb = new StringBuilder();
        sb.append("while (").append(condicao).append(") {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("}");
        return sb.toString();
    }

    // ======================================================================
    // 11. visitCmdFacaEnquanto (do-while)
    // ======================================================================
    @Override
    public String visitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        String blocoLoop = visit(ctx.bloco());
        String condicao  = visit(ctx.expressao());
        StringBuilder sb = new StringBuilder();
        sb.append("do {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("} while (").append(condicao).append(");");
        return sb.toString();
    }

    // ======================================================================
    // 12. visitCmdPara (for)
    // ======================================================================
    @Override
    public String visitCmdPara(TarbaloParser.CmdParaContext ctx) {
        String inicializacao = visit(ctx.inicializacaoPara());
        String condicao      = visit(ctx.expressao());
        String atualizacao   = visit(ctx.atualizacaoPara());
        String blocoLoop     = visit(ctx.bloco());

        StringBuilder sb = new StringBuilder();
        sb.append("for (").append(inicializacao).append("; ")
                .append(condicao).append("; ").append(atualizacao).append(") {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("}");
        return sb.toString();
    }

    // ======================================================================
    // 13. visitCmdParaCada (foreach)
    // ======================================================================
    @Override
    public String visitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        String tipoJava  = mapearTipo(ctx.tipoVariavel().getText());
        String nomeVar   = ctx.ID().getText();
        String colecao   = visit(ctx.expressao());
        String blocoLoop = visit(ctx.bloco());
        StringBuilder sb = new StringBuilder();
        sb.append("for (").append(tipoJava).append(" ").append(nomeVar)
                .append(" : ").append(colecao).append(") {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String visitInicializacaoPara(TarbaloParser.InicializacaoParaContext ctx) {
        // Se for apenas uma atribuição (ex: i : 0)
        if (ctx.atribuicaoPara() != null) {
            return visit(ctx.atribuicaoPara());
        }
        // Se for uma declaração completa de variável (ex: var int i : 0)
        if (ctx.VARIAVEL() != null) {
            String tipoJava = mapearTipo(ctx.tipoVariavel().getText());
            String nomeVar = ctx.ID().getText();
            String valor = visit(ctx.expressao());
            return tipoJava + " " + nomeVar + " = " + valor; // Sem ponto e vírgula no final, pois o for já os coloca
        }
        return "";
    }

    // ======================================================================
    // 14. Auxiliares do FOR e Incrementos
    // ======================================================================
    @Override
    public String visitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx) {
        TarbaloParser.VariavelContext sel = ctx.variavel();
        String valor = visit(ctx.valorAtribuicao());
        String nomeVetor = sel.ID().getText();
        int ndims = sel.dimensao().size();

        // Se o destino é acesso indexado em ArrayList → usar .set()
        if (!sel.dimensao().isEmpty() && !possuiSlice(sel)) {
            Simbolo simVetor = tabela.buscarComMemoria(nomeVetor);
            String tipoBaseElem = simVetor != null ? simVetor.getTipoBase() : "int";
            String javaBaseElem = mapearTipo(tipoBaseElem);
            String cast = "";
            if (javaBaseElem.equals("double")) cast = "(double) ";
            else if (javaBaseElem.equals("int")) cast = "(int) ";

            if (ctx.operadorAtribuicaoComposta() != null) {
                String opJava = visit(ctx.operadorAtribuicaoComposta());
                String opSimples = opJava.charAt(0) + ""; // += → +, -= → -, etc.
                StringBuilder getChain = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims; d++) {
                    getChain.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                StringBuilder setPrefix = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims - 1; d++) {
                    setPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));
                return setPrefix.toString() + ".set(" + lastIdx + ", " + getChain.toString() + " " + opSimples + " " + cast + valor + ")";
            } else {
                StringBuilder setPrefix = new StringBuilder(nomeVetor);
                for (int d = 0; d < ndims - 1; d++) {
                    setPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
                }
                String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));
                return setPrefix.toString() + ".set(" + lastIdx + ", " + cast + valor + ")";
            }
        }

        // Variável simples
        String destino = visit(sel);
        String opJava = "=";
        if (ctx.operadorAtribuicaoComposta() != null) {
            opJava = visit(ctx.operadorAtribuicaoComposta());
        }
        return destino + " " + opJava + " " + valor;
    }

    @Override
    public String visitAtualizacaoPara(TarbaloParser.AtualizacaoParaContext ctx) {
        if (ctx.incremento()    != null) return visit(ctx.incremento());
        if (ctx.decremento()    != null) return visit(ctx.decremento());
        if (ctx.atribuicaoPara()!= null) return visit(ctx.atribuicaoPara());
        return "";
    }

    @Override
    public String visitIncremento(TarbaloParser.IncrementoContext ctx) {
        return visitIncrementoDecremento(ctx.variavel(), "++");
    }

    @Override
    public String visitDecremento(TarbaloParser.DecrementoContext ctx) {
        return visitIncrementoDecremento(ctx.variavel(), "--");
    }

    private String visitIncrementoDecremento(TarbaloParser.VariavelContext sel, String op) {
        String nomeVetor = sel.ID().getText();
        int ndims = sel.dimensao().size();

        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            // Slice: gerar loops aninhados
            String[] idxVars = new String[ndims];
            int sliceCount = 0;
            String indent = "";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ndims; i++) {
                TarbaloParser.DimensaoContext dim = sel.dimensao(i);
                if (dim.PONTOPONTO() != null) {
                    String varName = "__tarbalo_i" + (sliceCount > 0 ? String.valueOf(sliceCount) : "");
                    idxVars[i] = varName;
                    String inicio = visit(dim.expressao(0));
                    String fim;
                    if (dim.expressao(1) != null) {
                        fim = "(" + visit(dim.expressao(1)) + ") + 1";
                    } else {
                        StringBuilder dimAccess = new StringBuilder(nomeVetor);
                        for (int j = 0; j < i; j++) dimAccess.append(".get(0)");
                        fim = dimAccess.toString() + ".size()";
                    }
                    sb.append(indent).append("for (int ").append(varName).append(" = ").append(inicio)
                        .append("; ").append(varName).append(" < ").append(fim)
                        .append("; ").append(varName).append("++) {\n");
                    indent += "    ";
                    sliceCount++;
                } else {
                    idxVars[i] = visit(dim.expressao(0));
                }
            }

            // Gerar acesso: v.get(idx0).set(idx1, v.get(idx0).get(idx1) + 1)
            StringBuilder getPrefix = new StringBuilder(nomeVetor);
            for (int i = 0; i < ndims - 1; i++) {
                getPrefix.append(".get(").append(idxVars[i]).append(")");
            }
            String lastIdx = idxVars[ndims - 1];

            // Build full get chain for the value
            StringBuilder getChain = new StringBuilder(nomeVetor);
            for (int i = 0; i < ndims; i++) {
                getChain.append(".get(").append(idxVars[i]).append(")");
            }

            String javaOp = op.equals("++") ? " + 1" : " - 1";
            sb.append(indent).append(getPrefix.toString()).append(".set(").append(lastIdx).append(", ").append(getChain.toString()).append(javaOp).append(");\n");

            for (int i = sliceCount - 1; i >= 0; i--) {
                indent = indent.substring(4);
                sb.append(indent).append("}");
                if (i > 0) sb.append("\n");
            }

            return sb.toString();
        }

        // Normal: variável simples
        if (sel.dimensao().isEmpty()) {
            return visit(sel) + op;
        }

        // Elemento de vetor indexado: v[i]++ → v.set(i, v.get(i) + 1)
        StringBuilder getPrefix = new StringBuilder(nomeVetor);
        for (int d = 0; d < ndims - 1; d++) {
            getPrefix.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
        }
        String lastIdx = visit(sel.dimensao(ndims - 1).expressao(0));

        StringBuilder getChain = new StringBuilder(nomeVetor);
        for (int d = 0; d < ndims; d++) {
            getChain.append(".get(").append(visit(sel.dimensao(d).expressao(0))).append(")");
        }

        String javaOp = op.equals("++") ? " + 1" : " - 1";
        return getPrefix.toString() + ".set(" + lastIdx + ", " + getChain.toString() + javaOp + ")";
    }

    @Override
    public String visitIncrementoPonto(TarbaloParser.IncrementoPontoContext ctx) {
        String resultado = visit(ctx.incremento());
        return resultado.endsWith("}") ? resultado : resultado + ";";
    }

    @Override
    public String visitDecrementoPonto(TarbaloParser.DecrementoPontoContext ctx) {
        String resultado = visit(ctx.decremento());
        return resultado.endsWith("}") ? resultado : resultado + ";";
    }

    // ======================================================================
    // 15. Controlo de Fluxo (break, continue, return)
    // ======================================================================
    @Override
    public String visitPare(TarbaloParser.PareContext ctx) { return "break;"; }

    @Override
    public String visitContinuar(TarbaloParser.ContinuarContext ctx) { return "continue;"; }

    @Override
    public String visitRetorno(TarbaloParser.RetornoContext ctx) {
        if (ctx.expressao() != null) {
            return "return " + visit(ctx.expressao()) + ";";
        }
        return "return;";
    }

    // ======================================================================
    // 16. Vetores: Declaração
    // ======================================================================
    @Override
    public String visitDeclaracaoVetor(TarbaloParser.DeclaracaoVetorContext ctx) {
        String tipoBase = ctx.tipoVariavel().getText(); // "int", "txt", etc.
        String nomeVar  = ctx.ID().getText();
        int ndims = ctx.dimensao().size();

        // Build full type string with dimensions: "int[]", "int[][]", etc.
        String tipoCompleto = tipoBase + "[]".repeat(ndims);
        String tipoJava = mapearTipo(tipoCompleto); // "ArrayList<Integer>", etc.

        String corpo;

        // Caso 1: inicialização explícita → usa os valores fornecidos
        if (ctx.ATRIBUICAO() != null) {
            if (ctx.valorAtribuicao().inicializacaoVetor() != null) {
                // bond int v[3]: [1; 2; 3].
                String valores = visit(ctx.valorAtribuicao().inicializacaoVetor());
                corpo = tipoJava + " " + nomeVar + " = new ArrayList<>(" + valores + ");";
            } else {
                // bond int fatia[]: slice1d_int(v; 0; 2).
                String expr = visit(ctx.valorAtribuicao().expressao());
                corpo = tipoJava + " " + nomeVar + " = " + expr + ";";
            }
        } else {
            // Caso 2: sem inicialização
            boolean primeiraDimComTamanho = !ctx.dimensao(0).expressao().isEmpty();

            if (primeiraDimComTamanho) {
                // Has sizes → allocate with Collections.nCopies or loop
                String indentAloc = "    ".repeat(escopoProfundidade + 1);
                String alocacao = alocacaoArrayList(tipoBase, ctx.dimensao(), nomeVar, indentAloc);
                // alocacao may be multi-line (for 2D+) or single-line (for 1D)
                if (alocacao.contains("\n")) {
                    // Multi-line: for 2D+ arrays, use static block
                    if (escopoProfundidade <= 1) {
                        // Global scope: declare field uninitialized, init in static block
                        globaisGeradas.append("    static ").append(tipoJava).append(" ").append(nomeVar).append(";\n");
                        blocosEstaticos.append("        ").append(nomeVar).append(" = ").append(alocacao).append("\n");
                        return "";
                    }
                    // Local scope: inline (loop is valid inside main)
                    corpo = tipoJava + " " + nomeVar + " = " + alocacao;
                } else {
                    corpo = tipoJava + " " + nomeVar + " = " + alocacao + ";";
                }
            } else {
                // No sizes → empty ArrayList
                corpo = tipoJava + " " + nomeVar + " = new ArrayList<>();";
            }
        }

        // escopoProfundidade == 1 → campo estático da classe
        if (escopoProfundidade <= 1) {
            globaisGeradas.append("    static ").append(corpo).append("\n");
            return "";
        }
        return corpo + "\n";
    }

    // ======================================================================
    // 17. Vetores: Inicialização  { expr1, expr2, ... }
    // ======================================================================
    @Override
    public String visitInicializacaoVetor(TarbaloParser.InicializacaoVetorContext ctx) {
        StringBuilder sb = new StringBuilder("Arrays.asList(");
        for (int i = 0; i < ctx.elemVetor().size(); i++) {
            if (i > 0) sb.append(", ");
            TarbaloParser.ElemVetorContext elem = ctx.elemVetor(i);
            if (elem.inicializacaoVetor() != null) {
                // Elemento aninhado: [[1; 2]; [3; 4]] → new ArrayList<>(Arrays.asList(...))
                sb.append("new ArrayList<>(").append(visit(elem.inicializacaoVetor())).append(")");
            } else {
                // Elemento escalar
                sb.append(visit(elem.expressao()));
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // ======================================================================
    // 18. (visitAcessoVetor, visitAcessoDimensao, visitCriacaoVetor removidos
    //      — lógica absorvida por visitVariavel)
    // ======================================================================

    // ======================================================================
    // 19. Declaração de Função
    // ======================================================================
    @Override
    public String visitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        String tipoRetorno = "void";
        if (ctx.tipoRetorno() != null) {
            String tr = ctx.tipoRetorno().getText();
            tipoRetorno = tr.equals("vazio") ? "void" : mapearTipo(tr);
        }

        String nomeFuncao  = ctx.ID().getText();
        String parametros  = ctx.parametros() != null ? visit(ctx.parametros()) : "";
        String bloco       = visit(ctx.bloco());

        StringBuilder sb = new StringBuilder();
        sb.append("public static ").append(tipoRetorno).append(" ")
                .append(nomeFuncao).append("(").append(parametros).append(") {\n");
        sb.append(indentar(bloco, "    "));
        sb.append("}\n");

        funcoesGeradas.append(indentar(sb.toString(), "    ")).append("\n");
        return ""; // Não aparece dentro do main()
    }

    // ======================================================================
    // 20. Parâmetros e Argumentos
    // ======================================================================
    @Override
    public String visitParametros(TarbaloParser.ParametrosContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.parametro().size(); i++) {
            sb.append(visit(ctx.parametro(i)));
            if (i < ctx.parametro().size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public String visitParametro(TarbaloParser.ParametroContext ctx) {
        String tipoBase = ctx.tipoVariavel().getText();
        String nomeVar  = ctx.variavel().ID().getText();
        int ndims = ctx.variavel().dimensao().size();
        String tipoCompleto = tipoBase + "[]".repeat(ndims);
        String tipoJava = mapearTipo(tipoCompleto);
        return tipoJava + " " + nomeVar;
    }

    @Override
    public String visitArgumentos(TarbaloParser.ArgumentosContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.expressao().size(); i++) {
            sb.append(visit(ctx.expressao(i)));
            if (i < ctx.expressao().size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    // ======================================================================
    // 21. Chamada de Função
    // ======================================================================
    @Override
    public String visitChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx) {
        String nomeFunc = ctx.ID().getText();
        String argumentos = ctx.argumentos() != null ? visit(ctx.argumentos()) : "";

        // Detectar instanciação de struct: NomeStruct() → new NomeStruct()
        Simbolo sStruct = tabela.buscarComMemoria(nomeFunc);
        if (sStruct != null && sStruct.getCategoria() == Simbolo.Categoria.REGS) {
            return "new " + nomeFunc + "(" + argumentos + ")";
        }

        Simbolo fun = analisador.obterResolucao(ctx);

        // Se a função é 'tamanho' ou 'tamanho_<tipo>', tratar especialmente
        if (nomeFunc.equals("tamanho") || nomeFunc.startsWith("tamanho_")) {
            String arg = visit(ctx.argumentos().expressao(0));
            // Contar dimensões do parâmetro
            int dims = 0;
            if (fun != null && fun.getTiposParametros() != null && !fun.getTiposParametros().isEmpty()) {
                String tipoParam = fun.getTiposParametros().get(0);
                String base = tipoParam.replaceAll("\\[\\]", "");
                dims = (tipoParam.length() - base.length()) / 2;
            }
            if (dims >= 2) {
                // Para 2D+: retorna total de elementos (dim0 × dim1 × dim2 × ...)
                StringBuilder sb = new StringBuilder();
                sb.append(arg);
                for (int d = 0; d < dims; d++) {
                    sb.insert(0, arg + ".isEmpty() ? 0 : ");
                    if (d < dims - 1) {
                        sb.append(".get(0)");
                    }
                    sb.append(".size()");
                    if (d < dims - 1) {
                        // Wrap for next dimension
                        sb = new StringBuilder(sb.toString());
                    }
                }
                // Build chain: arg.size() * arg.get(0).size() * arg.get(0).get(0).size() ...
                StringBuilder chain = new StringBuilder();
                for (int d = 0; d < dims; d++) {
                    if (d > 0) chain.append(" * ");
                    chain.append(arg);
                    for (int j = 0; j < d; j++) chain.append(".get(0)");
                    chain.append(".size()");
                }
                return arg + ".isEmpty() ? 0 : " + chain.toString();
            }
            return arg + ".size()";
        }

        // Se foi resolvido pelo analisador, use o nome do símbolo
        if (fun != null) {
            String nomeReal = fun.getNome();

            // Se o nome é genérico (sem sufixo), adicionar sufixo do tipo
            if (isNomeGenerico(nomeReal) && !fun.getTiposParametros().isEmpty()) {
                String tipoPrimeiroParam = fun.getTiposParametros().get(0);
                String sufixo = extrairSufixoTipo(tipoPrimeiroParam);
                if (sufixo != null) {
                    nomeReal = nomeReal + "_" + sufixo;
                }
            }

            return nomeReal + "(" + argumentos + ")";
        }

        // Função não resolvida — não deveria chegar aqui se o analisador validou
        System.err.println("Erro interno: função '" + nomeFunc + "' não resolvida pelo analisador semântico.");
        return "/* ERRO: função não resolvida */";
    }

    /** Verifica se o nome é genérico (sem sufixo de tipo). */
    private boolean isNomeGenerico(String nome) {
        return nome.equals("ordenar") || nome.equals("inverter") ||
               nome.equals("anexar") || nome.equals("inserir") ||
               nome.equals("remover") || nome.equals("redim") ||
               nome.equals("slice1d") || nome.equals("slice2d");
    }

    /** Extrai o sufixo do tipo do parâmetro. Ex: "int[]" → "int", "txt[][]" → "txt". */
    private String extrairSufixoTipo(String tipoParam) {
        if (tipoParam == null) return null;
        // Remove todos os colchetes
        String base = tipoParam.replaceAll("\\[\\]", "");
        if (base.equals("int") || base.equals("qbd") || base.equals("txt") ||
            base.equals("ltr") || base.equals("lgc")) {
            return base;
        }
        return null;
    }

    // ======================================================================
    // 22. visitCmdBloco
    // ======================================================================
    @Override
    public String visitCmdBloco(TarbaloParser.CmdBlocoContext ctx) {
        String bloco = visit(ctx.bloco());
        return "{\n" + indentar(bloco, "    ") + "}";
    }

    // ======================================================================
    // 23. visitComando
    // ======================================================================
    @Override
    public String visitComando(TarbaloParser.ComandoContext ctx) {
        if (ctx.declaracao() != null) return visit(ctx.declaracao());
        if (ctx.atribuicao() != null) return visit(ctx.atribuicao());
        if (ctx.leitura() != null) return visit(ctx.leitura());
        if (ctx.escrita() != null) return visit(ctx.escrita());
        if (ctx.cmdSe() != null) return visit(ctx.cmdSe());
        if (ctx.cmdEnquanto() != null) return visit(ctx.cmdEnquanto());
        if (ctx.cmdFacaEnquanto() != null) return visit(ctx.cmdFacaEnquanto());
        if (ctx.cmdPara() != null) return visit(ctx.cmdPara());
        if (ctx.cmdParaCada() != null) return visit(ctx.cmdParaCada());
        if (ctx.cmdBloco() != null) return visit(ctx.cmdBloco());
        if (ctx.cmdEscolha() != null) return visit(ctx.cmdEscolha());
        if (ctx.cmdTente() != null) return visit(ctx.cmdTente());
        if (ctx.retorno() != null) return visit(ctx.retorno());
        if (ctx.pare() != null) return visit(ctx.pare());
        if (ctx.continuar() != null) return visit(ctx.continuar());
        if (ctx.incrementoPonto() != null) return visit(ctx.incrementoPonto());
        if (ctx.decrementoPonto() != null) return visit(ctx.decrementoPonto());
        if (ctx.chamadaFuncao() != null) { return visit(ctx.chamadaFuncao()) + ";"; }
        return "";
    }

    // ======================================================================
    // Métodos auxiliares
    // ======================================================================

    private String getSufixoTipo(String tipoBase) {
        return switch (tipoBase) {
            case "int"    -> "_int";
            case "qbd"    -> "_qbd";
            case "txt"    -> "_txt";
            case "ltr"    -> "_ltr";
            case "lgc"    -> "_lgc";
            default       -> "_int"; // fallback
        };
    }

    /** Gera um método helper de slice em Java. */
    private String generarSliceHelper(String nome) {
        // Formato: sliceNd_tipo (ex: slice1d_int, slice2d_qbd)
        String[] partes = nome.split("_", 2);
        int ndims = Integer.parseInt(partes[0].replace("slice", "").replace("d", ""));
        String tipoSufixo = partes.length > 1 ? partes[1] : "int";

        String javaTipo = switch (tipoSufixo) {
            case "int"  -> "Integer";
            case "qbd"  -> "Double";
            case "txt"  -> "String";
            case "ltr"  -> "Character";
            case "lgc"  -> "Boolean";
            default     -> "Integer";
        };

        StringBuilder sb = new StringBuilder();
        if (ndims == 1) {
            // public static ArrayList<Integer> slice1d_int(ArrayList<Integer> arr, int ini, int fim) {
            //     return new ArrayList<>(arr.subList(ini, Math.min(fim, arr.size())));
            // }
            sb.append("    public static ArrayList<").append(javaTipo).append("> ").append(nome)
              .append("(ArrayList<").append(javaTipo).append("> arr, int ini, int fim) {\n");
            sb.append("        return new ArrayList<>(arr.subList(ini, Math.min(fim, arr.size())));\n");
            sb.append("    }\n");
        } else if (ndims == 2) {
            // public static ArrayList<ArrayList<Integer>> slice2d_int(...) { ... }
            String innerTipo = "ArrayList<" + javaTipo + ">";
            sb.append("    public static ArrayList<").append(innerTipo).append("> ").append(nome)
              .append("(ArrayList<").append(innerTipo).append("> arr, int ini0, int fim0, int ini1, int fim1) {\n");
            sb.append("        ArrayList<").append(innerTipo).append("> res = new ArrayList<>();\n");
            sb.append("        for (int i = ini0; i < Math.min(fim0, arr.size()); i++) {\n");
            sb.append("            res.add(new ArrayList<>(arr.get(i).subList(ini1, Math.min(fim1, arr.get(i).size()))));\n");
            sb.append("        }\n");
            sb.append("        return res;\n");
            sb.append("    }\n");
        }
        return sb.toString();
    }

    // ======================================================================
    // Despachante genérico para Declarações
    // ======================================================================
    @Override
    public String visitDeclaracao(TarbaloParser.DeclaracaoContext ctx) {
        if (ctx.declaracaoVariavel() != null) return visit(ctx.declaracaoVariavel());
        if (ctx.declaracaoVetor()    != null) return visit(ctx.declaracaoVetor());
        if (ctx.declaracaoFuncao()   != null) return visit(ctx.declaracaoFuncao());
        if (ctx.declaracaoEnum()     != null) return visit(ctx.declaracaoEnum());
        if (ctx.declaracaoStruct()   != null) return visit(ctx.declaracaoStruct());
        return "";
    }

    // ======================================================================
    // CNJT (Enum)
    // ======================================================================
    @Override
    public String visitDeclaracaoEnum(TarbaloParser.DeclaracaoEnumContext ctx) {
        String tipoJava = mapearTipo(ctx.tipoVariavel().getText());
        String nomeCnjt = ctx.ID().getText();
        StringBuilder sb = new StringBuilder();
        sb.append("public static class ").append(nomeCnjt).append(" {\n");
        for (TarbaloParser.CnjtElemContext elem : ctx.cnjtElem()) {
            String nomeElem = elem.ID().getText();
            sb.append("    public static final ").append(tipoJava).append(" ").append(nomeElem);
            if (elem.ATRIBUICAO() != null && elem.expressao() != null) {
                sb.append(" = ").append(visit(elem.expressao()));
            } else {
                // Auto-valor para int: precisa contar
                sb.append(" = ").append(calcularAutoValorCnjt(ctx, elem));
            }
            sb.append(";\n");
        }
        sb.append("}\n");
        cnjtsGerados.append(sb.toString());
        return "";
    }

    private int calcularAutoValorCnjt(TarbaloParser.DeclaracaoEnumContext ctx, TarbaloParser.CnjtElemContext alvo) {
        int auto = 0;
        for (TarbaloParser.CnjtElemContext elem : ctx.cnjtElem()) {
            if (elem == alvo) return auto;
            if (elem.ATRIBUICAO() != null && elem.expressao() != null) {
                // Tem valor explícito — avaliar expressão inteira
                Integer valor = avaliarExpressaoInt(elem.expressao());
                if (valor != null) {
                    auto = valor;
                } else {
                    auto++;
                }
            } else {
                auto++;
            }
        }
        return auto;
    }

    /** Tenta avaliar uma expressão como inteiro em tempo de compilação. Retorna null se não for possível. */
    private Integer avaliarExpressaoInt(TarbaloParser.ExpressaoContext ctx) {
        if (ctx == null) return null;
        // expressao → expressaoConcatenacao (OU expressaoConcatenacao)*
        // Para constantes inteiras, só a primeira expressaoConcatenacao importa (sem OU)
        if (ctx.expressaoConcatenacao().isEmpty()) return null;
        return avaliarExpressaoConcatenacaoInt(ctx.expressaoConcatenacao(0));
    }

    private Integer avaliarExpressaoConcatenacaoInt(TarbaloParser.ExpressaoConcatenacaoContext ctx) {
        if (ctx == null || ctx.expressaoXor().isEmpty()) return null;
        // Se tem CONCAT (&), não é constante inteira
        if (!ctx.CONCAT().isEmpty()) return null;
        return avaliarExpressaoXorInt(ctx.expressaoXor(0));
    }

    private Integer avaliarExpressaoXorInt(TarbaloParser.ExpressaoXorContext ctx) {
        if (ctx == null || ctx.expressaoE().isEmpty()) return null;
        if (!ctx.XOR().isEmpty()) return null;
        return avaliarExpressaoEInt(ctx.expressaoE(0));
    }

    private Integer avaliarExpressaoEInt(TarbaloParser.ExpressaoEContext ctx) {
        if (ctx == null || ctx.expressaoNegacao().isEmpty()) return null;
        if (!ctx.E().isEmpty()) return null;
        return avaliarExpressaoNegacaoInt(ctx.expressaoNegacao(0));
    }

    private Integer avaliarExpressaoNegacaoInt(TarbaloParser.ExpressaoNegacaoContext ctx) {
        if (ctx == null) return null;
        if (ctx.NAO() != null) return null; // nao é operação inteira
        return avaliarExpressaoRelacionalInt(ctx.expressaoRelacional());
    }

    private Integer avaliarExpressaoRelacionalInt(TarbaloParser.ExpressaoRelacionalContext ctx) {
        if (ctx == null || ctx.expressaoAditiva().isEmpty()) return null;
        if (ctx.operadorRelacional() != null) return null; // comparação não é constante inteira
        return avaliarExpressaoAditivaInt(ctx.expressaoAditiva(0));
    }

    private Integer avaliarExpressaoAditivaInt(TarbaloParser.ExpressaoAditivaContext ctx) {
        if (ctx == null || ctx.expressaoMultiplicativa().isEmpty()) return null;
        Integer resultado = avaliarExpressaoMultiplicativaInt(ctx.expressaoMultiplicativa(0));
        if (resultado == null) return null;
        int idx = 1;
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            Integer proximo = avaliarExpressaoMultiplicativaInt(ctx.expressaoMultiplicativa(idx++));
            if (proximo == null) return null;
            if (op.equals("+")) resultado += proximo;
            else if (op.equals("-")) resultado -= proximo;
            else return null;
        }
        return resultado;
    }

    private Integer avaliarExpressaoMultiplicativaInt(TarbaloParser.ExpressaoMultiplicativaContext ctx) {
        if (ctx == null || ctx.operando().isEmpty()) return null;
        Integer resultado = avaliarOperandoInt(ctx.operando(0));
        if (resultado == null) return null;
        int idx = 1;
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            Integer proximo = avaliarOperandoInt(ctx.operando(idx++));
            if (proximo == null || proximo == 0) return null;
            if (op.equals("*")) resultado *= proximo;
            else if (op.equals("/")) resultado /= proximo;
            else if (op.equals("//")) resultado /= proximo;
            else if (op.equals("%")) resultado %= proximo;
            else return null;
        }
        return resultado;
    }

    private Integer avaliarOperandoInt(TarbaloParser.OperandoContext ctx) {
        if (ctx == null) return null;
        if (ctx.NUM() != null) {
            try { return Integer.parseInt(ctx.NUM().getText()); }
            catch (NumberFormatException e) { return null; }
        }
        if (ctx.MENOS() != null && ctx.operando() != null) {
            Integer val = avaliarOperandoInt(ctx.operando());
            return val != null ? -val : null;
        }
        if (ctx.expressao() != null) {
            return avaliarExpressaoInt(ctx.expressao());
        }
        return null; // variável, chamada, etc. — não avaliável
    }

    @Override
    public String visitAcessoElem(TarbaloParser.AcessoElemContext ctx) {
        List<TerminalNode> ids = ctx.ID();
        String nomeRaiz = ids.get(0).getText();

        // Processar dimensões no primeiro ID: v[0]::campo → v.get(0).campo
        StringBuilder result = new StringBuilder(nomeRaiz);
        for (TarbaloParser.DimensaoContext dim : ctx.dimensao()) {
            if (dim.expressao() != null && !dim.expressao().isEmpty()) {
                result.append(".get(").append(visit(dim.expressao(0))).append(")");
            }
        }

        // Resolver tipo para verificar Erro
        Simbolo sRaiz = tabela.buscarComMemoria(nomeRaiz);
        String tipoAtual = null;
        if (sRaiz != null) {
            tipoAtual = sRaiz.getTipo();
            if (sRaiz.getCategoria() == Simbolo.Categoria.VETOR && !ctx.dimensao().isEmpty()) {
                tipoAtual = sRaiz.getTipoBase();
            }
        }

        // Cadeia de campos: p::endereco::rua → p.endereco.rua
        for (int i = 1; i < ids.size(); i++) {
            String campo = ids.get(i).getText();

            // Último campo é "mensagem" de Erro? → .getMessage()
            if (tipoAtual != null && tipoAtual.equals("Erro")
                && campo.equals("mensagem") && i == ids.size() - 1) {
                result.append(".getMessage()");
                return result.toString();
            }

            result.append(".").append(campo);

            // Resolver tipo do campo para próxima iteração
            if (tipoAtual != null) {
                Simbolo sCampo = tabela.buscarCampo(tipoAtual, campo);
                if (sCampo != null) {
                    tipoAtual = sCampo.getTipo();
                } else {
                    tipoAtual = null;
                }
            }
        }

        return result.toString();
    }

    // ======================================================================
    // REGS (Structs)
    // ======================================================================
    @Override
    public String visitDeclaracaoStruct(TarbaloParser.DeclaracaoStructContext ctx) {
        String nomeStruct = ctx.ID().getText();
        StringBuilder sb = new StringBuilder();
        sb.append("public static class ").append(nomeStruct).append(" {\n");

        // Campos
        List<String> nomesCampos = new ArrayList<>();
        List<String> tiposCampos = new ArrayList<>();
        for (TarbaloParser.DeclaracaoVariavelContext varCtx : ctx.declaracaoVariavel()) {
            String tipoJava = mapearTipo(varCtx.tipoVariavel().getText());
            String nomeCampo = varCtx.ID().getText();
            sb.append("    public ").append(tipoJava).append(" ").append(nomeCampo).append(";\n");
            nomesCampos.add(nomeCampo);
            tiposCampos.add(tipoJava);
        }

        // Construtor com todos os campos
        sb.append("    public ").append(nomeStruct).append("(");
        for (int i = 0; i < nomesCampos.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(tiposCampos.get(i)).append(" ").append(nomesCampos.get(i));
        }
        sb.append(") {\n");
        for (String nomeCampo : nomesCampos) {
            sb.append("        this.").append(nomeCampo).append(" = ").append(nomeCampo).append(";\n");
        }
        sb.append("    }\n");

        // Construtor sem argumentos (default)
        sb.append("    public ").append(nomeStruct).append("() {\n");
        sb.append("    }\n");

        sb.append("}\n");
        regsGerados.append(sb.toString());
        return "";
    }

    @Override
    public String visitChamadaConstrutor(TarbaloParser.ChamadaConstrutorContext ctx) {
        String nomeStruct = ctx.ID().getText();
        StringBuilder args = new StringBuilder();
        if (ctx.argumentos() != null) {
            List<TarbaloParser.ExpressaoContext> exprs = ctx.argumentos().expressao();
            for (int i = 0; i < exprs.size(); i++) {
                if (i > 0) args.append(", ");
                args.append(visit(exprs.get(i)));
            }
        }
        return "new " + nomeStruct + "(" + args.toString() + ")";
    }

    // ======================================================================
    // ======================================================================
    // SONHA/VISH (Try/Catch)
    // ======================================================================
    @Override
    public String visitCmdTente(TarbaloParser.CmdTenteContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("try {\n");
        sb.append(indentar(visit(ctx.bloco()), "    "));
        sb.append("}");
        if (ctx.cmdPegue() != null) {
            TarbaloParser.CmdPegueContext pegue = ctx.cmdPegue();
            String nomeVar = pegue.ID().getText();
            String tipoJava = mapearTipo(pegue.tipoVariavel().getText());
            // Se o tipo não é uma exceção válida, usar Exception como fallback
            if (tipoJava.equals("Object") || tipoJava.equals("int") || tipoJava.equals("double")
                || tipoJava.equals("char") || tipoJava.equals("boolean") || tipoJava.equals("String")
                || tipoJava.equals("void")) {
                tipoJava = "Exception";
            }
            sb.append(" catch (").append(tipoJava).append(" ").append(nomeVar).append(") {\n");
            sb.append(indentar(visit(pegue.bloco()), "    "));
            sb.append("}");
        }
        return sb.toString();
    }

    // ======================================================================
    // ESCOLHA (switch/case)
    // ======================================================================
    @Override
    public String visitCmdEscolha(TarbaloParser.CmdEscolhaContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("switch (").append(visit(ctx.expressao())).append(") {\n");
        for (TarbaloParser.BlocoCasoContext caso : ctx.blocoCaso()) {
            sb.append(visit(caso));
        }
        if (ctx.blocoPadrao() != null) {
            sb.append(visit(ctx.blocoPadrao()));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String visitBlocoCaso(TarbaloParser.BlocoCasoContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("    case ").append(visit(ctx.expressao())).append(": {\n");
        sb.append(indentar(visit(ctx.bloco()), "        "));
        sb.append("        break;\n");
        sb.append("    }\n");
        return sb.toString();
    }

    @Override
    public String visitBlocoPadrao(TarbaloParser.BlocoPadraoContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("    default: {\n");
        sb.append(indentar(visit(ctx.bloco()), "        "));
        sb.append("    }\n");
        return sb.toString();
    }
}