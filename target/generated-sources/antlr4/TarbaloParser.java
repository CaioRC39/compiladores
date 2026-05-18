// Generated from Tarbalo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TarbaloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INICIO=1, FIM=2, INTEIRO=3, DECIMAL=4, CARACTERE=5, TEXTO_TIPO=6, BOOLEANO_TIPO=7, 
		VAZIO=8, FUNCAO=9, RETORNE=10, LEIA=11, ESCREVA=12, SE=13, SENAO=14, ENQUANTO=15, 
		FACA=16, PARA=17, PARE=18, CONTINUAR=19, VERDADEIRO=20, FALSO=21, SOMA_ATRIBUICAO=22, 
		SUBTRACAO_ATRIBUICAO=23, MULT_ATRIBUICAO=24, DIV_ATRIBUICAO=25, MOD_ATRIBUICAO=26, 
		INCREMENTO=27, DECREMENTO=28, MAIS=29, MENOS=30, MULT=31, DIVINT=32, DIV=33, 
		MOD=34, CONCAT=35, ATRIBUICAO=36, MENOR=37, MAIOR=38, MENORIGUAL=39, MAIORIGUAL=40, 
		IGUAL=41, DIFERENTE=42, NAO=43, E=44, OU=45, XOR=46, ABREPARENTE=47, FECHAPARENTE=48, 
		ABRECHAVE=49, FECHACHAVE=50, ABRECOLCHETE=51, FECHACOLCHETE=52, VIRGULA=53, 
		PONTIVIRGULA=54, PONTO=55, NUMDEC=56, NUM=57, CHAR=58, STRING=59, ID=60, 
		COMENTARIO_LINHA=61, COMENTARIO_BLOCO=62, WS=63;
	public static final int
		RULE_programa = 0, RULE_declaracoes = 1, RULE_declaracao = 2, RULE_declaracaoVariavel = 3, 
		RULE_variavel = 4, RULE_tipoVariavel = 5, RULE_tipoRetorno = 6, RULE_dimensaoDeclaracao = 7, 
		RULE_acessoVetor = 8, RULE_acessoDimensao = 9, RULE_declaracaoFuncao = 10, 
		RULE_parametros = 11, RULE_parametro = 12, RULE_blocoPrincipal = 13, RULE_bloco = 14, 
		RULE_comando = 15, RULE_leitura = 16, RULE_escrita = 17, RULE_destinoAtribuicao = 18, 
		RULE_atribuicao = 19, RULE_incremento = 20, RULE_decremento = 21, RULE_cmdSe = 22, 
		RULE_cmdEnquanto = 23, RULE_cmdFacaEnquanto = 24, RULE_cmdPara = 25, RULE_atribuicaoPara = 26, 
		RULE_atualizacaoPara = 27, RULE_incrementoPara = 28, RULE_decrementoPara = 29, 
		RULE_atribuicaoCompostaPara = 30, RULE_retorno = 31, RULE_pare = 32, RULE_continuar = 33, 
		RULE_expressao = 34, RULE_expressaoOu = 35, RULE_expressaoXor = 36, RULE_expressaoE = 37, 
		RULE_expressaoNegacao = 38, RULE_expressaoRelacional = 39, RULE_expressaoAditiva = 40, 
		RULE_expressaoMultiplicativa = 41, RULE_expressaoUnaria = 42, RULE_operando = 43, 
		RULE_chamadaFuncao = 44, RULE_argumentos = 45, RULE_operadorRelacional = 46, 
		RULE_operadorAtribuicaoComposta = 47;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracoes", "declaracao", "declaracaoVariavel", "variavel", 
			"tipoVariavel", "tipoRetorno", "dimensaoDeclaracao", "acessoVetor", "acessoDimensao", 
			"declaracaoFuncao", "parametros", "parametro", "blocoPrincipal", "bloco", 
			"comando", "leitura", "escrita", "destinoAtribuicao", "atribuicao", "incremento", 
			"decremento", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", "cmdPara", "atribuicaoPara", 
			"atualizacaoPara", "incrementoPara", "decrementoPara", "atribuicaoCompostaPara", 
			"retorno", "pare", "continuar", "expressao", "expressaoOu", "expressaoXor", 
			"expressaoE", "expressaoNegacao", "expressaoRelacional", "expressaoAditiva", 
			"expressaoMultiplicativa", "expressaoUnaria", "operando", "chamadaFuncao", 
			"argumentos", "operadorRelacional", "operadorAtribuicaoComposta"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'prog'", "'fimprog'", "'int'", "'dec'", "'car'", "'texto'", "'booleano'", 
			"'vazio'", "'func'", "'retorne'", "'leia'", "'escreva'", "'se'", "'senao'", 
			"'enquanto'", "'faca'", "'para'", "'pare'", "'continue'", "'verdadeiro'", 
			"'falso'", "'+:'", "'-:'", "'*:'", "'/:'", "'%:'", "'++'", "'--'", "'+'", 
			"'-'", "'*'", "'//'", "'/'", "'%'", "'&'", "':'", "'<'", "'>'", "'<='", 
			"'>='", "'='", "'!='", "'nao'", "'e'", "'ou'", "'xor'", "'('", "')'", 
			"'{'", "'}'", "'['", "']'", "','", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INICIO", "FIM", "INTEIRO", "DECIMAL", "CARACTERE", "TEXTO_TIPO", 
			"BOOLEANO_TIPO", "VAZIO", "FUNCAO", "RETORNE", "LEIA", "ESCREVA", "SE", 
			"SENAO", "ENQUANTO", "FACA", "PARA", "PARE", "CONTINUAR", "VERDADEIRO", 
			"FALSO", "SOMA_ATRIBUICAO", "SUBTRACAO_ATRIBUICAO", "MULT_ATRIBUICAO", 
			"DIV_ATRIBUICAO", "MOD_ATRIBUICAO", "INCREMENTO", "DECREMENTO", "MAIS", 
			"MENOS", "MULT", "DIVINT", "DIV", "MOD", "CONCAT", "ATRIBUICAO", "MENOR", 
			"MAIOR", "MENORIGUAL", "MAIORIGUAL", "IGUAL", "DIFERENTE", "NAO", "E", 
			"OU", "XOR", "ABREPARENTE", "FECHAPARENTE", "ABRECHAVE", "FECHACHAVE", 
			"ABRECOLCHETE", "FECHACOLCHETE", "VIRGULA", "PONTIVIRGULA", "PONTO", 
			"NUMDEC", "NUM", "CHAR", "STRING", "ID", "COMENTARIO_LINHA", "COMENTARIO_BLOCO", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Tarbalo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TarbaloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode INICIO() { return getToken(TarbaloParser.INICIO, 0); }
		public DeclaracoesContext declaracoes() {
			return getRuleContext(DeclaracoesContext.class,0);
		}
		public BlocoPrincipalContext blocoPrincipal() {
			return getRuleContext(BlocoPrincipalContext.class,0);
		}
		public TerminalNode FIM() { return getToken(TarbaloParser.FIM, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public TerminalNode EOF() { return getToken(TarbaloParser.EOF, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(INICIO);
			setState(97);
			declaracoes();
			setState(98);
			blocoPrincipal();
			setState(99);
			match(FIM);
			setState(100);
			match(PONTO);
			setState(101);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracoesContext extends ParserRuleContext {
		public List<DeclaracaoContext> declaracao() {
			return getRuleContexts(DeclaracaoContext.class);
		}
		public DeclaracaoContext declaracao(int i) {
			return getRuleContext(DeclaracaoContext.class,i);
		}
		public DeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDeclaracoes(this);
		}
	}

	public final DeclaracoesContext declaracoes() throws RecognitionException {
		DeclaracoesContext _localctx = new DeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracoes);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(103);
					declaracao();
					}
					} 
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoContext extends ParserRuleContext {
		public DeclaracaoVariavelContext declaracaoVariavel() {
			return getRuleContext(DeclaracaoVariavelContext.class,0);
		}
		public DeclaracaoFuncaoContext declaracaoFuncao() {
			return getRuleContext(DeclaracaoFuncaoContext.class,0);
		}
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDeclaracao(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracao);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEIRO:
			case DECIMAL:
			case CARACTERE:
			case TEXTO_TIPO:
			case BOOLEANO_TIPO:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				declaracaoVariavel();
				}
				break;
			case FUNCAO:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				declaracaoFuncao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoVariavelContext extends ParserRuleContext {
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public List<VariavelContext> variavel() {
			return getRuleContexts(VariavelContext.class);
		}
		public VariavelContext variavel(int i) {
			return getRuleContext(VariavelContext.class,i);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public List<TerminalNode> VIRGULA() { return getTokens(TarbaloParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(TarbaloParser.VIRGULA, i);
		}
		public DeclaracaoVariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracaoVariavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDeclaracaoVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDeclaracaoVariavel(this);
		}
	}

	public final DeclaracaoVariavelContext declaracaoVariavel() throws RecognitionException {
		DeclaracaoVariavelContext _localctx = new DeclaracaoVariavelContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracaoVariavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			tipoVariavel();
			setState(114);
			variavel();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(115);
				match(VIRGULA);
				setState(116);
				variavel();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariavelContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public List<DimensaoDeclaracaoContext> dimensaoDeclaracao() {
			return getRuleContexts(DimensaoDeclaracaoContext.class);
		}
		public DimensaoDeclaracaoContext dimensaoDeclaracao(int i) {
			return getRuleContext(DimensaoDeclaracaoContext.class,i);
		}
		public VariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitVariavel(this);
		}
	}

	public final VariavelContext variavel() throws RecognitionException {
		VariavelContext _localctx = new VariavelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(ID);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABRECOLCHETE) {
				{
				{
				setState(125);
				dimensaoDeclaracao();
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoVariavelContext extends ParserRuleContext {
		public TerminalNode INTEIRO() { return getToken(TarbaloParser.INTEIRO, 0); }
		public TerminalNode DECIMAL() { return getToken(TarbaloParser.DECIMAL, 0); }
		public TerminalNode TEXTO_TIPO() { return getToken(TarbaloParser.TEXTO_TIPO, 0); }
		public TerminalNode CARACTERE() { return getToken(TarbaloParser.CARACTERE, 0); }
		public TerminalNode BOOLEANO_TIPO() { return getToken(TarbaloParser.BOOLEANO_TIPO, 0); }
		public TipoVariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoVariavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterTipoVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitTipoVariavel(this);
		}
	}

	public final TipoVariavelContext tipoVariavel() throws RecognitionException {
		TipoVariavelContext _localctx = new TipoVariavelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipoVariavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 248L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoRetornoContext extends ParserRuleContext {
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public TerminalNode VAZIO() { return getToken(TarbaloParser.VAZIO, 0); }
		public TipoRetornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoRetorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterTipoRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitTipoRetorno(this);
		}
	}

	public final TipoRetornoContext tipoRetorno() throws RecognitionException {
		TipoRetornoContext _localctx = new TipoRetornoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tipoRetorno);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEIRO:
			case DECIMAL:
			case CARACTERE:
			case TEXTO_TIPO:
			case BOOLEANO_TIPO:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				tipoVariavel();
				}
				break;
			case VAZIO:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(VAZIO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DimensaoDeclaracaoContext extends ParserRuleContext {
		public TerminalNode ABRECOLCHETE() { return getToken(TarbaloParser.ABRECOLCHETE, 0); }
		public TerminalNode NUM() { return getToken(TarbaloParser.NUM, 0); }
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
		public DimensaoDeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensaoDeclaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDimensaoDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDimensaoDeclaracao(this);
		}
	}

	public final DimensaoDeclaracaoContext dimensaoDeclaracao() throws RecognitionException {
		DimensaoDeclaracaoContext _localctx = new DimensaoDeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dimensaoDeclaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(ABRECOLCHETE);
			setState(138);
			match(NUM);
			setState(139);
			match(FECHACOLCHETE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AcessoVetorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public List<AcessoDimensaoContext> acessoDimensao() {
			return getRuleContexts(AcessoDimensaoContext.class);
		}
		public AcessoDimensaoContext acessoDimensao(int i) {
			return getRuleContext(AcessoDimensaoContext.class,i);
		}
		public AcessoVetorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acessoVetor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAcessoVetor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAcessoVetor(this);
		}
	}

	public final AcessoVetorContext acessoVetor() throws RecognitionException {
		AcessoVetorContext _localctx = new AcessoVetorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_acessoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(ID);
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				acessoDimensao();
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ABRECOLCHETE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AcessoDimensaoContext extends ParserRuleContext {
		public TerminalNode ABRECOLCHETE() { return getToken(TarbaloParser.ABRECOLCHETE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
		public AcessoDimensaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acessoDimensao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAcessoDimensao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAcessoDimensao(this);
		}
	}

	public final AcessoDimensaoContext acessoDimensao() throws RecognitionException {
		AcessoDimensaoContext _localctx = new AcessoDimensaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_acessoDimensao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(ABRECOLCHETE);
			setState(148);
			expressao();
			setState(149);
			match(FECHACOLCHETE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoFuncaoContext extends ParserRuleContext {
		public TerminalNode FUNCAO() { return getToken(TarbaloParser.FUNCAO, 0); }
		public TipoRetornoContext tipoRetorno() {
			return getRuleContext(TipoRetornoContext.class,0);
		}
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode ABRECHAVE() { return getToken(TarbaloParser.ABRECHAVE, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode FECHACHAVE() { return getToken(TarbaloParser.FECHACHAVE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public DeclaracaoFuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracaoFuncao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDeclaracaoFuncao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDeclaracaoFuncao(this);
		}
	}

	public final DeclaracaoFuncaoContext declaracaoFuncao() throws RecognitionException {
		DeclaracaoFuncaoContext _localctx = new DeclaracaoFuncaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaracaoFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(FUNCAO);
			setState(152);
			tipoRetorno();
			setState(153);
			match(ID);
			setState(154);
			match(ABREPARENTE);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 248L) != 0)) {
				{
				setState(155);
				parametros();
				}
			}

			setState(158);
			match(FECHAPARENTE);
			setState(159);
			match(ABRECHAVE);
			setState(160);
			bloco();
			setState(161);
			match(FECHACHAVE);
			setState(162);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametrosContext extends ParserRuleContext {
		public List<ParametroContext> parametro() {
			return getRuleContexts(ParametroContext.class);
		}
		public ParametroContext parametro(int i) {
			return getRuleContext(ParametroContext.class,i);
		}
		public List<TerminalNode> PONTIVIRGULA() { return getTokens(TarbaloParser.PONTIVIRGULA); }
		public TerminalNode PONTIVIRGULA(int i) {
			return getToken(TarbaloParser.PONTIVIRGULA, i);
		}
		public ParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitParametros(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			parametro();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTIVIRGULA) {
				{
				{
				setState(165);
				match(PONTIVIRGULA);
				setState(166);
				parametro();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametroContext extends ParserRuleContext {
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterParametro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitParametro(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parametro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			tipoVariavel();
			setState(173);
			variavel();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoPrincipalContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public BlocoPrincipalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocoPrincipal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterBlocoPrincipal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitBlocoPrincipal(this);
		}
	}

	public final BlocoPrincipalContext blocoPrincipal() throws RecognitionException {
		BlocoPrincipalContext _localctx = new BlocoPrincipalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_blocoPrincipal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			bloco();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1152921504607878392L) != 0)) {
				{
				{
				setState(177);
				comando();
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public DeclaracaoVariavelContext declaracaoVariavel() {
			return getRuleContext(DeclaracaoVariavelContext.class,0);
		}
		public AtribuicaoContext atribuicao() {
			return getRuleContext(AtribuicaoContext.class,0);
		}
		public LeituraContext leitura() {
			return getRuleContext(LeituraContext.class,0);
		}
		public EscritaContext escrita() {
			return getRuleContext(EscritaContext.class,0);
		}
		public CmdSeContext cmdSe() {
			return getRuleContext(CmdSeContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdFacaEnquantoContext cmdFacaEnquanto() {
			return getRuleContext(CmdFacaEnquantoContext.class,0);
		}
		public CmdParaContext cmdPara() {
			return getRuleContext(CmdParaContext.class,0);
		}
		public RetornoContext retorno() {
			return getRuleContext(RetornoContext.class,0);
		}
		public PareContext pare() {
			return getRuleContext(PareContext.class,0);
		}
		public ContinuarContext continuar() {
			return getRuleContext(ContinuarContext.class,0);
		}
		public IncrementoContext incremento() {
			return getRuleContext(IncrementoContext.class,0);
		}
		public DecrementoContext decremento() {
			return getRuleContext(DecrementoContext.class,0);
		}
		public ChamadaFuncaoContext chamadaFuncao() {
			return getRuleContext(ChamadaFuncaoContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comando);
		try {
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				declaracaoVariavel();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				atribuicao();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				leitura();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(186);
				escrita();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(187);
				cmdSe();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(188);
				cmdEnquanto();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(189);
				cmdFacaEnquanto();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(190);
				cmdPara();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(191);
				retorno();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(192);
				pare();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(193);
				continuar();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(194);
				incremento();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(195);
				decremento();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(196);
				chamadaFuncao();
				setState(197);
				match(PONTO);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeituraContext extends ParserRuleContext {
		public TerminalNode LEIA() { return getToken(TarbaloParser.LEIA, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public LeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitLeitura(this);
		}
	}

	public final LeituraContext leitura() throws RecognitionException {
		LeituraContext _localctx = new LeituraContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_leitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(LEIA);
			setState(202);
			match(ABREPARENTE);
			setState(203);
			variavel();
			setState(204);
			match(FECHAPARENTE);
			setState(205);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EscritaContext extends ParserRuleContext {
		public TerminalNode ESCREVA() { return getToken(TarbaloParser.ESCREVA, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<TerminalNode> PONTIVIRGULA() { return getTokens(TarbaloParser.PONTIVIRGULA); }
		public TerminalNode PONTIVIRGULA(int i) {
			return getToken(TarbaloParser.PONTIVIRGULA, i);
		}
		public EscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitEscrita(this);
		}
	}

	public final EscritaContext escrita() throws RecognitionException {
		EscritaContext _localctx = new EscritaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_escrita);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(ESCREVA);
			setState(208);
			match(ABREPARENTE);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233934950370902016L) != 0)) {
				{
				setState(209);
				expressao();
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PONTIVIRGULA) {
					{
					{
					setState(210);
					match(PONTIVIRGULA);
					setState(211);
					expressao();
					}
					}
					setState(216);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(219);
			match(FECHAPARENTE);
			setState(220);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DestinoAtribuicaoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public AcessoVetorContext acessoVetor() {
			return getRuleContext(AcessoVetorContext.class,0);
		}
		public DestinoAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destinoAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDestinoAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDestinoAtribuicao(this);
		}
	}

	public final DestinoAtribuicaoContext destinoAtribuicao() throws RecognitionException {
		DestinoAtribuicaoContext _localctx = new DestinoAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_destinoAtribuicao);
		try {
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				acessoVetor();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtribuicaoContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public OperadorAtribuicaoCompostaContext operadorAtribuicaoComposta() {
			return getRuleContext(OperadorAtribuicaoCompostaContext.class,0);
		}
		public AtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAtribuicao(this);
		}
	}

	public final AtribuicaoContext atribuicao() throws RecognitionException {
		AtribuicaoContext _localctx = new AtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_atribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			destinoAtribuicao();
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(227);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
				{
				setState(228);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(231);
			expressao();
			setState(232);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IncrementoContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(TarbaloParser.INCREMENTO, 0); }
		public IncrementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incremento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitIncremento(this);
		}
	}

	public final IncrementoContext incremento() throws RecognitionException {
		IncrementoContext _localctx = new IncrementoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_incremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			destinoAtribuicao();
			setState(235);
			match(INCREMENTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecrementoContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public TerminalNode DECREMENTO() { return getToken(TarbaloParser.DECREMENTO, 0); }
		public DecrementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decremento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDecremento(this);
		}
	}

	public final DecrementoContext decremento() throws RecognitionException {
		DecrementoContext _localctx = new DecrementoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_decremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			destinoAtribuicao();
			setState(238);
			match(DECREMENTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdSeContext extends ParserRuleContext {
		public TerminalNode SE() { return getToken(TarbaloParser.SE, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public List<TerminalNode> ABRECHAVE() { return getTokens(TarbaloParser.ABRECHAVE); }
		public TerminalNode ABRECHAVE(int i) {
			return getToken(TarbaloParser.ABRECHAVE, i);
		}
		public List<BlocoContext> bloco() {
			return getRuleContexts(BlocoContext.class);
		}
		public BlocoContext bloco(int i) {
			return getRuleContext(BlocoContext.class,i);
		}
		public List<TerminalNode> FECHACHAVE() { return getTokens(TarbaloParser.FECHACHAVE); }
		public TerminalNode FECHACHAVE(int i) {
			return getToken(TarbaloParser.FECHACHAVE, i);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public TerminalNode SENAO() { return getToken(TarbaloParser.SENAO, 0); }
		public CmdSeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdSe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdSe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdSe(this);
		}
	}

	public final CmdSeContext cmdSe() throws RecognitionException {
		CmdSeContext _localctx = new CmdSeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(SE);
			setState(241);
			match(ABREPARENTE);
			setState(242);
			expressao();
			setState(243);
			match(FECHAPARENTE);
			setState(244);
			match(ABRECHAVE);
			setState(245);
			bloco();
			setState(246);
			match(FECHACHAVE);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENAO) {
				{
				setState(247);
				match(SENAO);
				setState(248);
				match(ABRECHAVE);
				setState(249);
				bloco();
				setState(250);
				match(FECHACHAVE);
				}
			}

			setState(254);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode ENQUANTO() { return getToken(TarbaloParser.ENQUANTO, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode ABRECHAVE() { return getToken(TarbaloParser.ABRECHAVE, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode FECHACHAVE() { return getToken(TarbaloParser.FECHACHAVE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_cmdEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(ENQUANTO);
			setState(257);
			match(ABREPARENTE);
			setState(258);
			expressao();
			setState(259);
			match(FECHAPARENTE);
			setState(260);
			match(ABRECHAVE);
			setState(261);
			bloco();
			setState(262);
			match(FECHACHAVE);
			setState(263);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdFacaEnquantoContext extends ParserRuleContext {
		public TerminalNode FACA() { return getToken(TarbaloParser.FACA, 0); }
		public TerminalNode ABRECHAVE() { return getToken(TarbaloParser.ABRECHAVE, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode FECHACHAVE() { return getToken(TarbaloParser.FECHACHAVE, 0); }
		public TerminalNode ENQUANTO() { return getToken(TarbaloParser.ENQUANTO, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public CmdFacaEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFacaEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdFacaEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdFacaEnquanto(this);
		}
	}

	public final CmdFacaEnquantoContext cmdFacaEnquanto() throws RecognitionException {
		CmdFacaEnquantoContext _localctx = new CmdFacaEnquantoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_cmdFacaEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(FACA);
			setState(266);
			match(ABRECHAVE);
			setState(267);
			bloco();
			setState(268);
			match(FECHACHAVE);
			setState(269);
			match(ENQUANTO);
			setState(270);
			match(ABREPARENTE);
			setState(271);
			expressao();
			setState(272);
			match(FECHAPARENTE);
			setState(273);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode PARA() { return getToken(TarbaloParser.PARA, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public AtribuicaoParaContext atribuicaoPara() {
			return getRuleContext(AtribuicaoParaContext.class,0);
		}
		public List<TerminalNode> PONTO() { return getTokens(TarbaloParser.PONTO); }
		public TerminalNode PONTO(int i) {
			return getToken(TarbaloParser.PONTO, i);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public AtualizacaoParaContext atualizacaoPara() {
			return getRuleContext(AtualizacaoParaContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode ABRECHAVE() { return getToken(TarbaloParser.ABRECHAVE, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode FECHACHAVE() { return getToken(TarbaloParser.FECHACHAVE, 0); }
		public CmdParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdPara(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cmdPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(PARA);
			setState(276);
			match(ABREPARENTE);
			setState(277);
			atribuicaoPara();
			setState(278);
			match(PONTO);
			setState(279);
			expressao();
			setState(280);
			match(PONTO);
			setState(281);
			atualizacaoPara();
			setState(282);
			match(FECHAPARENTE);
			setState(283);
			match(ABRECHAVE);
			setState(284);
			bloco();
			setState(285);
			match(FECHACHAVE);
			setState(286);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtribuicaoParaContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public OperadorAtribuicaoCompostaContext operadorAtribuicaoComposta() {
			return getRuleContext(OperadorAtribuicaoCompostaContext.class,0);
		}
		public AtribuicaoParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribuicaoPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAtribuicaoPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAtribuicaoPara(this);
		}
	}

	public final AtribuicaoParaContext atribuicaoPara() throws RecognitionException {
		AtribuicaoParaContext _localctx = new AtribuicaoParaContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_atribuicaoPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			destinoAtribuicao();
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(289);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
				{
				setState(290);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(293);
			expressao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtualizacaoParaContext extends ParserRuleContext {
		public IncrementoParaContext incrementoPara() {
			return getRuleContext(IncrementoParaContext.class,0);
		}
		public DecrementoParaContext decrementoPara() {
			return getRuleContext(DecrementoParaContext.class,0);
		}
		public AtribuicaoParaContext atribuicaoPara() {
			return getRuleContext(AtribuicaoParaContext.class,0);
		}
		public AtribuicaoCompostaParaContext atribuicaoCompostaPara() {
			return getRuleContext(AtribuicaoCompostaParaContext.class,0);
		}
		public AtualizacaoParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atualizacaoPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAtualizacaoPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAtualizacaoPara(this);
		}
	}

	public final AtualizacaoParaContext atualizacaoPara() throws RecognitionException {
		AtualizacaoParaContext _localctx = new AtualizacaoParaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_atualizacaoPara);
		try {
			setState(299);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				incrementoPara();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				decrementoPara();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				atribuicaoPara();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				atribuicaoCompostaPara();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IncrementoParaContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(TarbaloParser.INCREMENTO, 0); }
		public IncrementoParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incrementoPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterIncrementoPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitIncrementoPara(this);
		}
	}

	public final IncrementoParaContext incrementoPara() throws RecognitionException {
		IncrementoParaContext _localctx = new IncrementoParaContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_incrementoPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			destinoAtribuicao();
			setState(302);
			match(INCREMENTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecrementoParaContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public TerminalNode DECREMENTO() { return getToken(TarbaloParser.DECREMENTO, 0); }
		public DecrementoParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decrementoPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDecrementoPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDecrementoPara(this);
		}
	}

	public final DecrementoParaContext decrementoPara() throws RecognitionException {
		DecrementoParaContext _localctx = new DecrementoParaContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_decrementoPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			destinoAtribuicao();
			setState(305);
			match(DECREMENTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtribuicaoCompostaParaContext extends ParserRuleContext {
		public DestinoAtribuicaoContext destinoAtribuicao() {
			return getRuleContext(DestinoAtribuicaoContext.class,0);
		}
		public OperadorAtribuicaoCompostaContext operadorAtribuicaoComposta() {
			return getRuleContext(OperadorAtribuicaoCompostaContext.class,0);
		}
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public TerminalNode NUM() { return getToken(TarbaloParser.NUM, 0); }
		public AtribuicaoCompostaParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribuicaoCompostaPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterAtribuicaoCompostaPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitAtribuicaoCompostaPara(this);
		}
	}

	public final AtribuicaoCompostaParaContext atribuicaoCompostaPara() throws RecognitionException {
		AtribuicaoCompostaParaContext _localctx = new AtribuicaoCompostaParaContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_atribuicaoCompostaPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			destinoAtribuicao();
			setState(308);
			operadorAtribuicaoComposta();
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(309);
				variavel();
				}
				break;
			case NUM:
				{
				setState(310);
				match(NUM);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RetornoContext extends ParserRuleContext {
		public TerminalNode RETORNE() { return getToken(TarbaloParser.RETORNE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public RetornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitRetorno(this);
		}
	}

	public final RetornoContext retorno() throws RecognitionException {
		RetornoContext _localctx = new RetornoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_retorno);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(RETORNE);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233934950370902016L) != 0)) {
				{
				setState(314);
				expressao();
				}
			}

			setState(317);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PareContext extends ParserRuleContext {
		public TerminalNode PARE() { return getToken(TarbaloParser.PARE, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public PareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterPare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitPare(this);
		}
	}

	public final PareContext pare() throws RecognitionException {
		PareContext _localctx = new PareContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_pare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(PARE);
			setState(320);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinuarContext extends ParserRuleContext {
		public TerminalNode CONTINUAR() { return getToken(TarbaloParser.CONTINUAR, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public ContinuarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continuar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterContinuar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitContinuar(this);
		}
	}

	public final ContinuarContext continuar() throws RecognitionException {
		ContinuarContext _localctx = new ContinuarContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_continuar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			match(CONTINUAR);
			setState(323);
			match(PONTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoContext extends ParserRuleContext {
		public ExpressaoOuContext expressaoOu() {
			return getRuleContext(ExpressaoOuContext.class,0);
		}
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressao(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expressao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			expressaoOu();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoOuContext extends ParserRuleContext {
		public List<ExpressaoXorContext> expressaoXor() {
			return getRuleContexts(ExpressaoXorContext.class);
		}
		public ExpressaoXorContext expressaoXor(int i) {
			return getRuleContext(ExpressaoXorContext.class,i);
		}
		public List<TerminalNode> OU() { return getTokens(TarbaloParser.OU); }
		public TerminalNode OU(int i) {
			return getToken(TarbaloParser.OU, i);
		}
		public ExpressaoOuContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoOu; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoOu(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoOu(this);
		}
	}

	public final ExpressaoOuContext expressaoOu() throws RecognitionException {
		ExpressaoOuContext _localctx = new ExpressaoOuContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expressaoOu);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			expressaoXor();
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OU) {
				{
				{
				setState(328);
				match(OU);
				setState(329);
				expressaoXor();
				}
				}
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoXorContext extends ParserRuleContext {
		public List<ExpressaoEContext> expressaoE() {
			return getRuleContexts(ExpressaoEContext.class);
		}
		public ExpressaoEContext expressaoE(int i) {
			return getRuleContext(ExpressaoEContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(TarbaloParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(TarbaloParser.XOR, i);
		}
		public ExpressaoXorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoXor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoXor(this);
		}
	}

	public final ExpressaoXorContext expressaoXor() throws RecognitionException {
		ExpressaoXorContext _localctx = new ExpressaoXorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_expressaoXor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			expressaoE();
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(336);
				match(XOR);
				setState(337);
				expressaoE();
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoEContext extends ParserRuleContext {
		public List<ExpressaoNegacaoContext> expressaoNegacao() {
			return getRuleContexts(ExpressaoNegacaoContext.class);
		}
		public ExpressaoNegacaoContext expressaoNegacao(int i) {
			return getRuleContext(ExpressaoNegacaoContext.class,i);
		}
		public List<TerminalNode> E() { return getTokens(TarbaloParser.E); }
		public TerminalNode E(int i) {
			return getToken(TarbaloParser.E, i);
		}
		public ExpressaoEContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoE; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoE(this);
		}
	}

	public final ExpressaoEContext expressaoE() throws RecognitionException {
		ExpressaoEContext _localctx = new ExpressaoEContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_expressaoE);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			expressaoNegacao();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==E) {
				{
				{
				setState(344);
				match(E);
				setState(345);
				expressaoNegacao();
				}
				}
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoNegacaoContext extends ParserRuleContext {
		public TerminalNode NAO() { return getToken(TarbaloParser.NAO, 0); }
		public ExpressaoNegacaoContext expressaoNegacao() {
			return getRuleContext(ExpressaoNegacaoContext.class,0);
		}
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ExpressaoNegacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoNegacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoNegacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoNegacao(this);
		}
	}

	public final ExpressaoNegacaoContext expressaoNegacao() throws RecognitionException {
		ExpressaoNegacaoContext _localctx = new ExpressaoNegacaoContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressaoNegacao);
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAO:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				match(NAO);
				setState(352);
				expressaoNegacao();
				}
				break;
			case VERDADEIRO:
			case FALSO:
			case MAIS:
			case MENOS:
			case ABREPARENTE:
			case NUMDEC:
			case NUM:
			case CHAR:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				expressaoRelacional();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAditivaContext> expressaoAditiva() {
			return getRuleContexts(ExpressaoAditivaContext.class);
		}
		public ExpressaoAditivaContext expressaoAditiva(int i) {
			return getRuleContext(ExpressaoAditivaContext.class,i);
		}
		public OperadorRelacionalContext operadorRelacional() {
			return getRuleContext(OperadorRelacionalContext.class,0);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoRelacional(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			expressaoAditiva();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8658654068736L) != 0)) {
				{
				setState(357);
				operadorRelacional();
				setState(358);
				expressaoAditiva();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoAditivaContext extends ParserRuleContext {
		public List<ExpressaoMultiplicativaContext> expressaoMultiplicativa() {
			return getRuleContexts(ExpressaoMultiplicativaContext.class);
		}
		public ExpressaoMultiplicativaContext expressaoMultiplicativa(int i) {
			return getRuleContext(ExpressaoMultiplicativaContext.class,i);
		}
		public List<TerminalNode> MAIS() { return getTokens(TarbaloParser.MAIS); }
		public TerminalNode MAIS(int i) {
			return getToken(TarbaloParser.MAIS, i);
		}
		public List<TerminalNode> MENOS() { return getTokens(TarbaloParser.MENOS); }
		public TerminalNode MENOS(int i) {
			return getToken(TarbaloParser.MENOS, i);
		}
		public List<TerminalNode> CONCAT() { return getTokens(TarbaloParser.CONCAT); }
		public TerminalNode CONCAT(int i) {
			return getToken(TarbaloParser.CONCAT, i);
		}
		public ExpressaoAditivaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAditiva; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoAditiva(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoAditiva(this);
		}
	}

	public final ExpressaoAditivaContext expressaoAditiva() throws RecognitionException {
		ExpressaoAditivaContext _localctx = new ExpressaoAditivaContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_expressaoAditiva);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			expressaoMultiplicativa();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35970351104L) != 0)) {
				{
				{
				setState(363);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 35970351104L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(364);
				expressaoMultiplicativa();
				}
				}
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoMultiplicativaContext extends ParserRuleContext {
		public List<ExpressaoUnariaContext> expressaoUnaria() {
			return getRuleContexts(ExpressaoUnariaContext.class);
		}
		public ExpressaoUnariaContext expressaoUnaria(int i) {
			return getRuleContext(ExpressaoUnariaContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(TarbaloParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(TarbaloParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(TarbaloParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(TarbaloParser.DIV, i);
		}
		public List<TerminalNode> DIVINT() { return getTokens(TarbaloParser.DIVINT); }
		public TerminalNode DIVINT(int i) {
			return getToken(TarbaloParser.DIVINT, i);
		}
		public List<TerminalNode> MOD() { return getTokens(TarbaloParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(TarbaloParser.MOD, i);
		}
		public ExpressaoMultiplicativaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoMultiplicativa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoMultiplicativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoMultiplicativa(this);
		}
	}

	public final ExpressaoMultiplicativaContext expressaoMultiplicativa() throws RecognitionException {
		ExpressaoMultiplicativaContext _localctx = new ExpressaoMultiplicativaContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_expressaoMultiplicativa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			expressaoUnaria();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) {
				{
				{
				setState(371);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(372);
				expressaoUnaria();
				}
				}
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoUnariaContext extends ParserRuleContext {
		public TerminalNode MENOS() { return getToken(TarbaloParser.MENOS, 0); }
		public ExpressaoUnariaContext expressaoUnaria() {
			return getRuleContext(ExpressaoUnariaContext.class,0);
		}
		public TerminalNode MAIS() { return getToken(TarbaloParser.MAIS, 0); }
		public OperandoContext operando() {
			return getRuleContext(OperandoContext.class,0);
		}
		public ExpressaoUnariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoUnaria; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoUnaria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoUnaria(this);
		}
	}

	public final ExpressaoUnariaContext expressaoUnaria() throws RecognitionException {
		ExpressaoUnariaContext _localctx = new ExpressaoUnariaContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_expressaoUnaria);
		try {
			setState(383);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MENOS:
				enterOuterAlt(_localctx, 1);
				{
				setState(378);
				match(MENOS);
				setState(379);
				expressaoUnaria();
				}
				break;
			case MAIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(380);
				match(MAIS);
				setState(381);
				expressaoUnaria();
				}
				break;
			case VERDADEIRO:
			case FALSO:
			case ABREPARENTE:
			case NUMDEC:
			case NUM:
			case CHAR:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(382);
				operando();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperandoContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(TarbaloParser.NUM, 0); }
		public TerminalNode NUMDEC() { return getToken(TarbaloParser.NUMDEC, 0); }
		public TerminalNode STRING() { return getToken(TarbaloParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(TarbaloParser.CHAR, 0); }
		public TerminalNode VERDADEIRO() { return getToken(TarbaloParser.VERDADEIRO, 0); }
		public TerminalNode FALSO() { return getToken(TarbaloParser.FALSO, 0); }
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public AcessoVetorContext acessoVetor() {
			return getRuleContext(AcessoVetorContext.class,0);
		}
		public ChamadaFuncaoContext chamadaFuncao() {
			return getRuleContext(ChamadaFuncaoContext.class,0);
		}
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public OperandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterOperando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitOperando(this);
		}
	}

	public final OperandoContext operando() throws RecognitionException {
		OperandoContext _localctx = new OperandoContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_operando);
		try {
			setState(398);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(NUM);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(386);
				match(NUMDEC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(387);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(388);
				match(CHAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(389);
				match(VERDADEIRO);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(390);
				match(FALSO);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(391);
				match(ID);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(392);
				acessoVetor();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(393);
				chamadaFuncao();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(394);
				match(ABREPARENTE);
				setState(395);
				expressao();
				setState(396);
				match(FECHAPARENTE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ChamadaFuncaoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public ChamadaFuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chamadaFuncao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterChamadaFuncao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitChamadaFuncao(this);
		}
	}

	public final ChamadaFuncaoContext chamadaFuncao() throws RecognitionException {
		ChamadaFuncaoContext _localctx = new ChamadaFuncaoContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_chamadaFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(ID);
			setState(401);
			match(ABREPARENTE);
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233934950370902016L) != 0)) {
				{
				setState(402);
				argumentos();
				}
			}

			setState(405);
			match(FECHAPARENTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentosContext extends ParserRuleContext {
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<TerminalNode> PONTIVIRGULA() { return getTokens(TarbaloParser.PONTIVIRGULA); }
		public TerminalNode PONTIVIRGULA(int i) {
			return getToken(TarbaloParser.PONTIVIRGULA, i);
		}
		public ArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterArgumentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitArgumentos(this);
		}
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			expressao();
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTIVIRGULA) {
				{
				{
				setState(408);
				match(PONTIVIRGULA);
				setState(409);
				expressao();
				}
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperadorRelacionalContext extends ParserRuleContext {
		public TerminalNode MENOR() { return getToken(TarbaloParser.MENOR, 0); }
		public TerminalNode MAIOR() { return getToken(TarbaloParser.MAIOR, 0); }
		public TerminalNode MENORIGUAL() { return getToken(TarbaloParser.MENORIGUAL, 0); }
		public TerminalNode MAIORIGUAL() { return getToken(TarbaloParser.MAIORIGUAL, 0); }
		public TerminalNode IGUAL() { return getToken(TarbaloParser.IGUAL, 0); }
		public TerminalNode DIFERENTE() { return getToken(TarbaloParser.DIFERENTE, 0); }
		public OperadorRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operadorRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterOperadorRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitOperadorRelacional(this);
		}
	}

	public final OperadorRelacionalContext operadorRelacional() throws RecognitionException {
		OperadorRelacionalContext _localctx = new OperadorRelacionalContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_operadorRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8658654068736L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperadorAtribuicaoCompostaContext extends ParserRuleContext {
		public TerminalNode SOMA_ATRIBUICAO() { return getToken(TarbaloParser.SOMA_ATRIBUICAO, 0); }
		public TerminalNode SUBTRACAO_ATRIBUICAO() { return getToken(TarbaloParser.SUBTRACAO_ATRIBUICAO, 0); }
		public TerminalNode MULT_ATRIBUICAO() { return getToken(TarbaloParser.MULT_ATRIBUICAO, 0); }
		public TerminalNode DIV_ATRIBUICAO() { return getToken(TarbaloParser.DIV_ATRIBUICAO, 0); }
		public TerminalNode MOD_ATRIBUICAO() { return getToken(TarbaloParser.MOD_ATRIBUICAO, 0); }
		public OperadorAtribuicaoCompostaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operadorAtribuicaoComposta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterOperadorAtribuicaoComposta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitOperadorAtribuicaoComposta(this);
		}
	}

	public final OperadorAtribuicaoCompostaContext operadorAtribuicaoComposta() throws RecognitionException {
		OperadorAtribuicaoCompostaContext _localctx = new OperadorAtribuicaoCompostaContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_operadorAtribuicaoComposta);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 130023424L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001?\u01a4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001"+
		"i\b\u0001\n\u0001\f\u0001l\t\u0001\u0001\u0002\u0001\u0002\u0003\u0002"+
		"p\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"v\b\u0003\n\u0003\f\u0003y\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0005\u0004\u007f\b\u0004\n\u0004\f\u0004\u0082\t\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u0088\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0004\b\u0090"+
		"\b\b\u000b\b\f\b\u0091\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0003\n\u009d\b\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00a8"+
		"\b\u000b\n\u000b\f\u000b\u00ab\t\u000b\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\u000e\u0005\u000e\u00b3\b\u000e\n\u000e\f\u000e\u00b6\t"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00c8"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u00d5\b\u0011\n\u0011\f\u0011\u00d8\t\u0011\u0003\u0011\u00da\b"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u00e1\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00e6"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00fd\b\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0124\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u012c\b\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0138\b\u001e\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u013c\b\u001f\u0001\u001f\u0001\u001f\u0001 \u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0005"+
		"#\u014b\b#\n#\f#\u014e\t#\u0001$\u0001$\u0001$\u0005$\u0153\b$\n$\f$\u0156"+
		"\t$\u0001%\u0001%\u0001%\u0005%\u015b\b%\n%\f%\u015e\t%\u0001&\u0001&"+
		"\u0001&\u0003&\u0163\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u0169"+
		"\b\'\u0001(\u0001(\u0001(\u0005(\u016e\b(\n(\f(\u0171\t(\u0001)\u0001"+
		")\u0001)\u0005)\u0176\b)\n)\f)\u0179\t)\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0003*\u0180\b*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u018f\b+\u0001,\u0001,\u0001"+
		",\u0003,\u0194\b,\u0001,\u0001,\u0001-\u0001-\u0001-\u0005-\u019b\b-\n"+
		"-\f-\u019e\t-\u0001.\u0001.\u0001/\u0001/\u0001/\u0000\u00000\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^\u0000\u0005\u0001\u0000\u0003\u0007"+
		"\u0002\u0000\u001d\u001e##\u0001\u0000\u001f\"\u0001\u0000%*\u0001\u0000"+
		"\u0016\u001a\u01a8\u0000`\u0001\u0000\u0000\u0000\u0002j\u0001\u0000\u0000"+
		"\u0000\u0004o\u0001\u0000\u0000\u0000\u0006q\u0001\u0000\u0000\u0000\b"+
		"|\u0001\u0000\u0000\u0000\n\u0083\u0001\u0000\u0000\u0000\f\u0087\u0001"+
		"\u0000\u0000\u0000\u000e\u0089\u0001\u0000\u0000\u0000\u0010\u008d\u0001"+
		"\u0000\u0000\u0000\u0012\u0093\u0001\u0000\u0000\u0000\u0014\u0097\u0001"+
		"\u0000\u0000\u0000\u0016\u00a4\u0001\u0000\u0000\u0000\u0018\u00ac\u0001"+
		"\u0000\u0000\u0000\u001a\u00af\u0001\u0000\u0000\u0000\u001c\u00b4\u0001"+
		"\u0000\u0000\u0000\u001e\u00c7\u0001\u0000\u0000\u0000 \u00c9\u0001\u0000"+
		"\u0000\u0000\"\u00cf\u0001\u0000\u0000\u0000$\u00e0\u0001\u0000\u0000"+
		"\u0000&\u00e2\u0001\u0000\u0000\u0000(\u00ea\u0001\u0000\u0000\u0000*"+
		"\u00ed\u0001\u0000\u0000\u0000,\u00f0\u0001\u0000\u0000\u0000.\u0100\u0001"+
		"\u0000\u0000\u00000\u0109\u0001\u0000\u0000\u00002\u0113\u0001\u0000\u0000"+
		"\u00004\u0120\u0001\u0000\u0000\u00006\u012b\u0001\u0000\u0000\u00008"+
		"\u012d\u0001\u0000\u0000\u0000:\u0130\u0001\u0000\u0000\u0000<\u0133\u0001"+
		"\u0000\u0000\u0000>\u0139\u0001\u0000\u0000\u0000@\u013f\u0001\u0000\u0000"+
		"\u0000B\u0142\u0001\u0000\u0000\u0000D\u0145\u0001\u0000\u0000\u0000F"+
		"\u0147\u0001\u0000\u0000\u0000H\u014f\u0001\u0000\u0000\u0000J\u0157\u0001"+
		"\u0000\u0000\u0000L\u0162\u0001\u0000\u0000\u0000N\u0164\u0001\u0000\u0000"+
		"\u0000P\u016a\u0001\u0000\u0000\u0000R\u0172\u0001\u0000\u0000\u0000T"+
		"\u017f\u0001\u0000\u0000\u0000V\u018e\u0001\u0000\u0000\u0000X\u0190\u0001"+
		"\u0000\u0000\u0000Z\u0197\u0001\u0000\u0000\u0000\\\u019f\u0001\u0000"+
		"\u0000\u0000^\u01a1\u0001\u0000\u0000\u0000`a\u0005\u0001\u0000\u0000"+
		"ab\u0003\u0002\u0001\u0000bc\u0003\u001a\r\u0000cd\u0005\u0002\u0000\u0000"+
		"de\u00057\u0000\u0000ef\u0005\u0000\u0000\u0001f\u0001\u0001\u0000\u0000"+
		"\u0000gi\u0003\u0004\u0002\u0000hg\u0001\u0000\u0000\u0000il\u0001\u0000"+
		"\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000k\u0003"+
		"\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mp\u0003\u0006\u0003"+
		"\u0000np\u0003\u0014\n\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000"+
		"\u0000p\u0005\u0001\u0000\u0000\u0000qr\u0003\n\u0005\u0000rw\u0003\b"+
		"\u0004\u0000st\u00055\u0000\u0000tv\u0003\b\u0004\u0000us\u0001\u0000"+
		"\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000"+
		"z{\u00057\u0000\u0000{\u0007\u0001\u0000\u0000\u0000|\u0080\u0005<\u0000"+
		"\u0000}\u007f\u0003\u000e\u0007\u0000~}\u0001\u0000\u0000\u0000\u007f"+
		"\u0082\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0001\u0000\u0000\u0000\u0081\t\u0001\u0000\u0000\u0000\u0082\u0080\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0007\u0000\u0000\u0000\u0084\u000b\u0001"+
		"\u0000\u0000\u0000\u0085\u0088\u0003\n\u0005\u0000\u0086\u0088\u0005\b"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0086\u0001\u0000"+
		"\u0000\u0000\u0088\r\u0001\u0000\u0000\u0000\u0089\u008a\u00053\u0000"+
		"\u0000\u008a\u008b\u00059\u0000\u0000\u008b\u008c\u00054\u0000\u0000\u008c"+
		"\u000f\u0001\u0000\u0000\u0000\u008d\u008f\u0005<\u0000\u0000\u008e\u0090"+
		"\u0003\u0012\t\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001"+
		"\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0011\u0001\u0000\u0000\u0000\u0093\u0094\u0005"+
		"3\u0000\u0000\u0094\u0095\u0003D\"\u0000\u0095\u0096\u00054\u0000\u0000"+
		"\u0096\u0013\u0001\u0000\u0000\u0000\u0097\u0098\u0005\t\u0000\u0000\u0098"+
		"\u0099\u0003\f\u0006\u0000\u0099\u009a\u0005<\u0000\u0000\u009a\u009c"+
		"\u0005/\u0000\u0000\u009b\u009d\u0003\u0016\u000b\u0000\u009c\u009b\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u00050\u0000\u0000\u009f\u00a0\u00051\u0000"+
		"\u0000\u00a0\u00a1\u0003\u001c\u000e\u0000\u00a1\u00a2\u00052\u0000\u0000"+
		"\u00a2\u00a3\u00057\u0000\u0000\u00a3\u0015\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a9\u0003\u0018\f\u0000\u00a5\u00a6\u00056\u0000\u0000\u00a6\u00a8"+
		"\u0003\u0018\f\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00ab\u0001"+
		"\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001"+
		"\u0000\u0000\u0000\u00aa\u0017\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0003\n\u0005\u0000\u00ad\u00ae\u0003\b"+
		"\u0004\u0000\u00ae\u0019\u0001\u0000\u0000\u0000\u00af\u00b0\u0003\u001c"+
		"\u000e\u0000\u00b0\u001b\u0001\u0000\u0000\u0000\u00b1\u00b3\u0003\u001e"+
		"\u000f\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b5\u001d\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00c8\u0003\u0006\u0003\u0000\u00b8\u00c8\u0003&\u0013"+
		"\u0000\u00b9\u00c8\u0003 \u0010\u0000\u00ba\u00c8\u0003\"\u0011\u0000"+
		"\u00bb\u00c8\u0003,\u0016\u0000\u00bc\u00c8\u0003.\u0017\u0000\u00bd\u00c8"+
		"\u00030\u0018\u0000\u00be\u00c8\u00032\u0019\u0000\u00bf\u00c8\u0003>"+
		"\u001f\u0000\u00c0\u00c8\u0003@ \u0000\u00c1\u00c8\u0003B!\u0000\u00c2"+
		"\u00c8\u0003(\u0014\u0000\u00c3\u00c8\u0003*\u0015\u0000\u00c4\u00c5\u0003"+
		"X,\u0000\u00c5\u00c6\u00057\u0000\u0000\u00c6\u00c8\u0001\u0000\u0000"+
		"\u0000\u00c7\u00b7\u0001\u0000\u0000\u0000\u00c7\u00b8\u0001\u0000\u0000"+
		"\u0000\u00c7\u00b9\u0001\u0000\u0000\u0000\u00c7\u00ba\u0001\u0000\u0000"+
		"\u0000\u00c7\u00bb\u0001\u0000\u0000\u0000\u00c7\u00bc\u0001\u0000\u0000"+
		"\u0000\u00c7\u00bd\u0001\u0000\u0000\u0000\u00c7\u00be\u0001\u0000\u0000"+
		"\u0000\u00c7\u00bf\u0001\u0000\u0000\u0000\u00c7\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c1\u0001\u0000\u0000\u0000\u00c7\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c3\u0001\u0000\u0000\u0000\u00c7\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c8\u001f\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005\u000b\u0000"+
		"\u0000\u00ca\u00cb\u0005/\u0000\u0000\u00cb\u00cc\u0003\b\u0004\u0000"+
		"\u00cc\u00cd\u00050\u0000\u0000\u00cd\u00ce\u00057\u0000\u0000\u00ce!"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\f\u0000\u0000\u00d0\u00d9\u0005"+
		"/\u0000\u0000\u00d1\u00d6\u0003D\"\u0000\u00d2\u00d3\u00056\u0000\u0000"+
		"\u00d3\u00d5\u0003D\"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d9\u00d1\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"\u00dc\u00050\u0000\u0000\u00dc\u00dd\u00057\u0000\u0000\u00dd#\u0001"+
		"\u0000\u0000\u0000\u00de\u00e1\u0005<\u0000\u0000\u00df\u00e1\u0003\u0010"+
		"\b\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1%\u0001\u0000\u0000\u0000\u00e2\u00e5\u0003$\u0012\u0000\u00e3"+
		"\u00e6\u0005$\u0000\u0000\u00e4\u00e6\u0003^/\u0000\u00e5\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0003D\"\u0000\u00e8\u00e9\u00057\u0000"+
		"\u0000\u00e9\'\u0001\u0000\u0000\u0000\u00ea\u00eb\u0003$\u0012\u0000"+
		"\u00eb\u00ec\u0005\u001b\u0000\u0000\u00ec)\u0001\u0000\u0000\u0000\u00ed"+
		"\u00ee\u0003$\u0012\u0000\u00ee\u00ef\u0005\u001c\u0000\u0000\u00ef+\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0005\r\u0000\u0000\u00f1\u00f2\u0005/"+
		"\u0000\u0000\u00f2\u00f3\u0003D\"\u0000\u00f3\u00f4\u00050\u0000\u0000"+
		"\u00f4\u00f5\u00051\u0000\u0000\u00f5\u00f6\u0003\u001c\u000e\u0000\u00f6"+
		"\u00fc\u00052\u0000\u0000\u00f7\u00f8\u0005\u000e\u0000\u0000\u00f8\u00f9"+
		"\u00051\u0000\u0000\u00f9\u00fa\u0003\u001c\u000e\u0000\u00fa\u00fb\u0005"+
		"2\u0000\u0000\u00fb\u00fd\u0001\u0000\u0000\u0000\u00fc\u00f7\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u00057\u0000\u0000\u00ff-\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\u0005\u000f\u0000\u0000\u0101\u0102\u0005/\u0000\u0000\u0102"+
		"\u0103\u0003D\"\u0000\u0103\u0104\u00050\u0000\u0000\u0104\u0105\u0005"+
		"1\u0000\u0000\u0105\u0106\u0003\u001c\u000e\u0000\u0106\u0107\u00052\u0000"+
		"\u0000\u0107\u0108\u00057\u0000\u0000\u0108/\u0001\u0000\u0000\u0000\u0109"+
		"\u010a\u0005\u0010\u0000\u0000\u010a\u010b\u00051\u0000\u0000\u010b\u010c"+
		"\u0003\u001c\u000e\u0000\u010c\u010d\u00052\u0000\u0000\u010d\u010e\u0005"+
		"\u000f\u0000\u0000\u010e\u010f\u0005/\u0000\u0000\u010f\u0110\u0003D\""+
		"\u0000\u0110\u0111\u00050\u0000\u0000\u0111\u0112\u00057\u0000\u0000\u0112"+
		"1\u0001\u0000\u0000\u0000\u0113\u0114\u0005\u0011\u0000\u0000\u0114\u0115"+
		"\u0005/\u0000\u0000\u0115\u0116\u00034\u001a\u0000\u0116\u0117\u00057"+
		"\u0000\u0000\u0117\u0118\u0003D\"\u0000\u0118\u0119\u00057\u0000\u0000"+
		"\u0119\u011a\u00036\u001b\u0000\u011a\u011b\u00050\u0000\u0000\u011b\u011c"+
		"\u00051\u0000\u0000\u011c\u011d\u0003\u001c\u000e\u0000\u011d\u011e\u0005"+
		"2\u0000\u0000\u011e\u011f\u00057\u0000\u0000\u011f3\u0001\u0000\u0000"+
		"\u0000\u0120\u0123\u0003$\u0012\u0000\u0121\u0124\u0005$\u0000\u0000\u0122"+
		"\u0124\u0003^/\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0122\u0001"+
		"\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0126\u0003"+
		"D\"\u0000\u01265\u0001\u0000\u0000\u0000\u0127\u012c\u00038\u001c\u0000"+
		"\u0128\u012c\u0003:\u001d\u0000\u0129\u012c\u00034\u001a\u0000\u012a\u012c"+
		"\u0003<\u001e\u0000\u012b\u0127\u0001\u0000\u0000\u0000\u012b\u0128\u0001"+
		"\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012b\u012a\u0001"+
		"\u0000\u0000\u0000\u012c7\u0001\u0000\u0000\u0000\u012d\u012e\u0003$\u0012"+
		"\u0000\u012e\u012f\u0005\u001b\u0000\u0000\u012f9\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0003$\u0012\u0000\u0131\u0132\u0005\u001c\u0000\u0000\u0132"+
		";\u0001\u0000\u0000\u0000\u0133\u0134\u0003$\u0012\u0000\u0134\u0137\u0003"+
		"^/\u0000\u0135\u0138\u0003\b\u0004\u0000\u0136\u0138\u00059\u0000\u0000"+
		"\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0136\u0001\u0000\u0000\u0000"+
		"\u0138=\u0001\u0000\u0000\u0000\u0139\u013b\u0005\n\u0000\u0000\u013a"+
		"\u013c\u0003D\"\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013b\u013c"+
		"\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u00057\u0000\u0000\u013e?\u0001\u0000\u0000\u0000\u013f\u0140\u0005\u0012"+
		"\u0000\u0000\u0140\u0141\u00057\u0000\u0000\u0141A\u0001\u0000\u0000\u0000"+
		"\u0142\u0143\u0005\u0013\u0000\u0000\u0143\u0144\u00057\u0000\u0000\u0144"+
		"C\u0001\u0000\u0000\u0000\u0145\u0146\u0003F#\u0000\u0146E\u0001\u0000"+
		"\u0000\u0000\u0147\u014c\u0003H$\u0000\u0148\u0149\u0005-\u0000\u0000"+
		"\u0149\u014b\u0003H$\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014b\u014e"+
		"\u0001\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014d"+
		"\u0001\u0000\u0000\u0000\u014dG\u0001\u0000\u0000\u0000\u014e\u014c\u0001"+
		"\u0000\u0000\u0000\u014f\u0154\u0003J%\u0000\u0150\u0151\u0005.\u0000"+
		"\u0000\u0151\u0153\u0003J%\u0000\u0152\u0150\u0001\u0000\u0000\u0000\u0153"+
		"\u0156\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0154"+
		"\u0155\u0001\u0000\u0000\u0000\u0155I\u0001\u0000\u0000\u0000\u0156\u0154"+
		"\u0001\u0000\u0000\u0000\u0157\u015c\u0003L&\u0000\u0158\u0159\u0005,"+
		"\u0000\u0000\u0159\u015b\u0003L&\u0000\u015a\u0158\u0001\u0000\u0000\u0000"+
		"\u015b\u015e\u0001\u0000\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000"+
		"\u015c\u015d\u0001\u0000\u0000\u0000\u015dK\u0001\u0000\u0000\u0000\u015e"+
		"\u015c\u0001\u0000\u0000\u0000\u015f\u0160\u0005+\u0000\u0000\u0160\u0163"+
		"\u0003L&\u0000\u0161\u0163\u0003N\'\u0000\u0162\u015f\u0001\u0000\u0000"+
		"\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0163M\u0001\u0000\u0000\u0000"+
		"\u0164\u0168\u0003P(\u0000\u0165\u0166\u0003\\.\u0000\u0166\u0167\u0003"+
		"P(\u0000\u0167\u0169\u0001\u0000\u0000\u0000\u0168\u0165\u0001\u0000\u0000"+
		"\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169O\u0001\u0000\u0000\u0000"+
		"\u016a\u016f\u0003R)\u0000\u016b\u016c\u0007\u0001\u0000\u0000\u016c\u016e"+
		"\u0003R)\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016e\u0171\u0001\u0000"+
		"\u0000\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000"+
		"\u0000\u0000\u0170Q\u0001\u0000\u0000\u0000\u0171\u016f\u0001\u0000\u0000"+
		"\u0000\u0172\u0177\u0003T*\u0000\u0173\u0174\u0007\u0002\u0000\u0000\u0174"+
		"\u0176\u0003T*\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0176\u0179\u0001"+
		"\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0177\u0178\u0001"+
		"\u0000\u0000\u0000\u0178S\u0001\u0000\u0000\u0000\u0179\u0177\u0001\u0000"+
		"\u0000\u0000\u017a\u017b\u0005\u001e\u0000\u0000\u017b\u0180\u0003T*\u0000"+
		"\u017c\u017d\u0005\u001d\u0000\u0000\u017d\u0180\u0003T*\u0000\u017e\u0180"+
		"\u0003V+\u0000\u017f\u017a\u0001\u0000\u0000\u0000\u017f\u017c\u0001\u0000"+
		"\u0000\u0000\u017f\u017e\u0001\u0000\u0000\u0000\u0180U\u0001\u0000\u0000"+
		"\u0000\u0181\u018f\u00059\u0000\u0000\u0182\u018f\u00058\u0000\u0000\u0183"+
		"\u018f\u0005;\u0000\u0000\u0184\u018f\u0005:\u0000\u0000\u0185\u018f\u0005"+
		"\u0014\u0000\u0000\u0186\u018f\u0005\u0015\u0000\u0000\u0187\u018f\u0005"+
		"<\u0000\u0000\u0188\u018f\u0003\u0010\b\u0000\u0189\u018f\u0003X,\u0000"+
		"\u018a\u018b\u0005/\u0000\u0000\u018b\u018c\u0003D\"\u0000\u018c\u018d"+
		"\u00050\u0000\u0000\u018d\u018f\u0001\u0000\u0000\u0000\u018e\u0181\u0001"+
		"\u0000\u0000\u0000\u018e\u0182\u0001\u0000\u0000\u0000\u018e\u0183\u0001"+
		"\u0000\u0000\u0000\u018e\u0184\u0001\u0000\u0000\u0000\u018e\u0185\u0001"+
		"\u0000\u0000\u0000\u018e\u0186\u0001\u0000\u0000\u0000\u018e\u0187\u0001"+
		"\u0000\u0000\u0000\u018e\u0188\u0001\u0000\u0000\u0000\u018e\u0189\u0001"+
		"\u0000\u0000\u0000\u018e\u018a\u0001\u0000\u0000\u0000\u018fW\u0001\u0000"+
		"\u0000\u0000\u0190\u0191\u0005<\u0000\u0000\u0191\u0193\u0005/\u0000\u0000"+
		"\u0192\u0194\u0003Z-\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0193\u0194"+
		"\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000\u0195\u0196"+
		"\u00050\u0000\u0000\u0196Y\u0001\u0000\u0000\u0000\u0197\u019c\u0003D"+
		"\"\u0000\u0198\u0199\u00056\u0000\u0000\u0199\u019b\u0003D\"\u0000\u019a"+
		"\u0198\u0001\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c"+
		"\u019a\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d"+
		"[\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f\u01a0"+
		"\u0007\u0003\u0000\u0000\u01a0]\u0001\u0000\u0000\u0000\u01a1\u01a2\u0007"+
		"\u0004\u0000\u0000\u01a2_\u0001\u0000\u0000\u0000\u001ejow\u0080\u0087"+
		"\u0091\u009c\u00a9\u00b4\u00c7\u00d6\u00d9\u00e0\u00e5\u00fc\u0123\u012b"+
		"\u0137\u013b\u014c\u0154\u015c\u0162\u0168\u016f\u0177\u017f\u018e\u0193"+
		"\u019c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}