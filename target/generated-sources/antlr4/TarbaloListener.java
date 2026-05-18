// Generated from Tarbalo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TarbaloParser}.
 */
public interface TarbaloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(TarbaloParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(TarbaloParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(TarbaloParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(TarbaloParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao(TarbaloParser.DeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao(TarbaloParser.DeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#declaracaoVariavel}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#declaracaoVariavel}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracaoVariavel(TarbaloParser.DeclaracaoVariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(TarbaloParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(TarbaloParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#tipoVariavel}.
	 * @param ctx the parse tree
	 */
	void enterTipoVariavel(TarbaloParser.TipoVariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#tipoVariavel}.
	 * @param ctx the parse tree
	 */
	void exitTipoVariavel(TarbaloParser.TipoVariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void enterTipoRetorno(TarbaloParser.TipoRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void exitTipoRetorno(TarbaloParser.TipoRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#dimensaoDeclaracao}.
	 * @param ctx the parse tree
	 */
	void enterDimensaoDeclaracao(TarbaloParser.DimensaoDeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#dimensaoDeclaracao}.
	 * @param ctx the parse tree
	 */
	void exitDimensaoDeclaracao(TarbaloParser.DimensaoDeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#acessoVetor}.
	 * @param ctx the parse tree
	 */
	void enterAcessoVetor(TarbaloParser.AcessoVetorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#acessoVetor}.
	 * @param ctx the parse tree
	 */
	void exitAcessoVetor(TarbaloParser.AcessoVetorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#acessoDimensao}.
	 * @param ctx the parse tree
	 */
	void enterAcessoDimensao(TarbaloParser.AcessoDimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#acessoDimensao}.
	 * @param ctx the parse tree
	 */
	void exitAcessoDimensao(TarbaloParser.AcessoDimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#declaracaoFuncao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#declaracaoFuncao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracaoFuncao(TarbaloParser.DeclaracaoFuncaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(TarbaloParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(TarbaloParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(TarbaloParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(TarbaloParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#blocoPrincipal}.
	 * @param ctx the parse tree
	 */
	void enterBlocoPrincipal(TarbaloParser.BlocoPrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#blocoPrincipal}.
	 * @param ctx the parse tree
	 */
	void exitBlocoPrincipal(TarbaloParser.BlocoPrincipalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(TarbaloParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(TarbaloParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(TarbaloParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(TarbaloParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#leitura}.
	 * @param ctx the parse tree
	 */
	void enterLeitura(TarbaloParser.LeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#leitura}.
	 * @param ctx the parse tree
	 */
	void exitLeitura(TarbaloParser.LeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#escrita}.
	 * @param ctx the parse tree
	 */
	void enterEscrita(TarbaloParser.EscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#escrita}.
	 * @param ctx the parse tree
	 */
	void exitEscrita(TarbaloParser.EscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#destinoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterDestinoAtribuicao(TarbaloParser.DestinoAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#destinoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitDestinoAtribuicao(TarbaloParser.DestinoAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#atribuicao}.
	 * @param ctx the parse tree
	 */
	void enterAtribuicao(TarbaloParser.AtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#atribuicao}.
	 * @param ctx the parse tree
	 */
	void exitAtribuicao(TarbaloParser.AtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(TarbaloParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(TarbaloParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#decremento}.
	 * @param ctx the parse tree
	 */
	void enterDecremento(TarbaloParser.DecrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#decremento}.
	 * @param ctx the parse tree
	 */
	void exitDecremento(TarbaloParser.DecrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(TarbaloParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(TarbaloParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(TarbaloParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdFacaEnquanto(TarbaloParser.CmdFacaEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(TarbaloParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(TarbaloParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#atribuicaoPara}.
	 * @param ctx the parse tree
	 */
	void enterAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#atribuicaoPara}.
	 * @param ctx the parse tree
	 */
	void exitAtribuicaoPara(TarbaloParser.AtribuicaoParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#atualizacaoPara}.
	 * @param ctx the parse tree
	 */
	void enterAtualizacaoPara(TarbaloParser.AtualizacaoParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#atualizacaoPara}.
	 * @param ctx the parse tree
	 */
	void exitAtualizacaoPara(TarbaloParser.AtualizacaoParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#incrementoPara}.
	 * @param ctx the parse tree
	 */
	void enterIncrementoPara(TarbaloParser.IncrementoParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#incrementoPara}.
	 * @param ctx the parse tree
	 */
	void exitIncrementoPara(TarbaloParser.IncrementoParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#decrementoPara}.
	 * @param ctx the parse tree
	 */
	void enterDecrementoPara(TarbaloParser.DecrementoParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#decrementoPara}.
	 * @param ctx the parse tree
	 */
	void exitDecrementoPara(TarbaloParser.DecrementoParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#atribuicaoCompostaPara}.
	 * @param ctx the parse tree
	 */
	void enterAtribuicaoCompostaPara(TarbaloParser.AtribuicaoCompostaParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#atribuicaoCompostaPara}.
	 * @param ctx the parse tree
	 */
	void exitAtribuicaoCompostaPara(TarbaloParser.AtribuicaoCompostaParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#retorno}.
	 * @param ctx the parse tree
	 */
	void enterRetorno(TarbaloParser.RetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#retorno}.
	 * @param ctx the parse tree
	 */
	void exitRetorno(TarbaloParser.RetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#pare}.
	 * @param ctx the parse tree
	 */
	void enterPare(TarbaloParser.PareContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#pare}.
	 * @param ctx the parse tree
	 */
	void exitPare(TarbaloParser.PareContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#continuar}.
	 * @param ctx the parse tree
	 */
	void enterContinuar(TarbaloParser.ContinuarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#continuar}.
	 * @param ctx the parse tree
	 */
	void exitContinuar(TarbaloParser.ContinuarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(TarbaloParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(TarbaloParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoOu}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoOu(TarbaloParser.ExpressaoOuContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoOu}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoOu(TarbaloParser.ExpressaoOuContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoXor}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoXor(TarbaloParser.ExpressaoXorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoXor}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoXor(TarbaloParser.ExpressaoXorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoE}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoE(TarbaloParser.ExpressaoEContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoE}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoE(TarbaloParser.ExpressaoEContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoNegacao}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoNegacao(TarbaloParser.ExpressaoNegacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoNegacao}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoNegacao(TarbaloParser.ExpressaoNegacaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoRelacional(TarbaloParser.ExpressaoRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoAditiva}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoAditiva}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoAditiva(TarbaloParser.ExpressaoAditivaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoMultiplicativa}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoMultiplicativa(TarbaloParser.ExpressaoMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoMultiplicativa}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoMultiplicativa(TarbaloParser.ExpressaoMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#expressaoUnaria}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoUnaria(TarbaloParser.ExpressaoUnariaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#expressaoUnaria}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoUnaria(TarbaloParser.ExpressaoUnariaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando(TarbaloParser.OperandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando(TarbaloParser.OperandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#chamadaFuncao}.
	 * @param ctx the parse tree
	 */
	void enterChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#chamadaFuncao}.
	 * @param ctx the parse tree
	 */
	void exitChamadaFuncao(TarbaloParser.ChamadaFuncaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(TarbaloParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(TarbaloParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#operadorRelacional}.
	 * @param ctx the parse tree
	 */
	void enterOperadorRelacional(TarbaloParser.OperadorRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#operadorRelacional}.
	 * @param ctx the parse tree
	 */
	void exitOperadorRelacional(TarbaloParser.OperadorRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TarbaloParser#operadorAtribuicaoComposta}.
	 * @param ctx the parse tree
	 */
	void enterOperadorAtribuicaoComposta(TarbaloParser.OperadorAtribuicaoCompostaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TarbaloParser#operadorAtribuicaoComposta}.
	 * @param ctx the parse tree
	 */
	void exitOperadorAtribuicaoComposta(TarbaloParser.OperadorAtribuicaoCompostaContext ctx);
}