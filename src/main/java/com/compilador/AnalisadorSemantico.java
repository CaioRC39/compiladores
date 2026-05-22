package com.compilador;

import com.compilador.tarbalo.TarbaloBaseListener;
import com.compilador.tarbalo.TarbaloParser;
import com.compilador.tarbalo.TarbaloBaseVisitor;
import java.util.*;

public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela = new TabelaSimbolos();

    // Para controle de contexto (funções, laços)
    private Deque<String> pilhaRetornos = new ArrayDeque<>(); // tipo de retorno da função atual
    private int nivelLaco = 0;
    private boolean dentroDeFuncao = false;

    // Para tratamento especial do bloco da função (compartilhar escopo com parâmetros)
    private boolean blocoFuncaoAtual = false;

    public TabelaSimbolos getTabela() {
        return tabela;
    }

    /* ---------------------------------------------------------------------
     * ESCOPOS
     * --------------------------------------------------------------------- */
    @Override
    public void enterBloco(TarbaloParser.BlocoContext ctx) {
        // Não abrimos escopo automaticamente para o bloco de função
        if (!blocoFuncaoAtual) {
            tabela.abrirEscopo();
        } else {
            // O escopo já foi aberto pela função (enterDeclaracaoFuncao)
            blocoFuncaoAtual = false; // próximo bloco comum volta a abrir escopo
        }
    }

    @Override
    public void exitBloco(TarbaloParser.BlocoContext ctx) {
        // Fechamos escopo apenas se não for o escopo da função (que será fechado em exitDeclaracaoFuncao)
        if (!dentroDeFuncao || pilhaRetornos.isEmpty()) {
            tabela.fecharEscopo();
        }
        // Se for o bloco da função, o fecho é controlado por exitDeclaracaoFuncao
    }

    /* ---------------------------------------------------------------------
     * DECLARAÇÃO DE VARIÁVEIS
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx) {
        String tipoVar = ctx.tipoVariavel().getText();
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();

        if (tabela.existeNoEscopoAtual(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + linha + "): A variável '" + nomeVar + "' já foi declarada neste escopo!");
            return;
        }

        tabela.adicionar(new Simbolo(nomeVar, tipoVar));

        // Verifica tipo da inicialização, se houver
        if (ctx.ATRIBUICAO() != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            String tipoExpr = avaliador.visit(ctx.expressao());

            if (!saoCompativeis(tipoVar, tipoExpr) && !tipoExpr.equals("erro")) {
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
            System.err.println("Erro Semântico (Linha " + linha + "): O vetor '" + nomeVar + "' já foi declarado neste escopo!");
            return;
        }

        // Obter dimensão
        int dim = 1;
        boolean dinamico = false;
        List<Integer> tamanhos = null;

        // Aceder à primeira (e única) dimensão
        if (ctx.dimensaoVetor() != null && !ctx.dimensaoVetor().isEmpty()) {
            TarbaloParser.DimensaoVetorContext dimCtx = ctx.dimensaoVetor(0);
            if (dimCtx.NUM() == null) {
                dinamico = true;
            } else {
                tamanhos = new ArrayList<>();
                tamanhos.add(Integer.parseInt(dimCtx.NUM().getText()));
            }
        }

        Simbolo simbolo = new Simbolo(nomeVar, tipoBase, dim, dinamico, tamanhos);
        tabela.adicionar(simbolo);

        // Verifica inicialização do vetor
        if (ctx.inicializacaoVetor() != null) {
            // A lista de inicialização deve ter tipos compatíveis com tipoBase
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            for (TarbaloParser.ExpressaoContext exprCtx : ctx.inicializacaoVetor().expressao()) {
                String tipoElem = avaliador.visit(exprCtx);
                if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Elemento do vetor '" + nomeVar + "' de tipo incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                    break;
                }
            }
        }
    }

    /* ---------------------------------------------------------------------
     * ATRIBUIÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void exitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        String nomeVar = obterNome(ctx.selecaoVariavel());
        int linha = ctx.start.getLine();

        if (nomeVar == null) return;

        if (!tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + linha + "): Tentativa de atribuição em variável não declarada: '" + nomeVar + "'");
            return;
        }

        Simbolo simbolo = tabela.buscar(nomeVar);
        String tipoVar = simbolo.getTipo();

        // Se for acesso a vetor (ex: v[0]), o tipo é o tipo base, senão é o próprio tipo
        if (ctx.selecaoVariavel().acessoVetor() != null) {
            // v[0] é do tipo base (sem "[]")
            if (simbolo.getCategoria() == Simbolo.Categoria.VETOR) {
                tipoVar = simbolo.getTipo(); // tipo já sem []
            }
        }

        AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
        String tipoExpr = avaliador.visit(ctx.expressao());

        if (!saoCompativeis(tipoVar, tipoExpr) && !tipoExpr.equals("erro")) {
            System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível na atribuição. A variável '" + nomeVar + "' é do tipo '" + tipoVar + "', mas a expressão resulta em '" + tipoExpr + "'.");
        }
    }

    /* ---------------------------------------------------------------------
     * LEITURA
     * --------------------------------------------------------------------- */
    @Override
    public void enterLeitura(TarbaloParser.LeituraContext ctx) {
        String nomeVar = obterNome(ctx.selecaoVariavel());
        if (nomeVar != null && !tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nomeVar + "' usada no comando 'leia' não foi declarada!");
        }
    }

    /* ---------------------------------------------------------------------
     * USO DE VARIÁVEIS EM EXPRESSÕES
     * --------------------------------------------------------------------- */
    @Override
    public void enterOperando(TarbaloParser.OperandoContext ctx) {
        String nomeVar = obterNome(ctx);
        if (nomeVar != null && !tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): A variável '" + nomeVar + "' foi utilizada, mas não foi declarada!");
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
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome + "' já declarada neste escopo.");
            // continua para evitar mais erros, mas o símbolo antigo permanece
        }

        String tipoRetorno = ctx.tipoRetorno().getText();

        // Coletar tipos dos parâmetros (serão adicionados no exitParametro)
        List<String> tiposParametros = new ArrayList<>();
        if (ctx.parametros() != null) {
            for (TarbaloParser.ParametroContext p : ctx.parametros().parametro()) {
                String tipo = p.tipoVariavel().getText();
                if (!p.variavel().dimensaoVetor().isEmpty()) {
                    tipo += "[]";
                }
                tiposParametros.add(tipo);
            }
        }

        Simbolo func = new Simbolo(nome, tipoRetorno, tiposParametros);
        tabela.adicionar(func);

        // Abrir escopo para parâmetros e corpo da função
        tabela.abrirEscopo();
        dentroDeFuncao = true;
        pilhaRetornos.push(tipoRetorno);
        blocoFuncaoAtual = true; // próximo bloco usará este escopo
    }

    @Override
    public void exitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        tabela.fecharEscopo(); // fecha o escopo da função (inclui parâmetros e corpo)
        dentroDeFuncao = false;
        pilhaRetornos.pop();
    }

    @Override
    public void exitParametro(TarbaloParser.ParametroContext ctx) {
        String tipo = ctx.tipoVariavel().getText();
        String nome = ctx.variavel().ID().getText();

        // Se o parâmetro for vetor
        if (!ctx.variavel().dimensaoVetor().isEmpty()) {
            tipo += "[]";
            // Consideramos vetor (pode ser dinâmico)
            Simbolo param;
            if (ctx.variavel().dimensaoVetor(0).NUM() == null) {
                param = new Simbolo(nome, tipo, 1, true, null);
            } else {
                int tam = Integer.parseInt(ctx.variavel().dimensaoVetor(0).NUM().getText());
                param = new Simbolo(nome, tipo, 1, false, List.of(tam));
            }
            tabela.adicionar(param);
        } else {
            tabela.adicionar(new Simbolo(nome, tipo));
        }
    }

    /* ---------------------------------------------------------------------
     * RETORNO
     * --------------------------------------------------------------------- */
    @Override
    public void enterRetorno(TarbaloParser.RetornoContext ctx) {
        if (!dentroDeFuncao) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'retorne' fora de uma função.");
            return;
        }

        if (ctx.expressao() != null) {
            String tipoRet = pilhaRetornos.peek();
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(ctx.start.getLine());
            String tipoExpr = avaliador.visit(ctx.expressao());
            if (!tipoExpr.equals("erro") && !saoCompativeis(tipoRet, tipoExpr)) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Tipo de retorno incompatível. Função espera '" + tipoRet + "', mas retornou '" + tipoExpr + "'.");
            }
        } else {
            String tipoRet = pilhaRetornos.peek();
            if (!tipoRet.equals("vazio")) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Função deve retornar um valor do tipo '" + tipoRet + "', mas 'retorne' está vazio.");
            }
        }
    }

    /* ---------------------------------------------------------------------
     * LAÇOS (pare, continuar)
     * --------------------------------------------------------------------- */
    @Override
    public void enterCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) { nivelLaco++; }
    @Override
    public void exitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) { nivelLaco--; }

    @Override
    public void enterCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) { nivelLaco++; }
    @Override
    public void exitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) { nivelLaco--; }

    @Override
    public void enterCmdPara(TarbaloParser.CmdParaContext ctx) {
        nivelLaco++;
        tabela.abrirEscopo(); // NOVO: Impede que as variáveis do 'para' vazem para fora
    }

    @Override
    public void exitCmdPara(TarbaloParser.CmdParaContext ctx) {
        tabela.fecharEscopo(); // NOVO: Limpa o escopo ao sair do 'para'
        nivelLaco--;
    }

    @Override
    public void enterCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        nivelLaco++;
        // Escopo para a variável de iteração
        tabela.abrirEscopo();
        String nome = ctx.ID().getText();
        String tipo = ctx.tipoVariavel().getText();
        if (tabela.existeNoEscopoAtual(nome)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nome + "' já declarada neste escopo.");
        } else {
            tabela.adicionar(new Simbolo(nome, tipo));
        }
    }
    @Override
    public void exitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        tabela.fecharEscopo(); // fecha escopo da variável
        nivelLaco--;
    }

    // Tratamento da inicialização com declaração no 'para' (var tipo id : valor)
    @Override
    public void enterAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx) {
        // Se a inicialização do 'para' for uma declaração, o contexto será visitado?
        // A regra 'inicializacaoPara' chama 'atribuicaoPara' ou declaração.
        // Precisamos detetar se é declaração. Usamos enterInicializacaoPara.
    }

    @Override
    public void enterInicializacaoPara(TarbaloParser.InicializacaoParaContext ctx) {
        if (ctx.VARIAVEL() != null) {
            // É uma declaração de variável dentro do para
            String nome = ctx.ID().getText();
            String tipo = ctx.tipoVariavel().getText();
            if (tabela.existeNoEscopoAtual(nome)) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nome + "' já declarada neste escopo.");
            } else {
                tabela.adicionar(new Simbolo(nome, tipo));
            }
        }
    }

    @Override
    public void enterPare(TarbaloParser.PareContext ctx) {
        if (nivelLaco == 0) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'pare' fora de um laço.");
        }
    }

    @Override
    public void enterContinuar(TarbaloParser.ContinuarContext ctx) {
        if (nivelLaco == 0) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Comando 'continuar' fora de um laço.");
        }
    }

    /* ---------------------------------------------------------------------
     * CHAMADA DE FUNÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void enterChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx) {
        String nome = ctx.ID().getText();
        // Se o nome não está na tabela, pode ser função nativa – assumimos válida.
        // Se estiver na tabela e não for função, seria erro, mas não diferenciamos aqui.
        Simbolo s = tabela.buscar(nome);
        if (s != null && s.getCategoria() != Simbolo.Categoria.FUNCAO) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): '" + nome + "' não é uma função.");
            return;
        }

        if (s != null && s.getCategoria() == Simbolo.Categoria.FUNCAO) {
            // Verificar número de argumentos
            int numArgs = ctx.argumentos() != null ? ctx.argumentos().expressao().size() : 0;
            int numParams = s.getTiposParametros().size();
            if (numArgs != numParams) {
                System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Função '" + nome + "' espera " + numParams + " parâmetro(s), mas foi chamada com " + numArgs + ".");
            }
            // Poderíamos verificar tipos, mas vamos simplificar.
        }
    }

    /* ---------------------------------------------------------------------
     * MÉTODOS AUXILIARES
     * --------------------------------------------------------------------- */
    private boolean saoCompativeis(String tipoVar, String tipoExpr) {
        if (tipoVar.equals("desconhecido") || tipoExpr.equals("desconhecido")) return true;
        if (tipoExpr.equals("erro")) return false;
        if (tipoVar.equals(tipoExpr)) return true;
        if (tipoVar.equals("dec") && tipoExpr.equals("int")) return true; // int cabe em dec
        return false;
    }

    private String obterNome(TarbaloParser.SelecaoVariavelContext ctx) {
        if (ctx == null) return null;
        if (ctx.ID() != null) return ctx.ID().getText();
        if (ctx.acessoVetor() != null) return ctx.acessoVetor().ID().getText();
        return null;
    }

    private String obterNome(TarbaloParser.OperandoContext ctx) {
        if (ctx.ID() != null) return ctx.ID().getText();
        if (ctx.acessoVetor() != null) return ctx.acessoVetor().ID().getText();
        return null;
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
        public String visitOperando(TarbaloParser.OperandoContext ctx) {
            if (ctx.NUM() != null) return "int";
            if (ctx.NUMDEC() != null) return "dec";
            if (ctx.STRING() != null) return "texto";
            if (ctx.CHAR() != null) return "car";
            if (ctx.VERDADEIRO() != null || ctx.FALSO() != null) return "logico";

            if (ctx.ID() != null) {
                String tipo = tabela.obterTipo(ctx.ID().getText());
                return tipo != null ? tipo : "desconhecido";
            }

            if (ctx.acessoVetor() != null) {
                String nomeVetor = ctx.acessoVetor().ID().getText();
                String tipo = tabela.obterTipo(nomeVetor);
                if (tipo != null && tipo.endsWith("[]")) {
                    return tipo.replace("[]", ""); // elemento do vetor
                }
                return tipo != null ? tipo : "desconhecido";
            }

            if (ctx.expressao() != null) return visit(ctx.expressao());

            if (ctx.chamadaFuncao() != null) {
                String nomeFunc = ctx.chamadaFuncao().ID().getText();
                Simbolo s = tabela.buscar(nomeFunc);
                if (s != null && s.getCategoria() == Simbolo.Categoria.FUNCAO) {
                    return s.getTipoRetorno();
                }
                return "desconhecido";
            }

            if (ctx.inicializacaoVetor() != null) {
                // Retorna o tipo do primeiro elemento + "[]"
                if (!ctx.inicializacaoVetor().expressao().isEmpty()) {
                    return visit(ctx.inicializacaoVetor().expressao(0)) + "[]";
                }
                return "desconhecido[]";
            }

            // Removido o bloco de inicializacaoVetor
            return "desconhecido";
        }

        @Override
        public String visitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx) {
            if (ctx.operadorRelacional() != null) {
                // Valida os dois lados (pode gerar erros internos)
                super.visitExpressaoRelacional(ctx);
                return "logico";
            }
            return super.visitExpressaoRelacional(ctx);
        }

        @Override
        protected String aggregateResult(String aggregate, String nextResult) {
            if (aggregate == null) return nextResult;
            if (nextResult == null) return aggregate;
            if (aggregate.equals("erro") || nextResult.equals("erro")) return "erro";
            if (aggregate.equals("desconhecido") || nextResult.equals("desconhecido")) return "desconhecido";
            if (aggregate.equals(nextResult)) return aggregate;
            if ((aggregate.equals("int") && nextResult.equals("dec")) ||
                    (aggregate.equals("dec") && nextResult.equals("int"))) {
                return "dec";
            }
            System.err.println("Erro Semântico (Linha " + linha + "): Tipos incompatíveis na operação ('" + aggregate + "' com '" + nextResult + "')");
            return "erro";
        }
    }
}