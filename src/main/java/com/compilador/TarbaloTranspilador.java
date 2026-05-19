package com.compilador;

import com.compilador.tarbalo.*;
import java.util.*;

public class TarbaloTranspilador extends TarbaloBaseVisitor<String> {

    private TabelaSimbolos tabela;
    private boolean erros = false;

    private StringBuilder funcoesGeradas = new StringBuilder();

    public TarbaloTranspilador(TabelaSimbolos tabela) {
        this.tabela = tabela;
    }

    public boolean houveErros() { return erros; }

    private void erro(int linha, String msg) {
        System.err.println("Erro semântico (linha " + linha + "): " + msg);
        erros = true;
    }

    // Mapeamento de tipos Tarbalo → Java
    private String mapearTipo(String tipoTarbalo) {
        return switch (tipoTarbalo) {
            case "int"    -> "int";
            case "dec"    -> "double";
            case "texto"  -> "String";
            case "car"    -> "char";
            case "logico" -> "boolean";
            default       -> "Object";
        };
    }

    // Método auxiliar para aplicar indentação limpa por bloco
    private String indentar(String codigo, String espacos) {
        if (codigo == null || codigo.isBlank()) return "";
        StringBuilder sb = new StringBuilder();
        for (String linha : codigo.split("\n")) {
            if (!linha.isBlank()) {
                sb.append(espacos).append(linha).append("\n");
            }
        }
        return sb.toString();
    }

    // ======================================================================
    // 1. visitPrograma (Atualizado para suportar Funções e Scanner Global)
    // ======================================================================
    @Override
    public String visitPrograma(TarbaloParser.ProgramaContext ctx) {
        funcoesGeradas = new StringBuilder(); // Limpa as funções geradas
        StringBuilder principal = new StringBuilder();

        // Extrai o código de dentro do prog..fimprog
        for (TarbaloParser.BlocoContext blocoCtx : ctx.bloco()) {
            String codigoBloco = visit(blocoCtx);
            if (codigoBloco != null && !codigoBloco.isBlank()) {
                principal.append(indentar(codigoBloco, "        "));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("import java.util.Scanner;\n\n");
        sb.append("public class ProgramaSaida {\n");

        // O scanner agora é estático para ser acessível pelas funções
        sb.append("    static Scanner scanner = new Scanner(System.in);\n\n");

        // Insere as funções geradas ANTES do main()
        sb.append(funcoesGeradas.toString());

        sb.append("    public static void main(String[] args) {\n");
        sb.append(principal.toString());
        sb.append("    }\n");
        sb.append("}\n");

        return sb.toString();
    }

    // ======================================================================
    // 2. visitBloco (Coleta os comandos alinhados à esquerda)
    // ======================================================================
    @Override
    public String visitBloco(TarbaloParser.BlocoContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (TarbaloParser.ComandoContext cmdCtx : ctx.comando()) {
            String linha = visit(cmdCtx);
            if (linha != null && !linha.isBlank()) {
                sb.append(linha);
                if (!linha.endsWith("\n")) {
                    sb.append("\n");
                }
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
        String tipoJava = mapearTipo(tipoTarbalo);
        String nomeVar = ctx.ID().getText();

        if (ctx.ATRIBUICAO() != null) {
            String valor = visit(ctx.expressao());
            return tipoJava + " " + nomeVar + " = " + valor + ";";
        }

        return tipoJava + " " + nomeVar + ";";
    }

    // ======================================================================
    // 4. visitAtribuicao
    // ======================================================================
    @Override
    public String visitAtribuicao(TarbaloParser.AtribuicaoContext ctx) {
        String nomeVar = null;
        if (ctx.selecaoVariavel().ID() != null) {
            nomeVar = ctx.selecaoVariavel().ID().getText();
        } else if (ctx.selecaoVariavel().acessoVetor() != null) {
            nomeVar = visit(ctx.selecaoVariavel().acessoVetor());
        }

        String operadorTarbalo = ctx.getChild(1).getText();
        String operadorJava = operadorTarbalo;

        if (operadorTarbalo.equals(":")) operadorJava = "=";
        else if (operadorTarbalo.equals("+:")) operadorJava = "+=";
        else if (operadorTarbalo.equals("-:")) operadorJava = "-=";
        else if (operadorTarbalo.equals("*:")) operadorJava = "*=";
        else if (operadorTarbalo.equals("/:")) operadorJava = "/=";

        String valor = visit(ctx.expressao());

        return nomeVar + " " + operadorJava + " " + valor + ";";
    }

    // ======================================================================
    // 5. visitLeitura
    // ======================================================================
    @Override
    public String visitLeitura(TarbaloParser.LeituraContext ctx) {
        String nomeVar = null;
        if (ctx.selecaoVariavel().ID() != null) {
            nomeVar = ctx.selecaoVariavel().ID().getText();
        } else {
            nomeVar = ctx.selecaoVariavel().acessoVetor().getText();
        }

        String tipoTarbalo = tabela.obterTipo(nomeVar);
        String chamadaScanner = "scanner.next()";

        if (tipoTarbalo != null) {
            chamadaScanner = switch (tipoTarbalo) {
                case "int"    -> "scanner.nextInt()";
                case "dec"    -> "scanner.nextDouble()";
                case "logico" -> "scanner.nextBoolean()";
                case "car"    -> "scanner.next().charAt(0)";
                default       -> "scanner.next()";
            };
        }

        return nomeVar + " = " + chamadaScanner + ";";
    }

    // ======================================================================
    // 6. visitEscrita
    // ======================================================================
    @Override
    public String visitEscrita(TarbaloParser.EscritaContext ctx) {
        if (ctx.expressao() != null && !ctx.expressao().isEmpty()) {
            String valorParaImprimir = visit(ctx.expressao(0));
            return "System.out.println(" + valorParaImprimir + ");";
        }
        return "System.out.println();";
    }

    // ======================================================================
    // 7. visitCmdSe (Lógica Condicional Corrigida com Indentação Escopada)
    // ======================================================================
    @Override
    public String visitCmdSe(TarbaloParser.CmdSeContext ctx) {
        String condicao = visit(ctx.expressao());
        String blocoSe = visit(ctx.bloco(0));

        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condicao).append(") {\n");
        // Recua o conteúdo interno do bloco SE em 4 espaços relativos
        sb.append(indentar(blocoSe, "    "));
        sb.append("}");

        if (ctx.SENAO() != null) {
            String blocoSenao = visit(ctx.bloco(1));
            sb.append(" else {\n");
            // Recua o conteúdo interno do bloco SENAO em 4 espaços relativos
            sb.append(indentar(blocoSenao, "    "));
            sb.append("}");
        }

        return sb.toString();
    }

    // ======================================================================
    // 8. visitOperando (Corrigido para respeitar as Funções)
    // ======================================================================
    @Override
    public String visitOperando(TarbaloParser.OperandoContext ctx) {
        // Se o operando for uma chamada de função, delega a tradução para o método correto!
        if (ctx.chamadaFuncao() != null) {
            return visit(ctx.chamadaFuncao());
        }
        // Se for um acesso a vetor que requer tradução
        if (ctx.acessoVetor() != null) {
            return visit(ctx.acessoVetor());
        }

        // Para números soltos, textos ou IDs, devolve o texto bruto
        return ctx.getText();
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }

    // ======================================================================
    // 9. Tratamento genérico de nós da árvore (Operadores) - CORRIGIDO
    // ======================================================================
    @Override
    public String visitChildren(org.antlr.v4.runtime.tree.RuleNode node) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < node.getChildCount(); i++) {
            org.antlr.v4.runtime.tree.ParseTree child = node.getChild(i);

            // Apanha os nós terminais (símbolos e operadores) ANTES de tentar visitá-los
            if (child instanceof org.antlr.v4.runtime.tree.TerminalNode) {
                String texto = child.getText();
                // Ignorar pontuação de delimitação que não vai para o Java
                if (texto.equals(".") || texto.equals(";") || texto.equals("{") || texto.equals("}")) {
                    continue;
                }
                // Traduzir operadores lógicos do Tarbalo para Java
                if (texto.equals("e")) texto = "&&";
                else if (texto.equals("ou")) texto = "||";
                else if (texto.equals("nao")) texto = "!";
                else if (texto.equals("=")) texto = "=="; // Em Tarbalo o = é comparação

                result.append(texto).append(" ");
            } else {
                // Se for uma regra da gramática (expressão, operando, etc.), visita normalmente
                String childResult = visit(child);
                if (childResult != null && !childResult.isBlank()) {
                    result.append(childResult).append(" ");
                }
            }
        }
        return result.toString().trim();
    }

    // ======================================================================
    // 10. visitCmdEnquanto (while)
    // ======================================================================
    @Override
    public String visitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx) {
        String condicao = visit(ctx.expressao());
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
        String condicao = visit(ctx.expressao());

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
        // Usa métodos auxiliares criados abaixo para a assinatura do For
        String inicializacao = visit(ctx.atribuicaoPara());
        String condicao = visit(ctx.expressao());
        String atualizacao = visit(ctx.atualizacaoPara());
        String blocoLoop = visit(ctx.bloco());

        StringBuilder sb = new StringBuilder();
        sb.append("for (").append(inicializacao).append("; ").append(condicao).append("; ").append(atualizacao).append(") {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("}");

        return sb.toString();
    }

    // ======================================================================
    // 13. visitCmdParaCada (foreach - pancada)
    // ======================================================================
    @Override
    public String visitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx) {
        String tipoTarbalo = ctx.tipoVariavel().getText();
        String tipoJava = mapearTipo(tipoTarbalo);
        String nomeVar = ctx.ID().getText();
        String colecao = visit(ctx.expressao());
        String blocoLoop = visit(ctx.bloco());

        StringBuilder sb = new StringBuilder();
        sb.append("for (").append(tipoJava).append(" ").append(nomeVar).append(" : ").append(colecao).append(") {\n");
        sb.append(indentar(blocoLoop, "    "));
        sb.append("}");

        return sb.toString();
    }

    // ======================================================================
    // 14. Auxiliares do FOR e Incrementos (++, --)
    // ======================================================================
    @Override
    public String visitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx) {
        String nomeVar = ctx.selecaoVariavel().getText();
        String operadorTarbalo = ctx.getChild(1).getText();
        String operadorJava = operadorTarbalo.equals(":") ? "=" : operadorTarbalo.replace(":", "=");
        String valor = visit(ctx.expressao());
        return nomeVar + " " + operadorJava + " " + valor;
    }

    @Override
    public String visitAtualizacaoPara(TarbaloParser.AtualizacaoParaContext ctx) {
        return visitChildren(ctx); // Delega para os incrementos ou atribuições
    }

    @Override
    public String visitIncremento(TarbaloParser.IncrementoContext ctx) {
        return ctx.selecaoVariavel().getText() + "++";
    }

    @Override
    public String visitDecremento(TarbaloParser.DecrementoContext ctx) {
        return ctx.selecaoVariavel().getText() + "--";
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
    // 15. Controlo de Fluxo (Break, Continue, Return)
    // ======================================================================
    @Override
    public String visitPare(TarbaloParser.PareContext ctx) {
        return "break;";
    }

    @Override
    public String visitContinuar(TarbaloParser.ContinuarContext ctx) {
        return "continue;";
    }

    @Override
    public String visitRetorno(TarbaloParser.RetornoContext ctx) {
        if (ctx.expressao() != null) {
            return "return " + visit(ctx.expressao()) + ";";
        }
        return "return;";
    }

    // ======================================================================
    // 16. Vetores: Declaração (ex: vtr int notas[10].)
    // ======================================================================
    @Override
    public String visitDeclaracaoVetor(TarbaloParser.DeclaracaoVetorContext ctx) {
        String tipoTarbalo = ctx.tipoVariavel().getText();
        String tipoJava = mapearTipo(tipoTarbalo);
        String nomeVar = ctx.ID().getText();

        // Prepara os colchetes para Java (suporta matrizes multi-dimensionais)
        StringBuilder colchetesJava = new StringBuilder();
        StringBuilder tamanhosJava = new StringBuilder();

        for (TarbaloParser.DimensaoVetorContext dimCtx : ctx.dimensaoVetor()) {
            colchetesJava.append("[]");
            if (dimCtx.NUM() != null) {
                tamanhosJava.append("[").append(dimCtx.NUM().getText()).append("]");
            } else {
                tamanhosJava.append("[]");
            }
        }

        // Se o vetor tiver valores iniciais (ex: vtr int notas[3] : [1; 2; 3].)
        if (ctx.ATRIBUICAO() != null) {
            String valores = visit(ctx.inicializacaoVetor());
            return tipoJava + colchetesJava.toString() + " " + nomeVar + " = " + valores + ";";
        }

        // Declaração vazia com tamanho (ex: vtr int notas[10].)
        return tipoJava + colchetesJava.toString() + " " + nomeVar + " = new " + tipoJava + tamanhosJava.toString() + ";";
    }

    // ======================================================================
    // 17. Vetores: Inicialização (ex: [1; 2; 3])
    // ======================================================================
    @Override
    public String visitInicializacaoVetor(TarbaloParser.InicializacaoVetorContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Pega em todos os valores separados por ponto e vírgula
        for (int i = 0; i < ctx.expressao().size(); i++) {
            sb.append(visit(ctx.expressao(i)));
            if (i < ctx.expressao().size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    // ======================================================================
    // 18. Vetores: Acesso (ex: notas[i])
    // ======================================================================
    @Override
    public String visitAcessoVetor(TarbaloParser.AcessoVetorContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.ID().getText()); // Nome do vetor

        for (TarbaloParser.AcessoDimensaoContext dimCtx : ctx.acessoDimensao()) {
            sb.append(visit(dimCtx));
        }
        return sb.toString();
    }

    @Override
    public String visitAcessoDimensao(TarbaloParser.AcessoDimensaoContext ctx) {
        // Retorna o índice entre colchetes (avaliando qualquer expressão matemática lá dentro)
        return "[" + visit(ctx.expressao(0)) + "]";
    }

    // ======================================================================
    // 19. Declaração de Função (ex: func int somar(int a ; int b) { ... } .)
    // ======================================================================
    @Override
    public String visitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx) {
        // Se a função não tiver retorno, assumimos "void". Pode adaptar se a sua gramática usar "vazio"
        String tipoRetorno = ctx.tipoRetorno() != null ? mapearTipo(ctx.tipoRetorno().getText()) : "void";
        if (ctx.tipoRetorno() != null && ctx.tipoRetorno().getText().equals("vazio")) tipoRetorno = "void";

        String nomeFuncao = ctx.ID().getText();

        String parametros = "";
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }

        String bloco = visit(ctx.bloco());

        StringBuilder sb = new StringBuilder();
        sb.append("public static ").append(tipoRetorno).append(" ").append(nomeFuncao).append("(").append(parametros).append(") {\n");
        sb.append(indentar(bloco, "    "));
        sb.append("}\n");

        // Guarda a função na variável global para ser colocada fora do main()
        funcoesGeradas.append(indentar(sb.toString(), "    ")).append("\n");

        // Retorna String vazia para que a função não seja impressa dentro do main()!
        return "";
    }

    // ======================================================================
    // 20. Parâmetros e Argumentos (Traduz o ';' do Tarbalo para a ',' do Java)
    // ======================================================================
    @Override
    public String visitParametros(TarbaloParser.ParametrosContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.parametro().size(); i++) {
            sb.append(visit(ctx.parametro(i)));
            if (i < ctx.parametro().size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String visitParametro(TarbaloParser.ParametroContext ctx) {
        String tipoJava = mapearTipo(ctx.tipoVariavel().getText());

        // CORREÇÃO: Acedemos ao ID através da sub-regra variavel()
        String nomeVar = ctx.variavel().ID().getText();

        // CORREÇÃO: Acedemos às dimensões também através da sub-regra variavel()
        StringBuilder colchetes = new StringBuilder();
        if (ctx.variavel().dimensaoVetor() != null) {
            for (int i = 0; i < ctx.variavel().dimensaoVetor().size(); i++) {
                colchetes.append("[]");
            }
        }

        return tipoJava + colchetes.toString() + " " + nomeVar;
    }

    @Override
    public String visitArgumentos(TarbaloParser.ArgumentosContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.expressao().size(); i++) {
            sb.append(visit(ctx.expressao(i)));
            if (i < ctx.expressao().size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // ======================================================================
    // 21. Chamada de Função (ex: somar(x ; y) )
    // ======================================================================
    @Override
    public String visitChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx) {
        String nomeFuncao = ctx.ID().getText();
        String argumentos = "";
        if (ctx.argumentos() != null) {
            argumentos = visit(ctx.argumentos());
        }
        return nomeFuncao + "(" + argumentos + ")";
    }

    // Despachante genérico para Declarações
    @Override
    public String visitDeclaracao(TarbaloParser.DeclaracaoContext ctx) {
        return visitChildren(ctx);
    }
}