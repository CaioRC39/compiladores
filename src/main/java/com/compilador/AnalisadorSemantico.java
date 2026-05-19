package com.compilador;

import com.compilador.tarbalo.TarbaloBaseListener;
import com.compilador.tarbalo.TarbaloParser;
import com.compilador.tarbalo.TarbaloBaseVisitor;

public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela = new TabelaSimbolos();

    public TabelaSimbolos getTabela() {
        return tabela;
    }

    // Método auxiliar para verificar se a variável pode receber o valor
    private boolean saoCompativeis(String tipoVar, String tipoExpr) {
        if (tipoExpr.equals("desconhecido") || tipoVar.equals("desconhecido")) return true;
        if (tipoExpr.equals("erro")) return false; // O erro já foi impresso
        if (tipoVar.equals(tipoExpr)) return true; // Tipos exatos (int e int, etc)
        if (tipoVar.equals("dec") && tipoExpr.equals("int")) return true; // int cabe em decimal
        return false;
    }

    /* ---------------------------------------------------------------------
     * 1. DECLARAÇÃO DE VARIÁVEIS
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx) {
        String tipoVariavel = ctx.tipoVariavel().getText();
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();

        if (tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + linha + "): A variável '" + nomeVar + "' já foi declarada!");
        } else {
            tabela.adicionar(nomeVar, tipoVariavel);
        }

        if (ctx.ATRIBUICAO() != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            String tipoExpressao = avaliador.visit(ctx.expressao());

            if (!saoCompativeis(tipoVariavel, tipoExpressao) && !tipoExpressao.equals("erro")) {
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível. A variável '" + nomeVar + "' é do tipo '" + tipoVariavel + "', mas recebeu um valor do tipo '" + tipoExpressao + "'.");
            }
        }
    }

    @Override
    public void exitDeclaracaoVetor(TarbaloParser.DeclaracaoVetorContext ctx) {
        String tipoVariavel = ctx.tipoVariavel().getText() + "[]"; // Marca internamente como vetor
        String nomeVar = ctx.ID().getText();
        int linha = ctx.start.getLine();

        if (tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + linha + "): O vetor '" + nomeVar + "' já foi declarado!");
        } else {
            tabela.adicionar(nomeVar, tipoVariavel);
        }
    }

    /* ---------------------------------------------------------------------
     * 2. ATRIBUIÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void exitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        String nomeVar = null;
        if (ctx.selecaoVariavel().ID() != null) {
            nomeVar = ctx.selecaoVariavel().ID().getText();
        } else if (ctx.selecaoVariavel().acessoVetor() != null) {
            nomeVar = ctx.selecaoVariavel().acessoVetor().ID().getText();
        }

        int linha = ctx.start.getLine();

        if (nomeVar != null) {
            if (!tabela.existe(nomeVar)) {
                System.err.println("Erro Semântico (Linha " + linha + "): Tentativa de atribuição em variável não declarada: '" + nomeVar + "'");
                return;
            }

            // Descobre o tipo da variável
            String tipoVariavel = tabela.obterTipo(nomeVar);

            if (ctx.selecaoVariavel().acessoVetor() != null && tipoVariavel != null && tipoVariavel.endsWith("[]")) {
                tipoVariavel = tipoVariavel.replace("[]", "");
            }

            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            String tipoExpressao = avaliador.visit(ctx.expressao());

            if (!saoCompativeis(tipoVariavel, tipoExpressao) && !tipoExpressao.equals("erro")) {
                System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível. A variável '" + nomeVar + "' é do tipo '" + tipoVariavel + "', mas a expressão resulta em '" + tipoExpressao + "'.");
            }
        }
    }

    /* ---------------------------------------------------------------------
     * 3. LEITURA
     * --------------------------------------------------------------------- */
    @Override
    public void enterLeitura(TarbaloParser.LeituraContext ctx) {
        String nomeVar = null;
        if (ctx.selecaoVariavel().ID() != null){
            nomeVar = ctx.selecaoVariavel().ID().getText();
        } else if (ctx.selecaoVariavel().acessoVetor() != null){
            nomeVar = ctx.selecaoVariavel().acessoVetor().ID().getText();
        }

        if (nomeVar != null && !tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): Variável '" + nomeVar + "' usada no 'leia' não foi declarada!");
        }
    }

    /* ---------------------------------------------------------------------
     * 4. USO DE VARIÁVEIS
     * --------------------------------------------------------------------- */
    @Override
    public void enterOperando(TarbaloParser.OperandoContext ctx) {
        String nomeVar = null;
        if (ctx.ID() != null) {
            nomeVar = ctx.ID().getText();
        } else if (ctx.acessoVetor() != null) {
            nomeVar = ctx.acessoVetor().ID().getText();
        }

        if (nomeVar != null && !tabela.existe(nomeVar)) {
            System.err.println("Erro Semântico (Linha " + ctx.start.getLine() + "): A variável '" + nomeVar + "' foi utilizada, mas não foi declarada!");
        }
    }

    /* ---------------------------------------------------------------------
     * 5. PARÂMETROS DE FUNÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void exitParametro(TarbaloParser.ParametroContext ctx) {
        String tipoVariavel = ctx.tipoVariavel().getText();
        String nomeVar = ctx.variavel().ID().getText();

        // Se o parâmetro for um vetor (ex: int notas[])
        if (ctx.variavel().dimensaoVetor() != null && !ctx.variavel().dimensaoVetor().isEmpty()) {
            tipoVariavel += "[]";
        }

        // Adiciona o parâmetro na tabela de símbolos para ele poder ser usado dentro da função
        if (!tabela.existe(nomeVar)) {
            tabela.adicionar(nomeVar, tipoVariavel);
        }
    }

    /* ======================================================================
     * CLASSE INTERNA: AVALIADOR DE TIPOS
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
                    return tipo.replace("[]", ""); // Devolve apenas 'int', 'texto', etc.
                }
                return tipo != null ? tipo : "desconhecido";
            }

            if (ctx.expressao() != null) return visit(ctx.expressao());
            return "desconhecido";
        }

        // Se a expressão usa (<, >), o tipo da expressão vira "logico"
        @Override
        public String visitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx) {
            if (ctx.operadorRelacional() != null) {
                super.visitExpressaoRelacional(ctx); // Captura erros internos
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

            System.err.println("Erro Semântico (Linha " + linha + "): Tipos incompatíveis na operação matemática ou lógica ('" + aggregate + "' com '" + nextResult + "')");
            return "erro";
        }
    }
}