package com.compilador;

import com.compilador.tarbalo.TarbaloBaseListener;
import com.compilador.tarbalo.TarbaloParser;
import com.compilador.tarbalo.TarbaloBaseVisitor;
import java.util.*;

import org.antlr.v4.runtime.tree.ParseTree;

public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela;

    // Para controle de contexto (funções, laços)
    private Deque<String> pilhaRetornos = new ArrayDeque<>(); // tipo de retorno da função atual
    private Map<ParseTree, Simbolo> mapaResolucao = new HashMap<>();
    private Map<ParseTree, String> mapaTiposExpressao = new HashMap<>();
    private int nivelLaco = 0;
    private boolean dentroDeFuncao = false;

    private boolean erros = false;

    public boolean houveErros() { return erros; }

    public AnalisadorSemantico(TabelaSimbolos tabela) {
        this.tabela = tabela;
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
    }

    @Override
    public void exitCmdBloco(TarbaloParser.CmdBlocoContext ctx) {
        tabela.fecharEscopo();
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
                    tamanhos.add(tam);
                } else {
                    dinamico = true;
                    tamanhos.add(null);
                }
            }
        }

        Simbolo simbolo = new Simbolo(nomeVar, tipoBase, dim, dinamico, tamanhos);
        tabela.adicionar(simbolo);

        // Verifica inicialização do vetor
        TarbaloParser.InicializacaoVetorContext initVetor =
            ctx.valorAtribuicao() != null ? ctx.valorAtribuicao().inicializacaoVetor() : null;

        if (initVetor != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            for (TarbaloParser.ExpressaoContext exprCtx : initVetor.expressao()) {
                String tipoElem = avaliador.visit(exprCtx);
                if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Elemento do vetor '" + nomeVar + "' de tipo incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                    break;
                }
            }
        }

        if (initVetor != null && !tamanhos.isEmpty() && tamanhos.get(0) != null) {
            int tamDeclarado = tamanhos.get(0);
            int tamInicial = initVetor.expressao().size();
            if (tamInicial > tamDeclarado) {
                System.err.println("Erro Semântico (Linha " + linha +
                    "): O vetor '" + nomeVar + "' foi declarado com tamanho " + tamDeclarado +
                    " mas a inicialização contém " + tamInicial + " elementos.");
                erros = true;
            }
        }
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
        verificarAtribuicao(ctx.variavel(), ctx.valorAtribuicao(), ctx.start.getLine());
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

        // Se o valor à direita é uma inicialização de vetor
        if (valor.inicializacaoVetor() != null) {
            // Só é permitido se o lado esquerdo for um slice
            if (!sel.dimensao().isEmpty() && contemSlice(sel)) {
                String tipoBase = simbolo.getTipoBase();  // tipo do elemento
                AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
                for (TarbaloParser.ExpressaoContext exprCtx : valor.inicializacaoVetor().expressao()) {
                    String tipoElem = avaliador.visit(exprCtx);
                    if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                        System.err.println("Erro Semântico (Linha " + linha + "): Elemento da inicialização incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                        erros = true;
                        return;
                    }
                }
                return;
            } else {
                System.err.println("Erro Semântico (Linha " + linha + "): Inicialização de vetor só pode ser usada em atribuições a slices.");
                erros = true;
                return;
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

        if (!saoCompativeis(tipoVar, tipoExpr) && !tipoExpr.equals("erro")) {
            System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na atribuição. A variável '" + nomeVar + "' é do tipo '" + tipoVar + "', mas a expressão resulta em '" + tipoExpr + "'.");
            erros = true;
        }
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
    }

    /* ---------------------------------------------------------------------
     * LEITURA
     * --------------------------------------------------------------------- */
    @Override
    public void enterLeitura(TarbaloParser.LeituraContext ctx) {
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
            }
        }
    }

    /* ---------------------------------------------------------------------
     * USO DE VARIÁVEIS EM EXPRESSÕES
     * --------------------------------------------------------------------- */
    @Override
    public void enterOperando(TarbaloParser.OperandoContext ctx) {
        // Verificação de variável declarada
        String nomeVar = obterNome(ctx);
        if (nomeVar != null && !tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                "): A variável '" + nomeVar + "' foi utilizada, mas não foi declarada!");
            erros = true;
        }
    }

    /* ---------------------------------------------------------------------
     * FUNÇÕES
     * --------------------------------------------------------------------- */
    @Override
    public void enterDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        String nome = ctx.ID().getText();
        int linha = ctx.start.getLine();

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
    }

    @Override
    public void exitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        String nome = ctx.ID().getText();
        tabela.fecharEscopo(); // fecha o escopo da função (inclui parâmetros e corpo)
        dentroDeFuncao = false;
        pilhaRetornos.pop();
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
            // Cria símbolo de vetor com as informações completas
            Simbolo param = new Simbolo(nome, tipoBase, dim, dinamico, tamanhos);
            tabela.adicionar(param);
        } else {
            tabela.adicionar(new Simbolo(nome, tipoBase));
        }
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
    // ---------- ENQUANTO (laço) ----------
    @Override
    public void enterCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        nivelLaco++;
    }
    @Override
    public void exitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        nivelLaco--;
    }

    // ---------- FACA-ENQUANTO (laço + controle) ----------
    @Override
    public void enterCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        nivelLaco++;
    }
    @Override
    public void exitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        nivelLaco--;
    }

    // ---------- PARA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdPara(TarbaloParser.CmdParaContext ctx) {
        nivelLaco++;
        tabela.abrirEscopo();
    }
    @Override
    public void exitCmdPara(TarbaloParser.CmdParaContext ctx) {
        tabela.fecharEscopo();
        nivelLaco--;
    }

    // ---------- PARACADA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        nivelLaco++;
        tabela.abrirEscopo();
        // O resto do método permanece igual: declaração da variável de iteração, etc.
        String nome = ctx.ID().getText();
        String tipo = ctx.tipoVariavel().getText();
        if (tabela.existeNoEscopoAtual(nome)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nome + "' já declarada neste escopo.");
            erros = true;
        } else {
            tabela.adicionar(new Simbolo(nome, tipo));
        }

        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
        String tipoExpr = avaliador.visit(ctx.expressao());
        if (!tipoExpr.equals("erro") && !tipoExpr.contains("[]")) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() +
                "): A expressão em 'pancada' deve ser um vetor, mas é do tipo '" + tipoExpr + "'.");
            erros = true;
        }
    }

    @Override
    public void exitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        tabela.fecharEscopo();
        nivelLaco--;
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
        // int[] cabe em qbd[], int[][] cabe em qbd[][], etc.
        if (tipoVar.startsWith("qbd") && tipoExpr.startsWith("int")) {
            String sufixoVar = tipoVar.substring(3);
            String sufixoExpr = tipoExpr.substring(3);
            if (sufixoVar.equals(sufixoExpr) && sufixoVar.startsWith("[")) return true;
        }
        return false;
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
    // Caminho: expressao → expressaoXor → expressaoE → expressaoNegacao →
    //          expressaoRelacional → expressaoAditiva → expressaoConcatenacao →
    //          expressaoMultiplicativa → operando → NUM
    private Integer extrairNumero(TarbaloParser.ExpressaoContext expr) {
        if (expr.expressaoXor().size() != 1) return null;
        var xor = expr.expressaoXor(0);
        if (xor.expressaoE().size() != 1) return null;
        var e = xor.expressaoE(0);
        if (e.expressaoNegacao().size() != 1) return null;
        var neg = e.expressaoNegacao(0);
        if (neg.NAO() != null) return null;
        var rel = neg.expressaoRelacional();
        if (rel.operadorRelacional() != null) return null;
        if (rel.expressaoAditiva().size() != 1) return null;
        var ad = rel.expressaoAditiva(0);
        if (ad.expressaoConcatenacao().size() != 1) return null;
        var conc = ad.expressaoConcatenacao(0);
        if (conc.expressaoMultiplicativa().size() != 1) return null;
        var mul = conc.expressaoMultiplicativa(0);
        if (mul.operando().size() != 1) return null;
        var op = mul.operando(0);
        if (op.NUM() != null) return Integer.parseInt(op.NUM().getText());
        return null;
    }

    private boolean saoCompativeisRelacionais(String tipoEsq, String tipoDir) {
        if (tipoEsq.equals("erro") || tipoDir.equals("erro")) return true;
        if (tipoEsq.equals("desconhecido") || tipoDir.equals("desconhecido")) return true;

        // Números (int e dec) são compatíveis entre si
        boolean esqNumerico = tipoEsq.equals("int") || tipoEsq.equals("qbd");
        boolean dirNumerico = tipoDir.equals("int") || tipoDir.equals("qbd");
        if (esqNumerico && dirNumerico) return true;

        // Tipos iguais (texto, car, logico)
        return tipoEsq.equals(tipoDir);
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
                    return s.getTipo();
                }
                // Se tem dimensão, é acesso a vetor
                if (s.getCategoria() != Simbolo.Categoria.VETOR) {
                    System.err.println("Erro Semântico (Linha " + linha + "): '" + nomeVar + "' não é um vetor e não pode ser indexado.");
                    erros = true;
                    return "erro";
                }
                // Verifica se há slice
                boolean isSlice = false;
                for (TarbaloParser.DimensaoContext dim : ctx.variavel().dimensao()) {
                    if (dim.PONTOPONTO() != null) {
                        isSlice = true;
                        break;
                    }
                }
                return isSlice ? s.getTipoBase() + "[]" : s.getTipoBase();
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
            if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                if (!tipoAcum.equals("erro") && !tipoAcum.equals("desconhecido") &&
                    !tipo.equals("erro") && !tipo.equals("desconhecido")) {
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
                if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                    if (!tipoAcum.equals("erro") && !tipoAcum.equals("desconhecido") &&
                        !tipo.equals("erro") && !tipo.equals("desconhecido")) {
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
            // Sem operador OU: delegar ao único filho
            if (ctx.expressaoXor().size() == 1) {
                return visit(ctx.expressaoXor(0));
            }
            // Com operador OU: validar que ambos os lados são lógicos
            String tipoAcum = visit(ctx.expressaoXor(0));
            for (int i = 1; i < ctx.expressaoXor().size(); i++) {
                String tipo = visit(ctx.expressaoXor(i));
                if (!tipoAcum.equals("lgc") || !tipo.equals("lgc")) {
                    if (!tipoAcum.equals("erro") && !tipoAcum.equals("desconhecido")
                    && !tipo.equals("erro") && !tipo.equals("desconhecido")) {
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
                // Visita subexpressões para detectar erros internos
                for (TarbaloParser.ExpressaoMultiplicativaContext sub : ctx.expressaoMultiplicativa()) {
                    visit(sub);
                }
                return "txt";
            }
            return visit(ctx.expressaoMultiplicativa(0));
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