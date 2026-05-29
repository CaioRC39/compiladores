package com.compilador;

import com.compilador.tarbalo.TarbaloBaseListener;
import com.compilador.tarbalo.TarbaloParser;
import com.compilador.tarbalo.TarbaloBaseVisitor;
import java.util.*;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela;

    // Para controle de contexto (funções, laços)
    private Deque<String> pilhaRetornos = new ArrayDeque<>(); // tipo de retorno da função atual
    private Map<ParseTree, Simbolo> mapaResolucao = new HashMap<>();
    private Map<ParseTree, String> mapaTiposExpressao = new HashMap<>();
    private int nivelLaco = 0;
    private boolean dentroDeFuncao = false;
    private int profundidadeEstruturaControle = 0;

    // Rastreamento de variáveis inicializadas
    private Deque<Set<String>> pilhaVariaveisInicializadas = new ArrayDeque<>();

    private boolean erros = false;

    public boolean houveErros() { return erros; }

    public AnalisadorSemantico(TabelaSimbolos tabela) {
        this.tabela = tabela;
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }

    public TabelaSimbolos getTabela() {
        return tabela;
    }

    public Simbolo obterResolucao(ParseTree ctx) {
        return mapaResolucao.get(ctx);
    }

    /**
     * Consulta o tipo inferido de uma expressão.
     * Usado pelo Transpilador para saber como traduzir operadores relacionais.
     */
    public String consultarTipo(ParseTree ctx) {
        return mapaTiposExpressao.getOrDefault(ctx, "desconhecido");
    }

    /* ---------------------------------------------------------------------
     * ESCOPOS
     * --------------------------------------------------------------------- */
    @Override
    public void enterCmdBloco(TarbaloParser.CmdBlocoContext ctx) {
        tabela.abrirEscopo();
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }

    @Override
    public void exitCmdBloco(TarbaloParser.CmdBlocoContext ctx) {
        tabela.fecharEscopo();
        if (pilhaVariaveisInicializadas.size() > 1) {
            pilhaVariaveisInicializadas.pop();
        }
    }

    @Override
    public void enterBloco(TarbaloParser.BlocoContext ctx) { }

    @Override
    public void exitBloco(TarbaloParser.BlocoContext ctx) { }

    /* ---------------------------------------------------------------------
     * DECLARAÇÃO DE VARIÁVEIS
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx) {
        String tipoVar = ctx.tipoVariavel().getText();
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();

        if (tabela.existeNoEscopoAtual(nomeVar)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): A variável '" + nomeVar + "' já foi declarada neste escopo!");
            return;
        }

        tabela.adicionar(new Simbolo(nomeVar, tipoVar));

        // Verifica tipo da inicialização, se houver
        if (ctx.ATRIBUICAO() != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            String tipoExpr = avaliador.visit(ctx.expressao());

            if (!saoCompativeis(tipoVar, tipoExpr) && !tipoExpr.equals("erro")) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na inicialização. A variável '" + nomeVar + "' é do tipo '" + tipoVar + "', mas recebeu um valor do tipo '" + tipoExpr + "'.");
            }
            // Marcar como inicializada
            marcarInicializada(nomeVar);
        }
    }

    /* ---------------------------------------------------------------------
     * DECLARAÇÃO DE VETOR
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoVetor(TarbaloParser.DeclaracaoVetorContext ctx) {
        String tipoBase = ctx.tipoVariavel().getText();
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();
        if (tabela.existeNoEscopoAtual(nomeVar)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): O vetor '" + nomeVar + "' já foi declarado neste escopo!");
            return;
        }

        // Obter todas as dimensões
        int dim = ctx.dimensao().size();
        List<Integer> tamanhos = new ArrayList<>();
        boolean dinamico = false;

        for (TarbaloParser.DimensaoContext dimCtx : ctx.dimensao()) {
            if (dimCtx.expressao().isEmpty()) {
                dinamico = true;
                tamanhos.add(null);   // dimensão dinâmica (tamanho desconhecido)
            } else {
                Integer tam = extrairNumero(dimCtx.expressao(0));
                if (tam != null) {
                    if (tam < 0) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + linha + "): Tamanho de vetor não pode ser negativo, mas recebeu " + tam + ".");
                    }
                    tamanhos.add(tam);
                } else {
                    dinamico = true;
                    tamanhos.add(null);
                }
            }
        }

        // Verificar mistura de dimensões estáticas e dinâmicas
        if (dim > 1) {
            boolean temEstatica = tamanhos.stream().anyMatch(t -> t != null);
            boolean temDinamica = tamanhos.stream().anyMatch(t -> t == null);
            if (temEstatica && temDinamica) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linha + "): Vetor '" + nomeVar
                    + "' não pode misturar dimensões estáticas e dinâmicas. Use todas com tamanho ou todas sem tamanho.");
                return;
            }
        }

        Simbolo simbolo = new Simbolo(nomeVar, tipoBase, dim, dinamico, tamanhos);
        tabela.adicionar(simbolo);

        // Verifica inicialização do vetor
        TarbaloParser.InicializacaoVetorContext initVetor =
            ctx.valorAtribuicao() != null ? ctx.valorAtribuicao().inicializacaoVetor() : null;

        if (initVetor != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            for (TarbaloParser.ElemVetorContext elemCtx : initVetor.elemVetor()) {
                if (elemCtx.inicializacaoVetor() != null) {
                    // Elemento aninhado: validar elementos internos contra tipoBase
                    for (TarbaloParser.ElemVetorContext innerElem : elemCtx.inicializacaoVetor().elemVetor()) {
                        if (innerElem.expressao() != null) {
                            String tipoElem = avaliador.visit(innerElem.expressao());
                            if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                                erros = true;
                                System.err.println("Erro Semântico (Linha " + linha + "): Elemento do vetor '" + nomeVar + "' de tipo incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                                break;
                            }
                        }
                    }
                } else if (elemCtx.expressao() != null) {
                    // Elemento escalar
                    String tipoElem = avaliador.visit(elemCtx.expressao());
                    if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + linha + "): Elemento do vetor '" + nomeVar + "' de tipo incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                        break;
                    }
                }
            }
        }

        // Validar inicialização por expressão (não literal): tipo da expressão vs. tipo do vetor
        if (ctx.valorAtribuicao() != null && ctx.valorAtribuicao().expressao() != null && initVetor == null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            String tipoExpr = avaliador.visit(ctx.valorAtribuicao().expressao());
            String tipoVetor = tipoBase + "[]".repeat(dim);
            if (!saoCompativeis(tipoVetor, tipoExpr) && !tipoExpr.equals("erro") && !tipoExpr.equals("desconhecido")) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na inicialização do vetor '" + nomeVar + "'. Esperado '" + tipoVetor + "', mas recebeu '" + tipoExpr + "'.");
            }
        }

        if (initVetor != null && !tamanhos.isEmpty() && tamanhos.get(0) != null) {
            int tamDeclarado = tamanhos.get(0);
            int tamInicial = initVetor.elemVetor().size();
            if (tamInicial > tamDeclarado) {
                System.err.println("Erro Semântico (Linha " + linha +
                    "): O vetor '" + nomeVar + "' foi declarado com tamanho " + tamDeclarado +
                    " mas a inicialização contém " + tamInicial + " elementos.");
                erros = true;
            }
            // Verificar sub-dimensões para vetores 2D+
            if (dim >= 2 && tamanhos.size() > 1 && tamanhos.get(1) != null) {
                int tamSubDeclarado = tamanhos.get(1);
                for (TarbaloParser.ElemVetorContext elem : initVetor.elemVetor()) {
                    if (elem.inicializacaoVetor() != null) {
                        int tamSubInicial = elem.inicializacaoVetor().elemVetor().size();
                        if (tamSubInicial > tamSubDeclarado) {
                            System.err.println("Erro Semântico (Linha " + linha +
                                "): Sub-vetor do vetor '" + nomeVar + "' declarado com tamanho " + tamSubDeclarado +
                                " mas contém " + tamSubInicial + " elementos.");
                            erros = true;
                        }
                    }
                }
            }
        }
    }

    /* ---------------------------------------------------------------------
     * DECLARAÇÃO DE CNJT (ENUM)
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoEnum(TarbaloParser.DeclaracaoEnumContext ctx) {
        String tipoValor = ctx.tipoVariavel().getText();
        String nomeCnjt = ctx.ID().getText();
        int linha = ctx.start.getLine();

        // Validar tipo do cnjt
        if (!tipoValor.equals("int") && !tipoValor.equals("qbd") && !tipoValor.equals("txt") &&
            !tipoValor.equals("ltr") && !tipoValor.equals("lgc")) {
            // Pode ser um nome de outro cnjt — verificar
            Simbolo sTipo = tabela.buscar(tipoValor);
            if (sTipo == null || sTipo.getCategoria() != Simbolo.Categoria.CNJT) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo inválido '" + tipoValor + "' para cnjt. Deve ser int, qbd, txt, ltr ou lgc.");
                return;
            }
        }

        // Verificar nome único
        if (tabela.existeNoEscopoAtual(nomeCnjt)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Já existe uma declaração com o nome '" + nomeCnjt + "' neste escopo.");
            return;
        }

        // Coletar e validar elementos
        Set<String> elems = new HashSet<>();
        int autoValor = 0;
        for (TarbaloParser.CnjtElemContext elemCtx : ctx.cnjtElem()) {
            String nomeElem = elemCtx.ID().getText();
            int linhaElem = elemCtx.start.getLine();

            if (elems.contains(nomeElem)) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linhaElem + "): Elemento duplicado '" + nomeElem + "' no cnjt '" + nomeCnjt + "'.");
                continue;
            }
            elems.add(nomeElem);

            if (elemCtx.ATRIBUICAO() != null && elemCtx.expressao() != null) {
                // Validar tipo do valor
                AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linhaElem);
                String tipoExpr = avaliador.visit(elemCtx.expressao());
                if (!tipoExpr.equals("erro") && !tipoExpr.equals("desconhecido") && !tipoExpr.equals(tipoValor)) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linhaElem + "): Elemento '" + nomeElem + "' tem valor do tipo '" + tipoExpr + "', mas o cnjt espera '" + tipoValor + "'.");
                }
            } else {
                // Valor implícito — só permitido para int
                if (!tipoValor.equals("int")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linhaElem + "): Elemento '" + nomeElem + "' sem valor explícito é permitido apenas para cnjt do tipo int.");
                    continue;
                }
                autoValor++;
            }

            // Registrar como símbolo
            tabela.adicionar(Simbolo.criarCnjtElem(nomeElem, tipoValor, nomeCnjt));
        }

        // Registrar o cnjt
        tabela.adicionar(Simbolo.criarCnjt(nomeCnjt, tipoValor));
    }

    /* ---------------------------------------------------------------------
     * REG (Struct simples)
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoStruct(TarbaloParser.DeclaracaoStructContext ctx) {
        String nomeStruct = ctx.ID().getText();
        int linha = ctx.start.getLine();

        // Verificar nome único
        if (tabela.existeNoEscopoAtual(nomeStruct)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Já existe uma declaração com o nome '" + nomeStruct + "' neste escopo.");
            return;
        }

        // Registrar o struct primeiro (para permitir auto-referência)
        tabela.adicionar(Simbolo.criarRegs(nomeStruct));

        // Validar e registrar campos
        Set<String> campos = new HashSet<>();
        for (TarbaloParser.DeclaracaoVariavelContext varCtx : ctx.declaracaoVariavel()) {
            String tipoCampo = varCtx.tipoVariavel().getText();
            String nomeCampo = varCtx.ID().getText();
            int linhaCampo = varCtx.start.getLine();

            // Validar que o tipo do campo é válido
            if (!tipoCampo.equals("int") && !tipoCampo.equals("qbd") && !tipoCampo.equals("txt") &&
                !tipoCampo.equals("ltr") && !tipoCampo.equals("lgc") && !tipoCampo.equals("Erro")) {
                Simbolo sTipo = tabela.buscar(tipoCampo);
                if (sTipo == null || (sTipo.getCategoria() != Simbolo.Categoria.CNJT &&
                                      sTipo.getCategoria() != Simbolo.Categoria.REGS)) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linhaCampo + "): Tipo inválido '" + tipoCampo + "' para campo do struct.");
                    continue;
                }
            }

            if (campos.contains(nomeCampo)) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + linhaCampo + "): Campo duplicado '" + nomeCampo + "' no struct '" + nomeStruct + "'.");
                continue;
            }
            campos.add(nomeCampo);

            // Registrar como campo do struct
            tabela.adicionar(Simbolo.criarCampo(nomeCampo, tipoCampo, nomeStruct));
        }
    }

    /* ---------------------------------------------------------------------
     * SONHA/VISH (TRY/CATCH)
     * --------------------------------------------------------------------- */
    @Override
    public void enterCmdTente(TarbaloParser.CmdTenteContext ctx) {
        profundidadeEstruturaControle++;
        tabela.abrirEscopo();
    }

    @Override
    public void exitCmdTente(TarbaloParser.CmdTenteContext ctx) {
        if (ctx.cmdPegue() == null) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): 'sonha' deve ter um bloco 'deuruim' associado.");
        }
        profundidadeEstruturaControle--;
        tabela.fecharEscopo();
    }

    @Override
    public void enterCmdPegue(TarbaloParser.CmdPegueContext ctx) {
        String tipoExcecao = ctx.tipoVariavel().getText();
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();

        if (!tipoExcecao.equals("Erro")) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Apenas o tipo 'Erro' pode ser capturado com 'pegue', mas recebeu '" + tipoExcecao + "'.");
        }

        // Abrir escopo para o bloco pegue e adicionar a variável de exceção
        tabela.abrirEscopo();
        Simbolo varExcecao = new Simbolo(nomeVar, "Erro");
        tabela.adicionar(varExcecao);
        marcarInicializada(nomeVar);
    }

    @Override
    public void exitCmdPegue(TarbaloParser.CmdPegueContext ctx) {
        tabela.fecharEscopo();
    }

    // Pilha para rastrear tipo da expressão do escolha (para validar casos)
    private Deque<String> pilhaTipoEscolha = new ArrayDeque<>();
    // Pilha para rastrear valores de casos já usados (para detectar duplicatas)
    private Deque<Set<String>> pilhaCasosUsados = new ArrayDeque<>();

    @Override
    public void enterCmdEscolha(TarbaloParser.CmdEscolhaContext ctx) {
        // Avaliar expressão e empilhar tipo ANTES de processar casos (bottom-up)
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoExpr = avaliador.visit(ctx.expressao());
        // Validar que o tipo é switch-compatível (int, txt, ltr, cnjt)
        if (!tipoExpr.equals("erro") && !tipoExpr.equals("desconhecido") &&
            !tipoExpr.equals("int") && !tipoExpr.equals("txt") && !tipoExpr.equals("ltr")) {
            // Verificar se é um cnjt
            Simbolo sTipo = tabela.buscar(tipoExpr);
            if (sTipo == null || sTipo.getCategoria() != Simbolo.Categoria.CNJT) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Tipo incompatível com 'escolha'. Esperado int, txt, ltr ou cnjt, encontrado '" + tipoExpr + "'.");
            }
        }
        pilhaTipoEscolha.push(tipoExpr);
        pilhaCasosUsados.push(new HashSet<>());
    }

    @Override
    public void exitBlocoCaso(TarbaloParser.BlocoCasoContext ctx) {
        if (pilhaTipoEscolha.isEmpty()) return;
        String tipoEscolha = pilhaTipoEscolha.peek();
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoCaso = avaliador.visit(ctx.expressao());
        if (!tipoEscolha.equals("erro") && !tipoCaso.equals("erro") &&
            !tipoCaso.equals("desconhecido") && !saoCompativeis(tipoEscolha, tipoCaso)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                "): Tipo incompatível em 'caso'. Esperado '" + tipoEscolha + "', encontrado '" + tipoCaso + "'.");
            erros = true;
        }

        // Validar que caso é uma constante (literal ou acesso a cnjt válido)
        String textoExpr = ctx.expressao().getText();
        boolean ehAcessoCnjt = false;
        if (textoExpr.contains("::")) {
            String[] partes = textoExpr.split("::", 2);
            if (partes.length == 2) {
                Simbolo sCnjt = tabela.buscar(partes[0]);
                Simbolo sElem = tabela.buscar(partes[1]);
                ehAcessoCnjt = sCnjt != null && sCnjt.getCategoria() == Simbolo.Categoria.CNJT
                    && sElem != null && sElem.getCategoria() == Simbolo.Categoria.CNJT_ELEM
                    && sElem.getTipoCnjt().equals(partes[0]);
            }
        }
        boolean ehConstante = textoExpr.matches("-?\\d+") ||           // NUM
                              textoExpr.matches("-?\\d+,\\d+") ||     // NUMDEC
                              textoExpr.matches("\"[^\"]*\"") ||      // STRING
                              textoExpr.matches("'.'") ||             // CHAR
                              textoExpr.equals("VDD") ||              // booleano
                              textoExpr.equals("FAKE") ||             // booleano
                              ehAcessoCnjt;                           // acesso cnjt válido
        if (!ehConstante) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                "): Expressão em 'caso' deve ser uma constante (literal ou acesso a cnjt).");
            erros = true;
        }

        // Validar caso duplicado
        if (!pilhaCasosUsados.isEmpty()) {
            Set<String> casosUsados = pilhaCasosUsados.peek();
            if (!casosUsados.add(textoExpr)) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Caso duplicado '" + textoExpr + "'.");
                erros = true;
            }
        }
    }

    @Override
    public void exitCmdEscolha(TarbaloParser.CmdEscolhaContext ctx) {
        // Desempilhar tipo ao sair do escolha
        if (!pilhaTipoEscolha.isEmpty()) pilhaTipoEscolha.pop();
        if (!pilhaCasosUsados.isEmpty()) pilhaCasosUsados.pop();
    }

    /* ---------------------------------------------------------------------
     * EXPRESSÕES RELACIONAIS — popular mapa de tipos para o Transpilador
     * --------------------------------------------------------------------- */
    @Override
    public void exitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx) {
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String esq = avaliador.visit(ctx.expressaoAditiva(0));
        mapaTiposExpressao.put(ctx.expressaoAditiva(0), esq);

        if (ctx.operadorRelacional() != null && ctx.expressaoAditiva().size() > 1) {
            String dir = avaliador.visit(ctx.expressaoAditiva(1));
            mapaTiposExpressao.put(ctx.expressaoAditiva(1), dir);

            String op = ctx.operadorRelacional().getText();
            int linha = ctx.start.getLine();

            // Verificar compatibilidade de tipos
            if (!esq.equals("erro") && !dir.equals("erro") &&
                !saoCompativeisRelacionais(esq, dir)) {
                System.err.println("Erro Semântico (Linha " + linha +
                    "): Tipos incompatíveis na comparação ('" + esq + "' com '" + dir + "').");
                erros = true;
            }

            // Operadores de ordenação: inválidos para lgc
            boolean ehOrdenacao = op.equals("<") || op.equals(">") ||
                                  op.equals("<=") || op.equals(">=");
            if (ehOrdenacao && !esq.equals("erro") && !dir.equals("erro")) {
                if (esq.equals("lgc") || dir.equals("lgc")) {
                    System.err.println("Erro Semântico (Linha " + linha +
                        "): Operador '" + op + "' não é válido para tipo lógico (lgc).");
                    erros = true;
                }
            }
        }
    }

    /* ---------------------------------------------------------------------
     * ATRIBUIÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void exitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        // Validar operador composto (:+ :- :* :/ :%)
        if (ctx.operadorAtribuicaoComposta() != null) {
            String op = ctx.operadorAtribuicaoComposta().getText();
            AvaliadorDeTipos avOp = new AvaliadorDeTipos(ctx.start.getLine());
            String tipoExprOp = avOp.visit(ctx.valorAtribuicao().expressao());
            boolean exprNumerica = tipoExprOp.equals("int") || tipoExprOp.equals("qbd") || tipoExprOp.equals("ltr");
            if (!tipoExprOp.equals("erro") && !tipoExprOp.equals("desconhecido")) {
                if ((op.equals(":+") || op.equals(":-")) && !exprNumerica && !tipoExprOp.equals("txt")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Operador '" + op + "' requer tipo numérico ou 'txt', encontrado '" + tipoExprOp + "'.");
                } else if ((op.equals(":*") || op.equals(":/") || op.equals(":%")) && !exprNumerica) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Operador '" + op + "' requer tipo numérico, encontrado '" + tipoExprOp + "'.");
                }
            }
        }
        if (ctx.variavel() != null) {
            verificarAtribuicao(ctx.variavel(), ctx.valorAtribuicao(), ctx.start.getLine());
        }
        // Validação de tipo para atribuição a campo de struct (cadeia recursiva)
        if (ctx.acessoElem() != null) {
            TarbaloParser.AcessoElemContext aec = ctx.acessoElem();
            List<TerminalNode> ids = aec.ID();
            String nomeRaiz = ids.get(0).getText();
            Simbolo sRaiz = tabela.buscar(nomeRaiz);
            if (sRaiz != null) {
                String tipoAtual = sRaiz.getTipo();
                if (sRaiz.getCategoria() == Simbolo.Categoria.VETOR && !aec.dimensao().isEmpty()) {
                    tipoAtual = sRaiz.getTipoBase();
                }

                // Caminhar pela cadeia até o último campo
                for (int i = 1; i < ids.size(); i++) {
                    String campo = ids.get(i).getText();
                    Simbolo sStruct = tabela.buscar(tipoAtual);
                    if (sStruct == null || sStruct.getCategoria() != Simbolo.Categoria.REGS) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                            "): '" + tipoAtual + "' não é um registro.");
                        break;
                    }
                    Simbolo sCampo = tabela.buscarCampo(tipoAtual, campo);
                    if (sCampo == null) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                            "): Campo '" + campo + "' não encontrado no struct '" + tipoAtual + "'.");
                        break;
                    }
                    // Se é o último campo, validar tipo do valor
                    if (i == ids.size() - 1) {
                        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
                        String tipoValor = avaliador.visit(ctx.valorAtribuicao().expressao());
                        if (!tipoValor.equals("erro") && !tipoValor.equals("desconhecido") &&
                            !saoCompativeis(sCampo.getTipo(), tipoValor)) {
                            erros = true;
                            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                                "): Tipo incompatível para campo '" + campo + "'. Esperado '" +
                                sCampo.getTipo() + "', encontrado '" + tipoValor + "'.");
                        }
                    } else {
                        tipoAtual = sCampo.getTipo();
                    }
                }
            }
        }
    }

    @Override
    public void exitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx) {
        verificarAtribuicao(ctx.variavel(), ctx.valorAtribuicao(), ctx.start.getLine());
    }

    // método auxiliar reutilizado em exitAtribuicao e exitAtribuicaoPara
    private void verificarAtribuicao(TarbaloParser.VariavelContext sel,
                                    TarbaloParser.ValorAtribuicaoContext valor,
                                    int linha) {
        String nomeVar = obterNome(sel);
        if (nomeVar == null) return;

        if (!tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + linha + "): Tentativa de atribuição em variável não declarada: '" + nomeVar + "'");
            erros = true;
            return;
        }

        Simbolo simbolo = tabela.buscar(nomeVar);

        if (simbolo.getCategoria() == Simbolo.Categoria.FUNCAO) {
            System.err.println("Erro Semântico (Linha " + linha + "): Não é possível atribuir a uma função.");
            erros = true;
            return;
        }

        // Vetor inteiro sem indices não pode receber atribuição (exceto slice)
        if (simbolo.getCategoria() == Simbolo.Categoria.VETOR && sel.dimensao().isEmpty() && !contemSlice(sel)) {
            System.err.println("Erro Semântico (Linha " + linha + "): Não é possível atribuir a um vetor inteiro sem índices.");
            erros = true;
            return;
        }

        // Se o valor à direita é uma inicialização de vetor
        if (valor.inicializacaoVetor() != null) {
            // Só é permitido se o lado esquerdo for um slice
            if (!sel.dimensao().isEmpty() && contemSlice(sel)) {
                String tipoBase = simbolo.getTipoBase();  // tipo do elemento
                AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
                for (TarbaloParser.ElemVetorContext elemCtx : valor.inicializacaoVetor().elemVetor()) {
                    if (elemCtx.expressao() != null) {
                        String tipoElem = avaliador.visit(elemCtx.expressao());
                        if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                            System.err.println("Erro Semântico (Linha " + linha + "): Elemento da inicialização incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                            erros = true;
                            return;
                        }
                    }
                }
                return;
            } else {
                System.err.println("Erro Semântico (Linha " + linha + "): Inicialização de vetor só pode ser usada em atribuições a slices.");
                erros = true;
                return;
            }
        }

        // Validação de limites para acesso indexado em vetores com tamanho conhecido
        if (!sel.dimensao().isEmpty() && !contemSlice(sel)) {
            List<Integer> tamanhosVetor = simbolo.getTamanhos();
            int dimIdx = 0;
            for (TarbaloParser.DimensaoContext dim : sel.dimensao()) {
                if (dim.expressao().isEmpty()) break;
                if (tamanhosVetor != null && dimIdx < tamanhosVetor.size() && tamanhosVetor.get(dimIdx) != null) {
                    Integer idxLiteral = extrairNumero(dim.expressao(0));
                    if (idxLiteral != null) {
                        if (idxLiteral < 0 || idxLiteral >= tamanhosVetor.get(dimIdx)) {
                            System.err.println("Erro Semântico (Linha " + linha + "): Índice " + idxLiteral
                                + " fora dos limites do vetor '" + nomeVar + "' (tamanho: " + tamanhosVetor.get(dimIdx) + ").");
                            erros = true;
                        }
                    }
                }
                dimIdx++;
            }
        }

        // Determinar tipo esperado do destino
        String tipoVar;
        if (!sel.dimensao().isEmpty() && !contemSlice(sel)) {
            tipoVar = simbolo.getTipoBase();   // acesso indexado normal
        } else if (!sel.dimensao().isEmpty() && contemSlice(sel)) {
            tipoVar = simbolo.getTipo();       // slice → tipo completo do vetor
        } else {
            tipoVar = simbolo.getTipo();
        }

        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
        String tipoExpr = avaliador.visit(valor.expressao());

        boolean erroTipo = !saoCompativeis(tipoVar, tipoExpr) && !tipoExpr.equals("erro");
        if (erroTipo) {
            System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na atribuição. A variável '" + nomeVar + "' é do tipo '" + tipoVar + "', mas a expressão resulta em '" + tipoExpr + "'.");
            erros = true;
        }

        // Marcar como inicializada apenas se não houve erro de tipo
        if (!erroTipo) marcarInicializada(nomeVar);
    }

    /* ---------------------------------------------------------------------
     * RASTREAMENTO DE VARIÁVEIS INICIALIZADAS
     * --------------------------------------------------------------------- */

    /** Marca uma variável como inicializada no escopo atual. */
    private void marcarInicializada(String nomeVar) {
        if (!pilhaVariaveisInicializadas.isEmpty()) {
            pilhaVariaveisInicializadas.peek().add(nomeVar);
        }
    }

    /** Verifica se uma variável foi inicializada em algum escopo acessível. */
    private boolean isInicializada(String nomeVar) {
        for (Set<String> escopo : pilhaVariaveisInicializadas) {
            if (escopo.contains(nomeVar)) return true;
        }
        return false;
    }

    @Override
    public void enterIncremento(TarbaloParser.IncrementoContext ctx) {
        String nomeVar = obterNome(ctx.variavel());
        int linha = ctx.start.getLine();
        if (nomeVar == null) return;

        Simbolo s = tabela.buscar(nomeVar);
        if (s == null) return;

        // Proibir vetor inteiro
        if (s.getCategoria() == Simbolo.Categoria.VETOR && ctx.variavel().dimensao().isEmpty()) {
            System.err.println("Erro Semântico (Linha " + linha + "): Não é permitido incrementar um vetor inteiro.");
            erros = true;
            return;
        }

        // Verificar tipo numérico
        String tipo = s.getTipoBase();
        if (!tipo.equals("int") && !tipo.equals("qbd") && !tipo.equals("ltr")) {
            System.err.println("Erro Semântico (Linha " + linha + "): Operador '++' não aplicável ao tipo '" + tipo + "'.");
            erros = true;
        }
        // ++ inicializa a variável
        marcarInicializada(nomeVar);
    }

    @Override
    public void enterDecremento(TarbaloParser.DecrementoContext ctx) {
        String nomeVar = obterNome(ctx.variavel());
        int linha = ctx.start.getLine();
        if (nomeVar == null) return;

        Simbolo s = tabela.buscar(nomeVar);
        if (s == null) return;

        // Proibir vetor inteiro
        if (s.getCategoria() == Simbolo.Categoria.VETOR && ctx.variavel().dimensao().isEmpty()) {
            System.err.println("Erro Semântico (Linha " + linha + "): Não é permitido decrementar um vetor inteiro.");
            erros = true;
            return;
        }

        // Verificar tipo numérico
        String tipo = s.getTipoBase(); // se for vetor, pega o tipo base; se escalar, é o próprio tipo
        if (!tipo.equals("int") && !tipo.equals("qbd") && !tipo.equals("ltr")) {
            System.err.println("Erro Semântico (Linha " + linha + "): Operador '--' não aplicável ao tipo '" + tipo + "'.");
            erros = true;
        }
        // -- inicializa a variável
        marcarInicializada(nomeVar);
    }

    /* ---------------------------------------------------------------------
     * LEITURA
     * --------------------------------------------------------------------- */
    @Override
    public void enterLeitura(TarbaloParser.LeituraContext ctx) {
        // Leitura para campo de struct: espia(turma[0]::nome). — cadeia recursiva
        if (ctx.acessoElem() != null) {
            TarbaloParser.AcessoElemContext aec = ctx.acessoElem();
            List<TerminalNode> ids = aec.ID();
            String nomeRaiz = ids.get(0).getText();
            int linha = ctx.start.getLine();

            Simbolo sRaiz = tabela.buscar(nomeRaiz);
            if (sRaiz == null) {
                System.err.println("Erro Semântico (Linha " + linha + "): Variável '" + nomeRaiz + "' não foi declarada.");
                erros = true;
                return;
            }

            // CNJT não pode ser lido
            if (sRaiz.getCategoria() == Simbolo.Categoria.CNJT) {
                System.err.println("Erro Semântico (Linha " + linha + "): Não é possível ler em uma constante de enum.");
                erros = true;
                return;
            }

            // Resolver tipo inicial
            String tipoAtual = sRaiz.getTipo();
            if (sRaiz.getCategoria() == Simbolo.Categoria.VETOR && !aec.dimensao().isEmpty()) {
                tipoAtual = sRaiz.getTipoBase();
            }

            // Caminhar pela cadeia de campos
            for (int i = 1; i < ids.size(); i++) {
                String campo = ids.get(i).getText();

                if (tipoAtual.equals("Erro")) {
                    // Erro built-in: só pode ler "mensagem"
                    if (!campo.equals("mensagem")) {
                        System.err.println("Erro Semântico (Linha " + linha + "): 'Erro' não tem campo '" + campo + "'.");
                        erros = true;
                    }
                    return;
                }

                Simbolo sStruct = tabela.buscar(tipoAtual);
                if (sStruct == null || sStruct.getCategoria() != Simbolo.Categoria.REGS) {
                    System.err.println("Erro Semântico (Linha " + linha + "): '" + tipoAtual + "' não é um registro.");
                    erros = true;
                    return;
                }

                Simbolo sCampo = tabela.buscarCampo(tipoAtual, campo);
                if (sCampo == null) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Campo '" + campo + "' não encontrado no struct '" + tipoAtual + "'.");
                    erros = true;
                    return;
                }

                tipoAtual = sCampo.getTipo();
            }
            return;
        }

        String nomeVar = obterNome(ctx.variavel());
        if (nomeVar != null) {
            Simbolo s = tabela.buscar(nomeVar);
            // proibir leitura de vetor inteiro (ex.: leia(v))
            if (s != null && s.getCategoria() == Simbolo.Categoria.VETOR &&
                ctx.variavel().dimensao().isEmpty()) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Não é permitido ler um vetor inteiro. Use índices.");
                erros = true;
                return;
            }
            // verificação de variável declarada (já existente)
            if (!tabela.existe(nomeVar)) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Variável '" + nomeVar + "' usada no comando 'leia' não foi declarada!");
                erros = true;
            } else {
                // Validação de limites para acesso indexado em leitura
                if (s != null && s.getCategoria() == Simbolo.Categoria.VETOR &&
                    !ctx.variavel().dimensao().isEmpty()) {
                    List<Integer> tamanhosVetor = s.getTamanhos();
                    int dimIdx = 0;
                    for (TarbaloParser.DimensaoContext dim : ctx.variavel().dimensao()) {
                        if (dim.expressao().isEmpty()) break;
                        if (tamanhosVetor != null && dimIdx < tamanhosVetor.size() && tamanhosVetor.get(dimIdx) != null) {
                            Integer idxLiteral = extrairNumero(dim.expressao(0));
                            if (idxLiteral != null) {
                                if (idxLiteral < 0 || idxLiteral >= tamanhosVetor.get(dimIdx)) {
                                    System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Índice " + idxLiteral
                                        + " fora dos limites do vetor '" + nomeVar + "' (tamanho: " + tamanhosVetor.get(dimIdx) + ").");
                                    erros = true;
                                }
                            }
                        }
                        dimIdx++;
                    }
                }
                // leia inicializa a variável
                marcarInicializada(nomeVar);
            }
        }
    }

    /* ---------------------------------------------------------------------
     * USO DE VARIÁVEIS EM EXPRESSÕES
     * --------------------------------------------------------------------- */
    @Override
    public void enterOperando(TarbaloParser.OperandoContext ctx) {
        // Verificação de variável declarada é feita pelo AvaliadorDeTipos.visitOperando
        // para evitar duplicação de mensagens de erro.
    }

    /* ---------------------------------------------------------------------
     * FUNÇÕES
     * --------------------------------------------------------------------- */
    // Flag para indicar se a função atual foi aceita (enter não retornou antecipadamente)
    private boolean funcaoAtualAceita = false;

    @Override
    public void enterDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        String nome = ctx.ID().getText();
        int linha = ctx.start.getLine();
        funcaoAtualAceita = false;

        // Proibir declaração de função dentro de estruturas de controle
        if (profundidadeEstruturaControle > 0) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Não é permitido declarar funções dentro de estruturas de controle.");
            return;
        }

        funcaoAtualAceita = true;

        if (tabela.existeNoEscopoAtual(nome)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome + "' já declarada neste escopo.");
            // continua para evitar mais erros, mas o símbolo antigo permanece
        }

        String tipoRetorno = ctx.tipoRetorno().getText();

        // Coletar tipos dos parâmetros (serão adicionados no exitParametro)
        List<String> tiposParametros = new ArrayList<>();
        if (ctx.parametros() != null) {
            for (TarbaloParser.ParametroContext p : ctx.parametros().parametro()) {
                String tipo = p.tipoVariavel().getText();
                if (!p.variavel().dimensao().isEmpty()) {
                    tipo += "[]";
                }
                tiposParametros.add(tipo);
            }
        }

        Simbolo func = new Simbolo(nome, tipoRetorno, tiposParametros, false);
        tabela.adicionar(func);

        // Abrir escopo para parâmetros e corpo da função
        tabela.abrirEscopo();
        dentroDeFuncao = true;
        pilhaRetornos.push(tipoRetorno);
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }

    @Override
    public void exitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        if (!funcaoAtualAceita) return; // enter retornou antecipadamente — nada para limpar

        String nome = ctx.ID().getText();
        String tipoRetorno = ctx.tipoRetorno().getText();
        int linha = ctx.start.getLine();

        // Verificar se a função não-vazia sempre retorna um valor
        if (!tipoRetorno.equals("vazio") && !blocoSempreRetorna(ctx.bloco())) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome + "' pode não retornar valor em todos os caminhos.");
        }

        tabela.fecharEscopo(); // fecha o escopo da função (inclui parâmetros e corpo)
        pilhaRetornos.pop();
        dentroDeFuncao = !pilhaRetornos.isEmpty();
        if (pilhaVariaveisInicializadas.size() > 1) {
            pilhaVariaveisInicializadas.pop();
        }
    }

    @Override
    public void exitParametro(TarbaloParser.ParametroContext ctx) {
        String tipoBase = ctx.tipoVariavel().getText();
        String nome = ctx.variavel().ID().getText();
        int linha = ctx.start.getLine();

        if (tabela.existeNoEscopoAtual(nome)) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Parâmetro '" + nome + "' duplicado.");
            return;
        }

        List<TarbaloParser.DimensaoContext> dimensoes = ctx.variavel().dimensao();
        if (!dimensoes.isEmpty()) {
            int dim = dimensoes.size();
            List<Integer> tamanhos = new ArrayList<>();
            boolean dinamico = false;
            for (TarbaloParser.DimensaoContext dimCtx : dimensoes) {
                if (dimCtx.expressao().isEmpty()) {
                    dinamico = true;
                    tamanhos.add(null);
                } else {
                    Integer tam = extrairNumero(dimCtx.expressao(0));
                    if (tam != null) {
                        tamanhos.add(tam);
                    } else {
                        dinamico = true;
                        tamanhos.add(null);
                    }
                }
            }
            // Verificar mistura de dimensões estáticas e dinâmicas
            if (dim > 1) {
                boolean temEstatica = tamanhos.stream().anyMatch(t -> t != null);
                boolean temDinamica = tamanhos.stream().anyMatch(t -> t == null);
                if (temEstatica && temDinamica) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Parâmetro '" + nome
                        + "' não pode misturar dimensões estáticas e dinâmicas.");
                    return;
                }
            }
            // Cria símbolo de vetor com as informações completas
            Simbolo param = new Simbolo(nome, tipoBase, dim, dinamico, tamanhos);
            tabela.adicionar(param);
        } else {
            tabela.adicionar(new Simbolo(nome, tipoBase));
        }
        // Parâmetros são inicializados pelo chamador
        marcarInicializada(nome);
    }

    /* ---------------------------------------------------------------------
     * RETORNO
     * --------------------------------------------------------------------- */
    @Override
    public void enterRetorno(TarbaloParser.RetornoContext ctx) {
        if (!dentroDeFuncao) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'retorne' fora de uma função.");
            return;
        }

        if (ctx.expressao() != null) {
            String tipoRet = pilhaRetornos.peek();
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
            String tipoExpr = avaliador.visit(ctx.expressao());
            if (!tipoExpr.equals("erro") && !saoCompativeis(tipoRet, tipoExpr)) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Tipo de retorno incompatível. Função espera '" + tipoRet + "', mas retornou '" + tipoExpr + "'.");
            }
        } else {
            String tipoRet = pilhaRetornos.isEmpty() ? "vazio" : pilhaRetornos.peek();
            if (!tipoRet.equals("vazio")) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Função deve retornar um valor do tipo '" + tipoRet + "', mas 'retorne' está vazio.");
            }
        }
    }

    /* ---------------------------------------------------------------------
     * LAÇOS (pare, continuar)
     * --------------------------------------------------------------------- */
    // ---------- SE (condicional) ----------
    @Override
    public void enterCmdSe(TarbaloParser.CmdSeContext ctx) {
        profundidadeEstruturaControle++;
    }
    @Override
    public void exitCmdSe(TarbaloParser.CmdSeContext ctx) {
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoCond = avaliador.visit(ctx.expressao());
        if (!tipoCond.equals("lgc") && !tipoCond.equals("erro") && !tipoCond.equals("desconhecido")) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Condição deve ser 'lgc', encontrado '" + tipoCond + "'.");
        }
        profundidadeEstruturaControle--;
    }

    // ---------- ENQUANTO (laço) ----------
    @Override
    public void enterCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        nivelLaco++;
        profundidadeEstruturaControle++;
        tabela.abrirEscopo();
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }
    @Override
    public void exitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoCond = avaliador.visit(ctx.expressao());
        if (!tipoCond.equals("lgc") && !tipoCond.equals("erro") && !tipoCond.equals("desconhecido")) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Condição deve ser 'lgc', encontrado '" + tipoCond + "'.");
        }
        tabela.fecharEscopo();
        if (pilhaVariaveisInicializadas.size() > 1) pilhaVariaveisInicializadas.pop();
        nivelLaco--;
        profundidadeEstruturaControle--;
    }

    // ---------- FACA-ENQUANTO (laço + controle) ----------
    @Override
    public void enterCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        nivelLaco++;
        profundidadeEstruturaControle++;
        tabela.abrirEscopo();
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }
    @Override
    public void exitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoCond = avaliador.visit(ctx.expressao());
        if (!tipoCond.equals("lgc") && !tipoCond.equals("erro") && !tipoCond.equals("desconhecido")) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Condição deve ser 'lgc', encontrado '" + tipoCond + "'.");
        }
        tabela.fecharEscopo();
        if (pilhaVariaveisInicializadas.size() > 1) pilhaVariaveisInicializadas.pop();
        nivelLaco--;
        profundidadeEstruturaControle--;
    }

    // ---------- PARA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdPara(TarbaloParser.CmdParaContext ctx) {
        nivelLaco++;
        profundidadeEstruturaControle++;
        tabela.abrirEscopo();
        pilhaVariaveisInicializadas.push(new HashSet<>());
    }
    @Override
    public void exitCmdPara(TarbaloParser.CmdParaContext ctx) {
        tabela.fecharEscopo();
        if (pilhaVariaveisInicializadas.size() > 1) {
            pilhaVariaveisInicializadas.pop();
        }
        nivelLaco--;
        profundidadeEstruturaControle--;
    }

    // ---------- PARACADA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        nivelLaco++;
        profundidadeEstruturaControle++;
        tabela.abrirEscopo();
        pilhaVariaveisInicializadas.push(new HashSet<>());
        // O resto do método permanece igual: declaração da variável de iteração, etc.
        String nome = ctx.ID().getText();
        String tipo = ctx.tipoVariavel().getText();
        if (tabela.existeNoEscopoAtual(nome)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nome + "' já declarada neste escopo.");
            erros = true;
        } else {
            tabela.adicionar(new Simbolo(nome, tipo));
        }
        // Iterador de pancada é inicializado pela expressão do vetor
        marcarInicializada(nome);

        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoExpr = avaliador.visit(ctx.expressao());
        if (!tipoExpr.equals("erro") && !tipoExpr.endsWith("[]")) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                "): A expressão em 'pancada' deve ser um vetor, mas é do tipo '" + tipoExpr + "'.");
            erros = true;
        }

        // Validar compatibilidade entre tipo do iterador e tipo base do vetor
        if (!tipoExpr.equals("erro") && tipoExpr.endsWith("[]")) {
            // Remover apenas o último [] (não todos)
            String tipoBaseVetor = tipoExpr.substring(0, tipoExpr.length() - 2);
            if (!saoCompativeis(tipo, tipoBaseVetor) && !tipo.equals("desconhecido")) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Tipo incompatível em 'pancada'. O iterador é do tipo '" + tipo +
                    "', mas o vetor contém elementos do tipo '" + tipoBaseVetor + "'.");
                erros = true;
            }
        }
    }

    @Override
    public void exitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        tabela.fecharEscopo();
        if (pilhaVariaveisInicializadas.size() > 1) {
            pilhaVariaveisInicializadas.pop();
        }
        nivelLaco--;
        profundidadeEstruturaControle--;
    }

    @Override
    public void enterInicializacaoPara(TarbaloParser.InicializacaoParaContext ctx) {
        if (ctx.VARIAVEL() != null) {
            String nome = ctx.ID().getText();
            String tipo = ctx.tipoVariavel().getText();
            int linha = ctx.start.getLine();

            if (tabela.existeNoEscopoAtual(nome)) {
                System.err.println("Erro Semântico (Linha " + linha + "): Variável '" + nome + "' já declarada neste escopo.");
                erros = true;
            } else {
                tabela.adicionar(new Simbolo(nome, tipo));
                // Variável de inicialização do para é inicializada pela própria declaração
                marcarInicializada(nome);

                // Verificar compatibilidade da expressão de inicialização
                AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
                String tipoExpr = avaliador.visit(ctx.expressao());
                if (!tipoExpr.equals("erro") && !saoCompativeis(tipo, tipoExpr)) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na inicialização. A variável '" + nome + "' é do tipo '" + tipo + "', mas recebeu um valor do tipo '" + tipoExpr + "'.");
                    erros = true;
                }
            }
        }
    }

    @Override
    public void enterPare(TarbaloParser.PareContext ctx) {
        if (nivelLaco == 0) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'pare' fora de um laço.");
        }
    }

    @Override
    public void enterContinuar(TarbaloParser.ContinuarContext ctx) {
        if (nivelLaco == 0) {
            erros = true;
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'continuar' fora de um laço.");
        }
    }

    /* ---------------------------------------------------------------------
     * CHAMADA DE FUNÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void enterChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx) {
        String nome = ctx.ID().getText();
        int linha = ctx.start.getLine();

        // Validar que 'tamanho' recebeu ao menos um argumento
        if (nome.equals("tamanho") || nome.startsWith("tamanho_")) {
            if (ctx.argumentos() == null || ctx.argumentos().expressao().isEmpty()) {
                System.err.println("Erro Semântico (Linha " + linha + "): Função 'tamanho' requer um argumento.");
                erros = true;
                return;
            }
        }

        // Avaliar tipos dos argumentos UMA única vez
        AvaliadorDeTipos av = new AvaliadorDeTipos(linha);
        List<String> tiposArgs = new ArrayList<>();
        if (ctx.argumentos() != null) {
            for (var expr : ctx.argumentos().expressao()) {
                String tipo = av.visit(expr);
                if (tipo.equals("erro")) {
                    erros = true;
                    return;
                }
                tiposArgs.add(tipo);
            }
        }

        // Se é uma instanciação de struct (NomeStruct()), validar argumentos
        Simbolo sStruct = tabela.buscar(nome);
        if (sStruct != null && sStruct.getCategoria() == Simbolo.Categoria.REGS) {
            // Contar campos do struct (parâmetros do construtor)
            List<String> tiposCampos = new ArrayList<>();
            for (Simbolo s : tabela.buscarTodos()) {
                if (s.getCategoria() == Simbolo.Categoria.REGS_CAMPO && s.getNomeRegs().equals(nome)) {
                    tiposCampos.add(s.getTipo());
                }
            }
            // Validar número de argumentos
            if (tiposArgs.size() != tiposCampos.size()) {
                erros = true;
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                    "): Construtor '" + nome + "' espera " + tiposCampos.size() +
                    " argumento(s), mas recebeu " + tiposArgs.size() + ".");
                return;
            }
            // Validar tipos dos argumentos
            for (int i = 0; i < tiposArgs.size(); i++) {
                if (!tiposArgs.get(i).equals("erro") && !tiposArgs.get(i).equals("desconhecido") &&
                    !saoCompativeis(tiposCampos.get(i), tiposArgs.get(i))) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                        "): Argumento " + (i + 1) + " do construtor '" + nome + "' espera '" +
                        tiposCampos.get(i) + "', mas recebeu '" + tiposArgs.get(i) + "'.");
                }
            }
            return;
        }

        // Resolver sobrecarga usando os tipos já avaliados
        Simbolo func = tabela.resolverSobrecarga(nome, tiposArgs);
        if (func == null) {
            List<Simbolo> candidatos = tabela.buscarFuncoes(nome);
            if (candidatos.isEmpty()) {
                System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome + "' não declarada.");
            } else {
                System.err.println("Erro Semântico (Linha " + linha + "): Chamada ambígua ou tipos incompatíveis para função '" + nome + "'.");
            }
            erros = true;
            return;
        }

        mapaResolucao.put(ctx, func);

        // Número de argumentos
        int numArgs = tiposArgs.size();
        List<String> tiposParam = func.getTiposParametros();
        int numParams = tiposParam.size();

        if (numArgs != numParams) {
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome +
                            "' espera " + numParams + " parâmetro(s), mas foi chamada com " + numArgs + ".");
            erros = true;
            return;
        }

        // Verificar tipo de cada argumento (reusa tiposArgs já avaliados)
        for (int i = 0; i < numArgs; i++) {
            String tipoArg = tiposArgs.get(i);
            String tipoPar = tiposParam.get(i);

            if (!tipoArg.equals("erro") && !saoCompativeis(tipoPar, tipoArg)) {
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível no argumento " + (i+1) +
                                " da função '" + nome + "'. Esperado '" + tipoPar + "', mas recebeu '" + tipoArg + "'.");
                erros = true;
            }
        }
    }

    /* ---------------------------------------------------------------------
     * MÉTODOS AUXILIARES
     * --------------------------------------------------------------------- */
    private boolean saoCompativeis(String tipoVar, String tipoExpr) {
        if (tipoVar.equals("desconhecido") || tipoExpr.equals("desconhecido")) return true;
        if (tipoExpr.equals("erro")) return false;
        if (tipoVar.equals(tipoExpr)) return true;
        if (tipoVar.equals("qbd") && tipoExpr.equals("int")) return true; // int cabe em qbd
        if (tipoVar.equals("int") && tipoExpr.equals("ltr")) return true; // ltr cabe em int
        if (tipoVar.equals("qbd") && tipoExpr.equals("ltr")) return true; // ltr cabe em qbd
        // int[] cabe em qbd[], int[][] cabe em qbd[][], etc.
        if (tipoVar.startsWith("qbd") && tipoExpr.startsWith("int")) {
            String sufixoVar = tipoVar.substring(3);
            String sufixoExpr = tipoExpr.substring(3);
            if (sufixoVar.equals(sufixoExpr) && sufixoVar.startsWith("[")) return true;
        }
        // Compatibilidade cnjt ↔ tipo base
        String baseVar = resolverTipoBaseCnjt(tipoVar);
        String baseExpr = resolverTipoBaseCnjt(tipoExpr);
        if (!baseVar.equals(tipoVar) || !baseExpr.equals(tipoExpr)) {
            return saoCompativeis(baseVar, baseExpr);
        }
        return false;
    }

    private String resolverTipoBaseCnjt(String tipo) {
        Simbolo s = tabela.buscar(tipo);
        if (s != null && s.getCategoria() == Simbolo.Categoria.CNJT) {
            return s.getTipo(); // retorna o tipo base do cnjt (int, qbd, txt, etc.)
        }
        return tipo;
    }

    private String obterNome(TarbaloParser.VariavelContext ctx) {
        if (ctx == null) return null;
        return ctx.ID().getText();
    }

    private String obterNome(TarbaloParser.OperandoContext ctx) {
        if (ctx.variavel() != null) return ctx.variavel().ID().getText();
        return null;
    }

    private boolean contemSlice(TarbaloParser.VariavelContext ctx) {
        for (TarbaloParser.DimensaoContext dim : ctx.dimensao()) {
            if (dim.PONTOPONTO() != null) return true;
        }
        return false;
    }

    // Extrai um número inteiro literal de uma expressao (para tamanhos de dimensão).
    // Caminho: expressao → expressaoConcatenacao → expressaoXor → expressaoE → expressaoNegacao →
    //          expressaoRelacional → expressaoAditiva → expressaoMultiplicativa → operando → NUM
    private Integer extrairNumero(TarbaloParser.ExpressaoContext expr) {
        if (expr.expressaoConcatenacao().size() != 1) return null;
        var conc = expr.expressaoConcatenacao(0);
        if (conc.expressaoXor().size() != 1) return null;
        var xor = conc.expressaoXor(0);
        if (xor.expressaoE().size() != 1) return null;
        var e = xor.expressaoE(0);
        if (e.expressaoNegacao().size() != 1) return null;
        var neg = e.expressaoNegacao(0);
        if (neg.NAO() != null) return null;
        var rel = neg.expressaoRelacional();
        if (rel.operadorRelacional() != null) return null;
        if (rel.expressaoAditiva().size() != 1) return null;
        var ad = rel.expressaoAditiva(0);
        if (ad.expressaoMultiplicativa().size() != 1) return null;
        var mul = ad.expressaoMultiplicativa(0);
        if (mul.operando().size() != 1) return null;
        var op = mul.operando(0);
        // Suporte a unário negativo: -NUM
        if (op.MENOS() != null && op.operando() != null) {
            Integer inner = extrairNumeroDeOperando(op.operando());
            return inner != null ? -inner : null;
        }
        return extrairNumeroDeOperando(op);
    }

    private Integer extrairNumeroDeOperando(TarbaloParser.OperandoContext op) {
        if (op.NUM() != null) {
            try {
                return Integer.parseInt(op.NUM().getText());
            } catch (NumberFormatException nfe) {
                System.err.println("Erro Semântico (Linha " + op.NUM().getSymbol().getLine() + "): Número inteiro muito grande: " + op.NUM().getText());
                erros = true;
                return null;
            }
        }
        return null;
    }

    private boolean saoCompativeisRelacionais(String tipoEsq, String tipoDir) {
        if (tipoEsq.equals("erro") || tipoDir.equals("erro")) return true;
        if (tipoEsq.equals("desconhecido") || tipoDir.equals("desconhecido")) return true;

        // Resolver tipos cnjt para o tipo base
        String baseEsq = resolverTipoBaseCnjt(tipoEsq);
        String baseDir = resolverTipoBaseCnjt(tipoDir);

        // Números (int, dec e ltr) são compatíveis entre si
        boolean esqNumerico = baseEsq.equals("int") || baseEsq.equals("qbd") || baseEsq.equals("ltr");
        boolean dirNumerico = baseDir.equals("int") || baseDir.equals("qbd") || baseDir.equals("ltr");
        if (esqNumerico && dirNumerico) return true;

        // Tipos iguais (texto, car, logico)
        return baseEsq.equals(baseDir);
    }

    /* ======================================================================
     * ANÁLISE DE RETORNO DEFINITIVO
     * ====================================================================== */

    /**
     * Verifica se um bloco SEMPRE executa um 'receba' em todos os caminhos.
     * Regras (mesma lógica do Java):
     *   - Último comando é 'retorno' → true
     *   - Último comando é 'sepa/senao' onde AMBOS os blocos retornam → true
     *   - Qualquer outra coisa → false
     */
    private boolean blocoSempreRetorna(TarbaloParser.BlocoContext bloco) {
        if (bloco == null || bloco.comando().isEmpty()) return false;

        // Verificar o ÚLTIMO comando do bloco
        TarbaloParser.ComandoContext ultimoCmd = bloco.comando().get(bloco.comando().size() - 1);

        // Regra 1: receba → sempre retorna
        if (ultimoCmd.retorno() != null) return true;

        // Regra 2: sepa/senao → retorna se AMBOS os caminhos retornam
        if (ultimoCmd.cmdSe() != null) {
            TarbaloParser.CmdSeContext se = ultimoCmd.cmdSe();
            boolean ifRetorna = blocoSempreRetorna(se.bloco(0));
            boolean elseRetorna = se.bloco().size() > 1
                                   && blocoSempreRetorna(se.bloco(1));
            return ifRetorna && elseRetorna;
        }

        // Regra 3: cmdBloco { ... } — recursivamente verificar o bloco interno
        if (ultimoCmd.cmdBloco() != null) {
            return blocoSempreRetorna(ultimoCmd.cmdBloco().bloco());
        }

        // Regra 4: vamo (do-while) — executa pelo menos 1x, se o corpo retorna → sempre retorna
        if (ultimoCmd.cmdFacaEnquanto() != null) {
            return blocoSempreRetorna(ultimoCmd.cmdFacaEnquanto().bloco());
        }

        // Regra 5: escolha (switch) — retorna se TODOS os casos + padrao retornam
        if (ultimoCmd.cmdEscolha() != null) {
            TarbaloParser.CmdEscolhaContext escolha = ultimoCmd.cmdEscolha();
            for (TarbaloParser.BlocoCasoContext caso : escolha.blocoCaso()) {
                if (!blocoSempreRetorna(caso.bloco())) return false;
            }
            // Padrao é obrigatório para garantir retorno
            if (escolha.blocoPadrao() == null) return false;
            return blocoSempreRetorna(escolha.blocoPadrao().bloco());
        }

        // Regra 6: laços, comandos simples, etc. → não garante retorno
        return false;
    }


    /* ======================================================================
     * AVALIADOR DE TIPOS (interno)
     * ====================================================================== */
    private class AvaliadorDeTipos extends TarbaloBaseVisitor<String> {
        private int linha;

        public AvaliadorDeTipos(int linha) {
            this.linha = linha;
        }

        @Override
        public String visitValorAtribuicao(TarbaloParser.ValorAtribuicaoContext ctx) {
            if (ctx.expressao() != null) return visit(ctx.expressao());
            if (ctx.inicializacaoVetor() != null) {
                // Retornamos um marcador especial para que a verificação em exitAtribuicao
                // saiba que se trata de uma inicialização literal de vetor.
                return "vetor_literal";
            }
            return "desconhecido";
        }

        @Override
        public String visitOperando(TarbaloParser.OperandoContext ctx) {
            if (ctx.NUM() != null) return "int";
            if (ctx.NUMDEC() != null) return "qbd";
            if (ctx.STRING() != null) return "txt";
            if (ctx.CHAR() != null) return "ltr";
            if (ctx.VERDADEIRO() != null || ctx.FALSO() != null) return "lgc";

            if (ctx.acessoElem() != null) {
                TarbaloParser.AcessoElemContext aec = ctx.acessoElem();
                List<TerminalNode> ids = aec.ID();
                String nomeRaiz = ids.get(0).getText();
                Simbolo sRaiz = tabela.buscar(nomeRaiz);
                if (sRaiz == null) {
                    System.err.println("Erro Semântico (Linha " + linha + "): '" + nomeRaiz + "' não declarado.");
                    erros = true;
                    return "erro";
                }

                // Acesso a elemento de cnjt: Cor::VERMELHO (apenas 2 IDs, sem dimensões)
                if (ids.size() == 2 && aec.dimensao().isEmpty() && sRaiz.getCategoria() == Simbolo.Categoria.CNJT) {
                    String nomeDir = ids.get(1).getText();
                    Simbolo sElem = tabela.buscar(nomeDir);
                    if (sElem == null || sElem.getCategoria() != Simbolo.Categoria.CNJT_ELEM
                        || !sElem.getTipoCnjt().equals(nomeRaiz)) {
                        System.err.println("Erro Semântico (Linha " + linha + "): Elemento '" + nomeDir + "' não encontrado no cnjt '" + nomeRaiz + "'.");
                        erros = true;
                        return "erro";
                    }
                    return sElem.getTipo();
                }

                // Resolver tipo inicial
                String tipoAtual = sRaiz.getTipo();
                if (sRaiz.getCategoria() == Simbolo.Categoria.VETOR && !aec.dimensao().isEmpty()) {
                    tipoAtual = sRaiz.getTipoBase();
                }

                // Cadeia de campos: p::endereco::rua
                for (int i = 1; i < ids.size(); i++) {
                    String campo = ids.get(i).getText();

                    // Verificar Erro built-in
                    if (tipoAtual.equals("Erro")) {
                        if (campo.equals("mensagem")) {
                            return "txt";
                        }
                        System.err.println("Erro Semântico (Linha " + linha + "): 'Erro' não tem campo '" + campo + "'.");
                        erros = true;
                        return "erro";
                    }

                    // Verificar struct field
                    Simbolo sStruct = tabela.buscar(tipoAtual);
                    if (sStruct == null || sStruct.getCategoria() != Simbolo.Categoria.REGS) {
                        System.err.println("Erro Semântico (Linha " + linha + "): '" + tipoAtual + "' não é um registro.");
                        erros = true;
                        return "erro";
                    }

                    Simbolo sCampo = tabela.buscarCampo(tipoAtual, campo);
                    if (sCampo == null) {
                        System.err.println("Erro Semântico (Linha " + linha + "): Campo '" + campo + "' não encontrado no struct '" + tipoAtual + "'.");
                        erros = true;
                        return "erro";
                    }

                    tipoAtual = sCampo.getTipo();
                }

                return tipoAtual;
            }

            if (ctx.variavel() != null) {
                String nomeVar = ctx.variavel().ID().getText();
                Simbolo s = tabela.buscar(nomeVar);
                if (s == null) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Variável '" + nomeVar + "' não declarada.");
                    erros = true;
                    return "erro";
                }
                // Se não tem dimensão, é variável simples
                if (ctx.variavel().dimensao().isEmpty()) {
                    // Verificar se a variável foi inicializada
                    if (s.getCategoria() == Simbolo.Categoria.VARIAVEL && !isInicializada(nomeVar)) {
                        System.err.println("Aviso Semântico (Linha " + linha + "): Variável '" + nomeVar + "' pode não ter sido inicializada.");
                    }
                    return s.getTipo();
                }
                // Se tem dimensão, é acesso a vetor
                if (s.getCategoria() != Simbolo.Categoria.VETOR) {
                    System.err.println("Erro Semântico (Linha " + linha + "): '" + nomeVar + "' não é um vetor e não pode ser indexado.");
                    erros = true;
                    return "erro";
                }
                // Verifica se há slice e valida tipos dos índices
                boolean isSlice = false;
                int dimsAcessadas = ctx.variavel().dimensao().size();
                for (TarbaloParser.DimensaoContext dim : ctx.variavel().dimensao()) {
                    if (dim.PONTOPONTO() != null) {
                        isSlice = true;
                    }
                    // Validar que as expressões de índice são do tipo int (ltr é aceito por compatibilidade numérica)
                    for (TarbaloParser.ExpressaoContext exprCtx : dim.expressao()) {
                        String tipoIdx = visit(exprCtx);
                        if (!tipoIdx.equals("erro") && !tipoIdx.equals("desconhecido") &&
                            !tipoIdx.equals("int") && !tipoIdx.equals("ltr")) {
                            System.err.println("Erro Semântico (Linha " + linha + "): Índice de vetor deve ser do tipo 'int', mas recebeu '" + tipoIdx + "'.");
                            erros = true;
                        }
                    }
                }
                // Validação de limites para vetores com tamanho conhecido
                List<Integer> tamanhosVetor = s.getTamanhos();
                int dimIdx = 0;
                for (TarbaloParser.DimensaoContext dim : ctx.variavel().dimensao()) {
                    if (dim.PONTOPONTO() != null) break; // slice, não validar
                    if (dim.expressao().isEmpty()) break;
                    if (tamanhosVetor != null && dimIdx < tamanhosVetor.size() && tamanhosVetor.get(dimIdx) != null) {
                        Integer idxLiteral = extrairNumero(dim.expressao(0));
                        if (idxLiteral != null) {
                            if (idxLiteral < 0 || idxLiteral >= tamanhosVetor.get(dimIdx)) {
                                System.err.println("Erro Semântico (Linha " + linha + "): Índice " + idxLiteral
                                    + " fora dos limites do vetor '" + nomeVar + "' (tamanho: " + tamanhosVetor.get(dimIdx) + ").");
                                erros = true;
                            }
                        }
                    }
                    dimIdx++;
                }
                // Calcular tipo resultante: se acessa menos dimensões do que o vetor tem, retorna tipo com dimensões restantes
                int dimsVetor = s.getDimensoes();
                int dimsRestantes = dimsVetor - dimsAcessadas + (isSlice ? 1 : 0);
                String tipoResult = s.getTipoBase();
                for (int d = 0; d < dimsRestantes; d++) {
                    tipoResult += "[]";
                }
                return tipoResult;
            }

            if (ctx.expressao() != null) return visit(ctx.expressao());

            if (ctx.chamadaFuncao() != null) {
                String nomeFunc = ctx.chamadaFuncao().ID().getText();

                // Avaliar tipos dos argumentos para resolver sobrecarga
                List<String> tiposArgsFunc = new ArrayList<>();
                if (ctx.chamadaFuncao().argumentos() != null) {
                    for (var expr : ctx.chamadaFuncao().argumentos().expressao()) {
                        tiposArgsFunc.add(visit(expr));
                    }
                }
                Simbolo func = tabela.resolverSobrecarga(nomeFunc, tiposArgsFunc);
                if (func == null) func = tabela.buscar(nomeFunc); // fallback

                if (func != null && func.getCategoria() == Simbolo.Categoria.FUNCAO) {
                    String ret = func.getTipoRetorno();
                    if (ret.equals("vazio")) {
                        System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nomeFunc + "' é do tipo vazio e não pode ser usada em expressão.");
                        erros = true;
                        return "erro";
                    }
                    return ret;
                }
                return "desconhecido";
            }

            if (ctx.chamadaConstrutor() != null) {
                String nomeStruct = ctx.chamadaConstrutor().ID().getText();
                Simbolo s = tabela.buscar(nomeStruct);
                if (s != null && s.getCategoria() == Simbolo.Categoria.REGS) {
                    return nomeStruct;
                }
                System.err.println("Erro Semântico (Linha " + linha + "): '" + nomeStruct + "' não é um struct válido.");
                erros = true;
                return "erro";
            }

            // Unário negativo: -operando
            if (ctx.MENOS() != null && ctx.operando() != null) {
                String tipo = visit(ctx.operando());
                if (tipo.equals("erro") || tipo.equals("desconhecido")) return tipo;
                if (!tipo.equals("int") && !tipo.equals("qbd")) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Operador unário '-' não aplicável ao tipo '" + tipo + "'.");
                    erros = true;
                    return "erro";
                }
                return tipo;
            }

            return "desconhecido";
        }

        @Override
        public String visitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx) {
            // As verificações semânticas estão no listener exitExpressaoRelacional
            // Aqui apenas inferimos o tipo
            visit(ctx.expressaoAditiva(0));
            if (ctx.operadorRelacional() != null && ctx.expressaoAditiva().size() > 1) {
                visit(ctx.expressaoAditiva(1));
                return "lgc";
            }
            return visit(ctx.expressaoAditiva(0));
        }


        @Override
        public String visitExpressaoE(TarbaloParser.ExpressaoEContext ctx) {
        if (ctx.expressaoNegacao().size() == 1) {
            return visit(ctx.expressaoNegacao(0));
        }
        String tipoAcum = visit(ctx.expressaoNegacao(0));
        for (int i = 1; i < ctx.expressaoNegacao().size(); i++) {
            String tipo = visit(ctx.expressaoNegacao(i));
            if (tipoAcum.equals("erro") || tipo.equals("erro")) return "erro";
            if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                if (!tipoAcum.equals("desconhecido") && !tipo.equals("desconhecido")) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Operador 'e' exige operandos lógicos.");
                    erros = true;
                    return "erro";
                }
            }
            tipoAcum = "lgc";
        }
        return "lgc";
    }

        @Override
        public String visitExpressaoXor(TarbaloParser.ExpressaoXorContext ctx) {
            if (ctx.expressaoE().size() == 1) {
                return visit(ctx.expressaoE(0));
            }
            String tipoAcum = visit(ctx.expressaoE(0));
            for (int i = 1; i < ctx.expressaoE().size(); i++) {
                String tipo = visit(ctx.expressaoE(i));
                if (tipoAcum.equals("erro") || tipo.equals("erro")) return "erro";
                if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                    if (!tipoAcum.equals("desconhecido") && !tipo.equals("desconhecido")) {
                        System.err.println("Erro Semântico (Linha " + linha + "): Operador 'xor' exige operandos lógicos.");
                        erros = true;
                        return "erro";
                    }
                }
                tipoAcum = "lgc";
            }
            return "lgc";
        }

        @Override
        public String visitExpressao(TarbaloParser.ExpressaoContext ctx) {
            if (ctx.expressaoConcatenacao().size() == 1) {
                return visit(ctx.expressaoConcatenacao(0));
            }
            String tipoAcum = visit(ctx.expressaoConcatenacao(0));
            for (int i = 1; i < ctx.expressaoConcatenacao().size(); i++) {
                String tipo = visit(ctx.expressaoConcatenacao(i));
                if (tipoAcum.equals("erro") || tipo.equals("erro")) return "erro";
                if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                    if (!tipoAcum.equals("desconhecido") && !tipo.equals("desconhecido")) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha "+linha+"): Operador 'ou' exige operandos lógicos.");
                        return "erro";
                    }
                }
                tipoAcum = "lgc";
            }
            return tipoAcum;
        }

        @Override
        public String visitExpressaoNegacao(TarbaloParser.ExpressaoNegacaoContext ctx) {
            if (ctx.NAO() != null) {
                String tipo = visit(ctx.expressaoNegacao());
                if (!tipo.equals("lgc") && !tipo.equals("erro") && !tipo.equals("desconhecido")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Operador 'nao' exige operando lógico, mas recebeu '" + tipo + "'.");
                    return "erro";
                }
                return "lgc";
            }
            return visit(ctx.expressaoRelacional());
        }

        @Override
        public String visitExpressaoConcatenacao(TarbaloParser.ExpressaoConcatenacaoContext ctx) {
            if (!ctx.CONCAT().isEmpty()) {
                for (TarbaloParser.ExpressaoXorContext sub : ctx.expressaoXor()) {
                    visit(sub);
                }
                return "txt";
            }
            return visit(ctx.expressaoXor(0));
        }

        @Override
        public String visitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx) {
            String tipoEsq = visit(ctx.expressaoMultiplicativa(0));
            if (ctx.expressaoMultiplicativa().size() == 1) return tipoEsq;

            for (int i = 1; i < ctx.expressaoMultiplicativa().size(); i++) {
                String tipoDir = visit(ctx.expressaoMultiplicativa(i));
                if (tipoEsq.equals("erro") || tipoDir.equals("erro") ||
                    tipoEsq.equals("desconhecido") || tipoDir.equals("desconhecido")) {
                    return "erro";
                }
                if (!ehNumerico(tipoEsq) || !ehNumerico(tipoDir)) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Operadores '+' e '-' exigem operandos numéricos, mas recebeu '" + tipoEsq + "' e '" + tipoDir + "'. Use '&' para concatenação.");
                    return "erro";
                }
                tipoEsq = tipoEsq.equals("qbd") || tipoDir.equals("qbd") ? "qbd" : "int";
            }
            return tipoEsq;
        }

        private boolean ehNumerico(String tipo) {
            return tipo.equals("int") || tipo.equals("qbd") || tipo.equals("ltr");
        }

        @Override
        public String visitExpressaoMultiplicativa(TarbaloParser.ExpressaoMultiplicativaContext ctx) {
            // Visitar operandos para inferir tipos
            List<String> tipos = new ArrayList<>();
            for (TarbaloParser.OperandoContext op : ctx.operando()) {
                tipos.add(visit(op));
            }

            if (tipos.size() == 1) return tipos.get(0);

            String tipoEsq = tipos.get(0);
            // Verificar cada operador e operando direito
            for (int i = 0; i < ctx.getChildCount() / 2; i++) {
                String op = ctx.getChild(2 * i + 1).getText();
                String tipoDir = tipos.get(i + 1);

                if (tipoEsq.equals("erro") || tipoDir.equals("erro") ||
                    tipoEsq.equals("desconhecido") || tipoDir.equals("desconhecido")) {
                    return "erro";
                }

                if (op.equals("//") || op.equals("%")) {
                    // Divisão inteira e módulo exigem operandos int
                    if (!tipoEsq.equals("int") || !tipoDir.equals("int")) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + linha + "): Operador '" + op + "' exige operandos do tipo 'int', mas recebeu '" + tipoEsq + "' e '" + tipoDir + "'.");
                        return "erro";
                    }
                    tipoEsq = "int";
                } else {
                    // * e / aceitam numéricos
                    if (!ehNumerico(tipoEsq) || !ehNumerico(tipoDir)) {
                        erros = true;
                        System.err.println("Erro Semântico (Linha " + linha + "): Operador '" + op + "' exige operandos numéricos, mas recebeu '" + tipoEsq + "' e '" + tipoDir + "'.");
                        return "erro";
                    }
                    tipoEsq = tipoEsq.equals("qbd") || tipoDir.equals("qbd") ? "qbd" : "int";
                }
            }
            return tipoEsq;
        }

        @Override
        protected String aggregateResult(String aggregate, String nextResult) {
            if (aggregate == null) return nextResult;
            if (nextResult == null) return aggregate;
            if (aggregate.equals("erro") || nextResult.equals("erro")) return "erro";
            if (aggregate.equals("desconhecido") || nextResult.equals("desconhecido")) return "desconhecido";
            if (aggregate.equals(nextResult)) return aggregate;
            if ((aggregate.equals("int") && nextResult.equals("qbd")) ||
                    (aggregate.equals("qbd") && nextResult.equals("int"))) {
                return "qbd";
            }
            erros = true;
            System.err.println("Erro Semântico (Linha " + linha + "): Tipos incompatíveis na operação ('" + aggregate + "' com '" + nextResult + "')");
            return "erro";
        }
    }
}