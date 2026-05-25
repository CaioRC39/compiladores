package com.compilador;

import java.util.List;

import com.compilador.tarbalo.TarbaloBaseVisitor;
import com.compilador.tarbalo.TarbaloParser;

public class TarbaloTranspilador extends TarbaloBaseVisitor<String> {

    private TabelaSimbolos tabela;
    private boolean erros = false;
    private StringBuilder funcoesGeradas = new StringBuilder();
    private AnalisadorSemantico analisador; // setado no construtor

    public TarbaloTranspilador(TabelaSimbolos tabela, AnalisadorSemantico analisador) {
        this.tabela = tabela;
        this.analisador = analisador;
    }

    public String transpilar(List<TarbaloParser.BlocoContext> blocos) {
        funcoesGeradas = new StringBuilder();
        StringBuilder principal = new StringBuilder();

        for (TarbaloParser.BlocoContext bloco : blocos) {
            String codigo = visit(bloco);
            if (codigo != null && !codigo.isBlank()) {
                principal.append(indentar(codigo, "        "));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("import java.util.Scanner;\n");
        sb.append("import java.util.Locale;\n\n");
        sb.append("public class ProgramaSaida {\n");
        sb.append("    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);\n\n");
        sb.append(funcoesGeradas.toString());
        sb.append("    public static void main(String[] args) {\n");
        sb.append(principal.toString());
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    public boolean houveErros() { return erros; }

    private void erro(int linha, String msg) {
        System.err.println("Erro semântico (linha " + linha + "): " + msg);
        erros = true;
    }

    private String mapearTipo(String tipoTarbalo) {
        // Extrair o tipo base e os colchetes
        String base = tipoTarbalo.replaceAll("\\[\\]", "");
        String colchetes = tipoTarbalo.substring(base.length());

        String javaBase = switch (base) {
            case "int"    -> "int";
            case "qbd"    -> "double";
            case "txt"    -> "String";
            case "ltr"    -> "char";
            case "lgc"    -> "boolean";
            case "vazio"  -> "void";
            default       -> "Object";
        };
        return javaBase + colchetes;
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
        if (tipoTarbalo == null) return "scanner.next()";
        return switch (tipoTarbalo) {
            case "int"    -> "scanner.nextInt()";
            case "qbd"    -> "scanner.nextDouble()";
            case "lgc"    -> "scanner.nextBoolean()";
            case "ltr"    -> "scanner.next().charAt(0)";
            default       -> "scanner.next()";
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
            String linha = visit(cmdCtx);
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

        if (ctx.ATRIBUICAO() != null) {
            String valor = visit(ctx.expressao());
            return tipoJava + " " + nomeVar + " = " + valor + ";";
        }

        return tipoJava + " " + nomeVar + ";";
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
            // Acesso indexado normal: v[0], mat[1][2]
            StringBuilder sb = new StringBuilder(nome);
            for (TarbaloParser.DimensaoContext d : ctx.dimensao()) {
                sb.append("[").append(visit(d.expressao(0))).append("]");
            }
            return sb.toString();
        }

        // Slice: gera chamada helper sliceNd_tipo(...)
        Simbolo sim = tabela.buscar(nome);
        String tipoBase = (sim != null && sim.getCategoria() == Simbolo.Categoria.VETOR)
            ? sim.getTipoBase() : "int";
        String sufixo = getSufixoTipo(tipoBase);
        int ndims = ctx.dimensao().size();
        StringBuilder args = new StringBuilder(nome);
        for (TarbaloParser.DimensaoContext d : ctx.dimensao()) {
            if (d.PONTOPONTO() != null) {
                args.append(", ").append(visit(d.expressao(0)))
                    .append(", (").append(visit(d.expressao(1))).append(") + 1");
            } else {
                String idx = visit(d.expressao(0));
                args.append(", ").append(idx).append(", (").append(idx).append(") + 1");
            }
        }
        return "slice" + ndims + "d" + sufixo + "(" + args + ")";
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
        TarbaloParser.VariavelContext sel = ctx.variavel();
        String nomeVar = nomeBaseDeVariavel(sel);

        // (verificação de declaração já feita pelo AnalisadorSemântico)

        // Slice do lado esquerdo
        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            String nomeVetor = sel.ID().getText();
            TarbaloParser.DimensaoContext dim = sel.dimensao(0);
            if (dim.PONTOPONTO() != null) {
                String inicio = visit(dim.expressao(0));
                String fim = visit(dim.expressao(1));

                // Verificar se é inicialização literal
                if (ctx.valorAtribuicao().inicializacaoVetor() != null) {
                    String valores = visit(ctx.valorAtribuicao().inicializacaoVetor());
                    Simbolo sim = tabela.buscar(nomeVetor);
                    String tipoBase = sim != null ? sim.getTipoBase() : "int";
                    String tipoJava = mapearTipo(tipoBase);
                    String tempVar = "__temp_" + nomeVetor;
                    return tipoJava + "[] " + tempVar + " = " + valores + ";\n" +
                        "for (int __i = " + inicio + "; __i <= " + fim + "; __i++) {\n" +
                        "    " + nomeVetor + "[__i] = " + tempVar + "[__i - (" + inicio + ")];\n" +
                        "}";
                } else {
                    // Expressão comum (variável, chamada, etc.)
                    String exprJava = visit(ctx.valorAtribuicao().expressao());
                    Simbolo sim = tabela.buscar(nomeVetor);
                    String tipoBase = sim != null ? sim.getTipoBase() : "int";
                    String tipoJava = mapearTipo(tipoBase);
                    String tempVar = "__temp_" + nomeVetor;
                    return tipoJava + "[] " + tempVar + " = " + exprJava + ";\n" +
                        "for (int __i = " + inicio + "; __i <= " + fim + "; __i++) {\n" +
                        "    " + nomeVetor + "[__i] = " + tempVar + "[__i - (" + inicio + ")];\n" +
                        "}";
                }
            }
        }

        // Atribuição normal
        String destino = visit(sel);
        String opJava = "=";
        if (ctx.operadorAtribuicaoComposta() != null) {
            opJava = visit(ctx.operadorAtribuicaoComposta());
        }
        String valor = visit(ctx.valorAtribuicao());
        return destino + " " + opJava + " " + valor + ";";
    }

    @Override
    public String visitValorAtribuicao(TarbaloParser.ValorAtribuicaoContext ctx) {
        if (ctx.expressao() != null) return visit(ctx.expressao());
        if (ctx.inicializacaoVetor() != null) {
            // Em contexto de atribuição, gerar new tipo[]{...} (não {…} que só vale em declaração)
            // Infelizmente não temos o tipo aqui, então geramos um array literal genérico
            // Na prática, inicializacaoVetor só aparece em declaracaoVetor (onde {…} é válido)
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
        TarbaloParser.VariavelContext sel = ctx.variavel();

        // Verificar se é um slice (variavel com alguma dimensão contendo PONTOPONTO)
        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            // leia(v[0..1]) → ler múltiplos valores para a fatia
            String nomeVetor = sel.ID().getText();
            // Consideramos apenas a primeira dimensão com slice (ex.: v[0..1])
            TarbaloParser.DimensaoContext dim = sel.dimensao(0);
            if (dim.PONTOPONTO() != null) {
                String inicio = visit(dim.expressao(0));
                String fim = visit(dim.expressao(1));
                // Determinar o tipo base para usar o scanner correto
                String tipoBase = tabela.obterTipo(nomeVetor);
                if (tipoBase != null && tipoBase.endsWith("[]")) {
                    tipoBase = tipoBase.replace("[]", "");
                }
                String scannerMethod = getScannerMethod(tipoBase);
                // Gera: for (int __i = inicio; __i < fim; __i++) { v[__i] = scanner.nextInt(); }
                StringBuilder sb = new StringBuilder();
                sb.append("for (int __i = ").append(inicio).append("; __i <= ").append(fim).append("; __i++) {\n");
                sb.append("    ").append(nomeVetor).append("[__i] = ").append(scannerMethod).append(";\n");
                sb.append("}");
                return sb.toString();
            }
        }

        // Leitura normal (variável simples ou elemento de vetor)
        String destino = visit(sel);
        String nomeVar = nomeBaseDeVariavel(sel);
        // (verificação de declaração já feita pelo AnalisadorSemântico)

        String tipoTarbalo = nomeVar != null ? tabela.obterTipo(nomeVar) : null;
        if (tipoTarbalo != null && tipoTarbalo.endsWith("[]")) {
            tipoTarbalo = tipoTarbalo.replace("[]", "");
        }
        String scannerMethod = getScannerMethod(tipoTarbalo);
        return destino + " = " + scannerMethod + ";";
    }

    // ======================================================================
    // 7. visitEscrita  (CORRIGIDO — itera todas as expressões)
    //    A gramática permite: escreva(expr1; expr2; ...).
    //    Cada expressão é concatenada uma na outra.
    // ======================================================================
    @Override
    public String visitEscrita(TarbaloParser.EscritaContext ctx) {
        if (ctx.expressao() == null || ctx.expressao().isEmpty()) {
            return "System.out.println();";
        }
        StringBuilder args = new StringBuilder();
        for (int i = 0; i < ctx.expressao().size(); i++) {
            if (i > 0) args.append(" + \" \" + ");
            // Forçar conversão para String com String.valueOf() evita
            // ambiguidade de '+' com char/int
            args.append("String.valueOf(").append(visit(ctx.expressao(i))).append(")");
        }
        return "System.out.println(" + args + ");";
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
    //      → expressaoRelacional → expressaoAditiva → expressaoConcatenacao
    //      → expressaoMultiplicativa → operando
    //
    //  Cada nível sabe exatamente qual operador usar, preservando a
    //  precedência que o ANTLR calculou na árvore.
    // ======================================================================

    /** expressao : expressaoXor (OU expressaoXor)* */
    @Override
    public String visitExpressao(TarbaloParser.ExpressaoContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoXor(0)));
        for (int i = 1; i < ctx.expressaoXor().size(); i++) {
            sb.append(" || ").append(visit(ctx.expressaoXor(i)));
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

            // Descobrir tipo do operando esquerdo para traduzir corretamente
            String tipoEsq = analisador.consultarTipo(ctx.expressaoAditiva(0));

            // Se o tipo é String (txt), traduzir para compareTo/equals
            if (tipoEsq.equals("txt")) {
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
     * expressaoAditiva : expressaoConcatenacao ((MAIS | MENOS) expressaoConcatenacao)*
     *
     * Os tokens de operador ficam nas posições ímpares da lista de filhos.
     */
    @Override
    public String visitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx) {
        StringBuilder sb = new StringBuilder(visit(ctx.expressaoConcatenacao(0)));
        int exprIdx = 1;
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            sb.append(" ").append(op).append(" ")
                    .append(visit(ctx.expressaoConcatenacao(exprIdx++)));
        }
        return sb.toString();
    }

    /**
     * expressaoConcatenacao : expressaoMultiplicativa (CONCAT expressaoMultiplicativa)*
     *
     * CONCAT em Tarbalo é '&' → Java '+' (concatenação de strings).
     * Usa String.valueOf() para garantir concatenação (evitar adição numérica).
     */
    @Override
    public String visitExpressaoConcatenacao(TarbaloParser.ExpressaoConcatenacaoContext ctx) {
        if (!ctx.CONCAT().isEmpty()) {
            StringBuilder sb = new StringBuilder("String.valueOf(" + visit(ctx.expressaoMultiplicativa(0)) + ")");
            for (int i = 1; i < ctx.expressaoMultiplicativa().size(); i++) {
                sb.append(" + String.valueOf(").append(visit(ctx.expressaoMultiplicativa(i))).append(")");
            }
            return sb.toString();
        }
        return visit(ctx.expressaoMultiplicativa(0));
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
            if (op.equals("//")) op = "/";  // DIVINT → divisão inteira Java
            sb.append(" ").append(op).append(" ")
                    .append(visit(ctx.operando(exprIdx++)));
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
        if (ctx.variavel()    != null) return visit(ctx.variavel());
        if (ctx.chamadaFuncao()!= null) return visit(ctx.chamadaFuncao());
        if (ctx.expressao()   != null) return "(" + visit(ctx.expressao()) + ")";
        return "";
    }

    // ======================================================================
    // Operador de Atribuição Composta  (NOVO)
    // ======================================================================
    @Override
    public String visitOperadorAtribuicaoComposta(TarbaloParser.OperadorAtribuicaoCompostaContext ctx) {
        return switch (ctx.getText()) {
            case "+:" -> "+=";
            case "-:" -> "-=";
            case "*:" -> "*=";
            case "/:" -> "/=";
            case "%:" -> "%=";
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
        String destino = visit(ctx.variavel());
        String opJava = "=";
        if (ctx.operadorAtribuicaoComposta() != null) {
            opJava = visit(ctx.operadorAtribuicaoComposta());
        }
        String valor = visit(ctx.valorAtribuicao());
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
        if (!sel.dimensao().isEmpty() && possuiSlice(sel)) {
            // Slice: gerar loop
            String nomeVetor = sel.ID().getText();
            TarbaloParser.DimensaoContext dim = sel.dimensao(0);
            if (dim.PONTOPONTO() != null) {
                String inicio = visit(dim.expressao(0));
                String fim = visit(dim.expressao(1));
                // Usar <= se intervalo for inclusivo (ajuste conforme semântica)
                return "for (int __i = " + inicio + "; __i <= " + fim + "; __i++) {\n" +
                    "    " + nomeVetor + "[__i]" + op + ";\n" +
                    "}";
            }
        }
        // Normal: variável ou elemento de vetor
        return visit(sel) + op;
    }

    @Override
    public String visitIncrementoPonto(TarbaloParser.IncrementoPontoContext ctx) {
        return visit(ctx.incremento()) + ";";
    }

    @Override
    public String visitDecrementoPonto(TarbaloParser.DecrementoPontoContext ctx) {
        return visit(ctx.decremento()) + ";";
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
        String tipoJava = mapearTipo(ctx.tipoVariavel().getText());
        String nomeVar  = ctx.ID().getText();

        StringBuilder colchetesJava = new StringBuilder();  // para o tipo: int[], int[][], etc.

        for (int i = 0; i < ctx.dimensao().size(); i++) {
            colchetesJava.append("[]");
        }

        // Caso 1: inicialização explícita → usa os valores fornecidos
        if (ctx.ATRIBUICAO() != null) {
            if (ctx.valorAtribuicao().inicializacaoVetor() != null) {
                // bond int v[3]: [1; 2; 3].
                String valores = visit(ctx.valorAtribuicao().inicializacaoVetor());
                return tipoJava + colchetesJava + " " + nomeVar + " = " + valores + ";";
            } else {
                // bond int fatia[]: slice1d_int(v; 0; 2).
                String expr = visit(ctx.valorAtribuicao().expressao());
                return tipoJava + colchetesJava + " " + nomeVar + " = " + expr + ";";
            }
        }

        // Caso 2: sem inicialização, aloca com new ou declara sem inicializar
        boolean primeiraDimComTamanho = !ctx.dimensao().isEmpty() && !ctx.dimensao(0).expressao().isEmpty();

        if (primeiraDimComTamanho) {
            // Construir new int[5], new int[5][], new int[5][3], etc.
            StringBuilder alocacao = new StringBuilder("new " + tipoJava);
            for (TarbaloParser.DimensaoContext dimCtx : ctx.dimensao()) {
                if (!dimCtx.expressao().isEmpty())
                    alocacao.append("[").append(visit(dimCtx.expressao(0))).append("]");
                else
                    alocacao.append("[]");
            }
            // O tipoJava + colchetesJava já traz o tipo com colchetes (ex.: "int[]")
            // A alocação já contém as dimensões (ex.: "new int[5]")
            // Portanto, o resultado fica: "int[] v = new int[5];"
            return tipoJava + colchetesJava + " " + nomeVar + " = " + alocacao + ";";
        } else {
            // Nenhuma dimensão tem tamanho → apenas declara a variável (ex.: int[] v;)
            return tipoJava + colchetesJava + " " + nomeVar + ";";
        }
    }

    // ======================================================================
    // 17. Vetores: Inicialização  { expr1, expr2, ... }
    // ======================================================================
    @Override
    public String visitInicializacaoVetor(TarbaloParser.InicializacaoVetorContext ctx) {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < ctx.expressao().size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(ctx.expressao(i)));
        }
        sb.append("}");
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
        String tipoJava = mapearTipo(ctx.tipoVariavel().getText());
        String nomeVar  = ctx.variavel().ID().getText();
        StringBuilder colchetes = new StringBuilder();
        if (!ctx.variavel().dimensao().isEmpty()) {
            for (int i = 0; i < ctx.variavel().dimensao().size(); i++) {
                colchetes.append("[]");
            }
        }
        return tipoJava + colchetes + " " + nomeVar;
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
        Simbolo fun = analisador.obterResolucao(ctx);
        String argumentos = ctx.argumentos() != null ? visit(ctx.argumentos()) : "";

        // Se a função é 'tamanho' ou 'tamanho_<tipo>', tratar especialmente (gera .length)
        if (nomeFunc.equals("tamanho") || nomeFunc.startsWith("tamanho_")) {
            if (ctx.argumentos() == null || ctx.argumentos().expressao().isEmpty()) {
                erro(ctx.start.getLine(), "tamanho requer um argumento");
                return "";
            }
            return visit(ctx.argumentos().expressao(0)) + ".length";
        }

        // Se foi resolvido pelo analisador, use o nome do símbolo (que pode ter sufixo)
        if (fun != null) {
            return fun.getNome() + "(" + argumentos + ")";
        }

        // Fallback (não deve ocorrer)
        return nomeFunc + "(" + argumentos + ")";
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

    // ======================================================================
    // Despachante genérico para Declarações
    // ======================================================================
    @Override
    public String visitDeclaracao(TarbaloParser.DeclaracaoContext ctx) {
        if (ctx.declaracaoVariavel() != null) return visit(ctx.declaracaoVariavel());
        if (ctx.declaracaoVetor()    != null) return visit(ctx.declaracaoVetor());
        if (ctx.declaracaoFuncao()   != null) return visit(ctx.declaracaoFuncao());
        return "";
    }
}