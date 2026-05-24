// Generated from com/compilador/tarbalo/Tarbalo.g4 by ANTLR 4.13.1
package com.compilador.tarbalo;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TarbaloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TarbaloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(TarbaloParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#declaracao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao(TarbaloParser.DeclaracaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#declaracaoVariavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(TarbaloParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#tipoVariavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoVariavel(TarbaloParser.TipoVariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#tipoComposto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoComposto(TarbaloParser.TipoCompostoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#tipoRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoRetorno(TarbaloParser.TipoRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#dimensaoVetor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensaoVetor(TarbaloParser.DimensaoVetorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#declaracaoVetor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoVetor(TarbaloParser.DeclaracaoVetorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#inicializacaoVetor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializacaoVetor(TarbaloParser.InicializacaoVetorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#valorAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorAtribuicao(TarbaloParser.ValorAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#declaracaoFuncao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(TarbaloParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(TarbaloParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#bloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloco(TarbaloParser.BlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComando(TarbaloParser.ComandoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdBloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdBloco(TarbaloParser.CmdBlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#diretiva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiretiva(TarbaloParser.DiretivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#leitura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeitura(TarbaloParser.LeituraContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#escrita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscrita(TarbaloParser.EscritaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#selecaoVariavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecaoVariavel(TarbaloParser.SelecaoVariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#atribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtribuicao(TarbaloParser.AtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(TarbaloParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#decremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecremento(TarbaloParser.DecrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#incrementoPonto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementoPonto(TarbaloParser.IncrementoPontoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#decrementoPonto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecrementoPonto(TarbaloParser.DecrementoPontoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(TarbaloParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(TarbaloParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#inicializacaoPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializacaoPara(TarbaloParser.InicializacaoParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#atribuicaoPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#atualizacaoPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtualizacaoPara(TarbaloParser.AtualizacaoParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#cmdParaCada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdParaCada(TarbaloParser.CmdParaCadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetorno(TarbaloParser.RetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#pare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPare(TarbaloParser.PareContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#continuar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuar(TarbaloParser.ContinuarContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(TarbaloParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoXor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoXor(TarbaloParser.ExpressaoXorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoE(TarbaloParser.ExpressaoEContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoNegacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoNegacao(TarbaloParser.ExpressaoNegacaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoAditiva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoMultiplicativa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoMultiplicativa(TarbaloParser.ExpressaoMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#expressaoUnaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoUnaria(TarbaloParser.ExpressaoUnariaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando(TarbaloParser.OperandoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#acessoVetor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcessoVetor(TarbaloParser.AcessoVetorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#acessoDimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcessoDimensao(TarbaloParser.AcessoDimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#chamadaFuncao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(TarbaloParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#operadorRelacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperadorRelacional(TarbaloParser.OperadorRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link TarbaloParser#operadorAtribuicaoComposta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperadorAtribuicaoComposta(TarbaloParser.OperadorAtribuicaoCompostaContext ctx);
}