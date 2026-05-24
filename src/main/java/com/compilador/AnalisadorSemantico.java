package com.compilador;

import com.compilador.tarbalo.TarbaloBaseListener;
import com.compilador.tarbalo.TarbaloParser;
import com.compilador.tarbalo.TarbaloBaseVisitor;
import java.util.*;

import org.antlr.v4.runtime.tree.ParseTree;

public class AnalisadorSemantico extends TarbaloBaseListener {

    private TabelaSimbolos tabela = new TabelaSimbolos();

    // Para controle de contexto (funções, laços)
    private Deque<String> pilhaRetornos = new ArrayDeque<>(); // tipo de retorno da função atual
    private Map<ParseTree, Simbolo> mapaResolucao = new HashMap<>();
    private int nivelLaco = 0;
    private int nivelBlocoControle = 0;   // quantos comandos de controle (se, enquanto, ...) estamos dentro
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
        int dim = ctx.dimensaoVetor().size();
        List<Integer> tamanhos = new ArrayList<>();
        boolean dinamico = false;

        for (TarbaloParser.DimensaoVetorContext dimCtx : ctx.dimensaoVetor()) {
            if (dimCtx.NUM() == null) {
                dinamico = true;
                tamanhos.add(null);   // dimensão dinâmica (tamanho desconhecido)
            } else {
                tamanhos.add(Integer.parseInt(dimCtx.NUM().getText()));
            }
        }

        Simbolo simbolo = new Simbolo(nomeVar, tipoBase, dim, dinamico, tamanhos);
        tabela.adicionar(simbolo);

        // Verifica inicialização do vetor
        if (ctx.inicializacaoVetor() != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            for (TarbaloParser.ExpressaoContext exprCtx : ctx.inicializacaoVetor().expressao()) {
                String tipoElem = avaliador.visit(exprCtx);
                if (!saoCompativeis(tipoBase, tipoElem) && !tipoElem.equals("erro")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Elemento do vetor '" + nomeVar + "' de tipo incompatível. Esperado '" + tipoBase + "', mas recebeu '" + tipoElem + "'.");
                    break;
                }
            }
        }

        if (ctx.inicializacaoVetor() != null && !tamanhos.isEmpty() && tamanhos.get(0) != null) {
            int tamDeclarado = tamanhos.get(0);
            int tamInicial = ctx.inicializacaoVetor().expressao().size();
            if (tamInicial > tamDeclarado) {
                System.err.println("Erro Semântico (Linha " + linha +
                    "): O vetor '" + nomeVar + "' foi declarado com tamanho " + tamDeclarado +
                    " mas a inicialização contém " + tamInicial + " elementos.");
                erros = true;
            }
        }
    }

    /* ---------------------------------------------------------------------
     * ATRIBUIÇÃO
     * --------------------------------------------------------------------- */
    @Override
    public void exitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        verificarAtribuicao(ctx.selecaoVariavel(), ctx.valorAtribuicao(), ctx.start.getLine());
    }

    @Override
    public void exitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx) {
        verificarAtribuicao(ctx.selecaoVariavel(), ctx.valorAtribuicao(), ctx.start.getLine());
    }

    // método auxiliar reutilizado em exitAtribuicao e exitAtribuicaoPara
    private void verificarAtribuicao(TarbaloParser.SelecaoVariavelContext sel,
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
            if (sel.acessoVetor() != null && contemSlice(sel)) {
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
        if (sel.acessoVetor() != null && !contemSlice(sel)) {
            tipoVar = simbolo.getTipoBase();   // acesso indexado normal
        } else if (sel.acessoVetor() != null && contemSlice(sel)) {
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
        String nomeVar = obterNome(ctx.selecaoVariavel());
        int linha = ctx.start.getLine();
        if (nomeVar == null) return;

        Simbolo s = tabela.buscar(nomeVar);
        if (s == null) return;

        // Proibir vetor inteiro
        if (s.getCategoria() == Simbolo.Categoria.VETOR && ctx.selecaoVariavel().acessoVetor() == null) {
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
        String nomeVar = obterNome(ctx.selecaoVariavel());
        if (nomeVar != null) {
            Simbolo s = tabela.buscar(nomeVar);
            // proibir leitura de vetor inteiro (ex.: leia(v))
            if (s != null && s.getCategoria() == Simbolo.Categoria.VETOR &&
                ctx.selecaoVariavel().acessoVetor() == null) {
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
                if (!p.variavel().dimensaoVetor().isEmpty()) {
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

        List<TarbaloParser.DimensaoVetorContext> dimensoes = ctx.variavel().dimensaoVetor();
        if (!dimensoes.isEmpty()) {
            int dim = dimensoes.size();
            List<Integer> tamanhos = new ArrayList<>();
            boolean dinamico = false;
            for (TarbaloParser.DimensaoVetorContext dimCtx : dimensoes) {
                if (dimCtx.NUM() == null) {
                    dinamico = true;
                    tamanhos.add(null);
                } else {
                    tamanhos.add(Integer.parseInt(dimCtx.NUM().getText()));
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
    // ---------- SE (não é laço, apenas bloco de controle) ----------
    @Override public void enterCmdSe(TarbaloParser.CmdSeContext ctx) { nivelBlocoControle++; }
    @Override public void exitCmdSe(TarbaloParser.CmdSeContext ctx) { nivelBlocoControle--; }

    // ---------- ENQUANTO (laço + controle) ----------
    @Override
    public void enterCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        nivelLaco++;
        nivelBlocoControle++;
    }
    @Override
    public void exitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        nivelLaco--;
        nivelBlocoControle--;
    }

    // ---------- FACA-ENQUANTO (laço + controle) ----------
    @Override
    public void enterCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        nivelLaco++;
        nivelBlocoControle++;
    }
    @Override
    public void exitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx) {
        nivelLaco--;
        nivelBlocoControle--;
    }

    // ---------- PARA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdPara(TarbaloParser.CmdParaContext ctx) {
        nivelLaco++;
        nivelBlocoControle++;
        tabela.abrirEscopo();
    }
    @Override
    public void exitCmdPara(TarbaloParser.CmdParaContext ctx) {
        tabela.fecharEscopo();
        nivelLaco--;
        nivelBlocoControle--;
    }

    // ---------- PARACADA (laço + controle + escopo próprio) ----------
    @Override
    public void enterCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        nivelLaco++;
        nivelBlocoControle++;
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
        nivelBlocoControle--;
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
        Simbolo s = tabela.buscar(nome);

        // Avaliar tipos dos argumentos
        List<String> tiposArgs = new ArrayList<>();
        if (ctx.argumentos() != null) {
            AvaliadorDeTipos av = new AvaliadorDeTipos(linha);
            for (var expr : ctx.argumentos().expressao()) {
                String tipo = av.visit(expr);
                if (tipo.equals("erro")) {
                    erros = true;
                    return;
                }
                tiposArgs.add(tipo);
            }
        }

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

        // Função não declarada
        if (s == null) {
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome + "' não declarada.");
            erros = true;
            return;
        }

        // Não é uma função
        if (s.getCategoria() != Simbolo.Categoria.FUNCAO) {
            System.err.println("Erro Semântico (Linha " + linha + "): '" + nome + "' não é uma função.");
            erros = true;
            return;
        }

        // Número de argumentos
        int numArgs = ctx.argumentos() != null ? ctx.argumentos().expressao().size() : 0;
        List<String> tiposParam = s.getTiposParametros();  // lista de tipos (ex.: ["int", "txt", "int[]"])
        int numParams = tiposParam.size();

        if (numArgs != numParams) {
            System.err.println("Erro Semântico (Linha " + linha + "): Função '" + nome +
                            "' espera " + numParams + " parâmetro(s), mas foi chamada com " + numArgs + ".");
            erros = true;
            return; // não vale a pena verificar tipos se as quantidades diferem
        }

        // Verificar tipo de cada argumento
        if (ctx.argumentos() != null) {
            AvaliadorDeTipos avaliador = new AvaliadorDeTipos(linha);
            List<TarbaloParser.ExpressaoContext> argumentos = ctx.argumentos().expressao();
            for (int i = 0; i < argumentos.size(); i++) {
                String tipoArg = avaliador.visit(argumentos.get(i));
                String tipoParam = tiposParam.get(i);

                if (!tipoArg.equals("erro") && !saoCompativeis(tipoParam, tipoArg)) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Tipo incompatível no argumento " + (i+1) +
                                    " da função '" + nome + "'. Esperado '" + tipoParam + "', mas recebeu '" + tipoArg + "'.");
                    erros = true;
                }
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
        if (tipoVar.equals("qbd") && tipoExpr.equals("int")) return true; // int cabe em dec
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

    private boolean contemSlice(TarbaloParser.SelecaoVariavelContext ctx) {
        if (ctx.acessoVetor() != null) {
            for (TarbaloParser.AcessoDimensaoContext dim : ctx.acessoVetor().acessoDimensao()) {
                if (dim.PONTOPONTO() != null) return true;
            }
        }
        return false;
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
        public String visitCriacaoVetor(TarbaloParser.CriacaoVetorContext ctx) {
            String tipo = ctx.tipoVariavel().getText();
            int dims = ctx.dimensaoDinamica().size();
            return tipo + "[]".repeat(dims);
        }

        @Override
        public String visitOperando(TarbaloParser.OperandoContext ctx) {
            if (ctx.NUM() != null) return "int";
            if (ctx.NUMDEC() != null) return "qbd";
            if (ctx.STRING() != null) return "txt";
            if (ctx.CHAR() != null) return "ltr";
            if (ctx.VERDADEIRO() != null || ctx.FALSO() != null) return "lgc";

            if (ctx.ID() != null) {
                String tipo = tabela.obterTipo(ctx.ID().getText());
                return tipo != null ? tipo : "desconhecido";
            }

            if (ctx.acessoVetor() != null) {
                String nomeVetor = ctx.acessoVetor().ID().getText();
                Simbolo s = tabela.buscar(nomeVetor);
                if (s == null) {
                    System.err.println("Erro Semântico (Linha " + linha + "): Vetor '" + nomeVetor + "' não declarado.");
                    erros = true;
                    return "erro";
                }
                if (s.getCategoria() != Simbolo.Categoria.VETOR) {
                    System.err.println("Erro Semântico (Linha " + linha + "): '" + nomeVetor + "' não é um vetor e não pode ser indexado.");
                    erros = true;
                    return "erro";
                }
                // Verifica se há slice
                boolean isSlice = false;
                for (TarbaloParser.AcessoDimensaoContext dim : ctx.acessoVetor().acessoDimensao()) {
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
                Simbolo s = tabela.buscar(nomeFunc);
                if (s != null && s.getCategoria() == Simbolo.Categoria.FUNCAO) {
                    String ret = s.getTipoRetorno();
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
            if (ctx.operadorRelacional() != null) {
                String esq = visit(ctx.expressaoAditiva(0));
                String dir = visit(ctx.expressaoAditiva(1));
                if (!esq.equals("erro") && !dir.equals("erro") &&
                    !saoCompativeisRelacionais(esq, dir)) {
                    System.err.println("Erro Semântico (Linha " + linha +
                        "): Tipos incompatíveis na comparação ('" + esq + "' com '" + dir + "').");
                    erros = true;
                }
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
        public String visitExpressaoUnaria(TarbaloParser.ExpressaoUnariaContext ctx) {
            if (ctx.MENOS() != null || ctx.MAIS() != null) {
                String tipo = visit(ctx.expressaoUnaria());
                if (!tipo.equals("int") && !tipo.equals("qbd") && !tipo.equals("erro") && !tipo.equals("desconhecido")) {
                    erros = true;
                    System.err.println("Erro Semântico (Linha " + linha + "): Operador unário exige operando numérico, mas recebeu '" + tipo + "'.");
                    return "erro";
                }
                return tipo;   // mantém o tipo do operando
            }
            return visit(ctx.operando());
        }

        @Override
        public String visitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx) {
            // Se houver concatenação, o resultado é sempre "txt"
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                if (ctx.getChild(i).getText().equals("&")) {
                    // Ainda visita as subexpressões para detetar erros internos
                    for (TarbaloParser.ExpressaoMultiplicativaContext sub : ctx.expressaoMultiplicativa()) {
                        visit(sub);
                    }
                    return "txt";
                }
            }
            // Se não há concatenação, segue a agregação normal
            return super.visitExpressaoAditiva(ctx);
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