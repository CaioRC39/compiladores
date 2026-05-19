public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela = new TabelaSimbolos();

    /* ---------------------------------------------------------------------
     * 1. DECLARAÇÃO DE VARIÁVEIS
     * Regra: declaracaoVariavel : tipoVariavel variavel (VIRGULA variavel)* PONTO ;
     * --------------------------------------------------------------------- */
    @Override
    public void exitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx) {
        // Obtém o tipo (int, dec, texto, car, booleano)
        String tipo = ctx.tipoVariavel().getText();

        // Percorre todas as variáveis declaradas na mesma linha
        for (TarbaloParser.VariavelContext varCtx : ctx.variavel()) {
            String nomeVar = varCtx.ID().getText();

            // Verifica se a variável já existe na tabela de símbolos
            if (tabela.existe(nomeVar)) {
                int linha = varCtx.start.getLine();
                System.err.println("Erro Semântico (Linha " + linha + "): A variável '" + nomeVar + "' já foi declarada!");
            } else {
                tabela.adicionar(nomeVar, tipo);
            }
        }
    }

    /* ---------------------------------------------------------------------
     * 2. ATRIBUIÇÃO
     * Regra: atribuicao : destinoAtribuicao (ATRIBUICAO | operador) expressao PONTO ;
     * --------------------------------------------------------------------- */
    @Override
    public void enterAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        TarbaloParser.DestinoAtribuicaoContext destino = ctx.destinoAtribuicao();
        String nomeVar = null;

        // O destino pode ser uma variável simples (ID) ou um vetor
        if (destino.ID() != null) {
            nomeVar = destino.ID().getText();
        } else if (destino.acessoVetor() != null) {
            nomeVar = destino.acessoVetor().ID().getText();
        }

        // Verifica se a variável que está recebendo o valor foi declarada
        if (nomeVar != null && !tabela.existe(nomeVar)) {
            int linha = ctx.start.getLine();
            System.err.println("Erro Semântico (Linha " + linha + "): Tentativa de atribuição em variável não declarada: '" + nomeVar + "'");
        }
    }

    /* ---------------------------------------------------------------------
     * 3. LEITURA (ENTRADA DE DADOS)
     * Regra: leitura: LEIA ABREPARENTE variavel FECHAPARENTE PONTO ;
     * --------------------------------------------------------------------- */
    @Override
    public void enterLeitura(TarbaloParser.LeituraContext ctx) {
        String nomeVar = ctx.variavel().ID().getText();

        if (!tabela.existe(nomeVar)) {
            int linha = ctx.start.getLine();
            System.err.println("Erro Semântico (Linha " + linha + "): Variável '" + nomeVar + "' usada no comando de leitura 'leia' não foi declarada!");
        }
    }

    /* ---------------------------------------------------------------------
     * 4. USO DE VARIÁVEIS EM EXPRESSÕES E CÁLCULOS
     * Regra: operando : NUM | NUMDEC | STRING | ... | ID | acessoVetor | ... ;
     * --------------------------------------------------------------------- */
    @Override
    public void enterOperando(TarbaloParser.OperandoContext ctx) {
        String nomeVar = null;

        // Verifica se o operando é uma variável simples
        if (ctx.ID() != null) {
            nomeVar = ctx.ID().getText();
        }
        // Verifica se o operando é uma posição de um vetor
        else if (ctx.acessoVetor() != null) {
            nomeVar = ctx.acessoVetor().ID().getText();
        }

        // Se for um identificador, valida a existência
        if (nomeVar != null && !tabela.existe(nomeVar)) {
            int linha = ctx.start.getLine();
            System.err.println("Erro Semântico (Linha " + linha + "): A variável '" + nomeVar + "' foi utilizada, mas não foi declarada!");
        }
    }
}