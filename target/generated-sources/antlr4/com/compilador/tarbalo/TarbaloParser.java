// Generated from com/compilador/tarbalo/Tarbalo.g4 by ANTLR 4.13.1
package com.compilador.tarbalo;
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
		INICIO=1, FIM=2, VARIAVEL=3, VETOR=4, INTEIRO=5, DECIMAL=6, CARACTERE=7, 
		TEXTO_TIPO=8, BOOLEANO_TIPO=9, VAZIO=10, FUNCAO=11, RETORNE=12, LEIA=13, 
		ESCREVA=14, SE=15, SENAO=16, ENQUANTO=17, FACA=18, PARA=19, PARACADA=20, 
		EM=21, PARE=22, CONTINUAR=23, VERDADEIRO=24, FALSO=25, PACOTE=26, SOMA_ATRIBUICAO=27, 
		SUBTRACAO_ATRIBUICAO=28, MULT_ATRIBUICAO=29, DIV_ATRIBUICAO=30, MOD_ATRIBUICAO=31, 
		INCREMENTO=32, DECREMENTO=33, PONTOPONTO=34, MAIS=35, MENOS=36, MULT=37, 
		DIVINT=38, DIV=39, MOD=40, CONCAT=41, ATRIBUICAO=42, MENORIGUAL=43, MAIORIGUAL=44, 
		DIFERENTE=45, MENOR=46, MAIOR=47, IGUAL=48, NAO=49, E=50, OU=51, XOR=52, 
		ABREPARENTE=53, FECHAPARENTE=54, ABRECHAVE=55, FECHACHAVE=56, ABRECOLCHETE=57, 
		FECHACOLCHETE=58, VIRGULA=59, PONTIVIRGULA=60, PONTO=61, NUMDEC=62, NUM=63, 
		CHAR=64, STRING=65, ID=66, COMENTARIO_LINHA=67, COMENTARIO_BLOCO=68, WS=69;
	public static final int
		RULE_programa = 0, RULE_declaracao = 1, RULE_declaracaoVariavel = 2, RULE_variavel = 3, 
		RULE_tipoVariavel = 4, RULE_tipoComposto = 5, RULE_tipoRetorno = 6, RULE_dimensao = 7, 
		RULE_declaracaoVetor = 8, RULE_inicializacaoVetor = 9, RULE_valorAtribuicao = 10, 
		RULE_declaracaoFuncao = 11, RULE_parametros = 12, RULE_parametro = 13, 
		RULE_bloco = 14, RULE_comando = 15, RULE_cmdBloco = 16, RULE_diretiva = 17, 
		RULE_leitura = 18, RULE_escrita = 19, RULE_atribuicao = 20, RULE_incremento = 21, 
		RULE_decremento = 22, RULE_incrementoPonto = 23, RULE_decrementoPonto = 24, 
		RULE_cmdSe = 25, RULE_cmdEnquanto = 26, RULE_cmdFacaEnquanto = 27, RULE_cmdPara = 28, 
		RULE_inicializacaoPara = 29, RULE_atribuicaoPara = 30, RULE_atualizacaoPara = 31, 
		RULE_cmdParaCada = 32, RULE_retorno = 33, RULE_pare = 34, RULE_continuar = 35, 
		RULE_expressao = 36, RULE_expressaoXor = 37, RULE_expressaoE = 38, RULE_expressaoNegacao = 39, 
		RULE_expressaoRelacional = 40, RULE_expressaoAditiva = 41, RULE_expressaoConcatenacao = 42, 
		RULE_expressaoMultiplicativa = 43, RULE_operando = 44, RULE_chamadaFuncao = 45, 
		RULE_argumentos = 46, RULE_operadorRelacional = 47, RULE_operadorAtribuicaoComposta = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracao", "declaracaoVariavel", "variavel", "tipoVariavel", 
			"tipoComposto", "tipoRetorno", "dimensao", "declaracaoVetor", "inicializacaoVetor", 
			"valorAtribuicao", "declaracaoFuncao", "parametros", "parametro", "bloco", 
			"comando", "cmdBloco", "diretiva", "leitura", "escrita", "atribuicao", 
			"incremento", "decremento", "incrementoPonto", "decrementoPonto", "cmdSe", 
			"cmdEnquanto", "cmdFacaEnquanto", "cmdPara", "inicializacaoPara", "atribuicaoPara", 
			"atualizacaoPara", "cmdParaCada", "retorno", "pare", "continuar", "expressao", 
			"expressaoXor", "expressaoE", "expressaoNegacao", "expressaoRelacional", 
			"expressaoAditiva", "expressaoConcatenacao", "expressaoMultiplicativa", 
			"operando", "chamadaFuncao", "argumentos", "operadorRelacional", "operadorAtribuicaoComposta"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'bora'", "'flw'", "'bglh'", "'bond'", "'int'", "'qbd'", "'ltr'", 
			"'txt'", "'lgc'", "'vazio'", "'func'", "'receba'", "'espia'", "'manda'", 
			"'sepa'", "'vish'", "'enquanto'", "'faca'", "'para'", "'pancada'", "'em'", 
			"'morre'", "'dale'", "'VDD'", "'FAKE'", "'usar'", "'+:'", "'-:'", "'*:'", 
			"'/:'", "'%:'", "'++'", "'--'", "'..'", "'+'", "'-'", "'*'", "'//'", 
			"'/'", "'%'", "'&'", "':'", "'<='", "'>='", "'!='", "'<'", "'>'", "'='", 
			"'nao'", "'e'", "'ou'", "'xor'", "'('", "')'", "'{'", "'}'", "'['", "']'", 
			"','", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INICIO", "FIM", "VARIAVEL", "VETOR", "INTEIRO", "DECIMAL", "CARACTERE", 
			"TEXTO_TIPO", "BOOLEANO_TIPO", "VAZIO", "FUNCAO", "RETORNE", "LEIA", 
			"ESCREVA", "SE", "SENAO", "ENQUANTO", "FACA", "PARA", "PARACADA", "EM", 
			"PARE", "CONTINUAR", "VERDADEIRO", "FALSO", "PACOTE", "SOMA_ATRIBUICAO", 
			"SUBTRACAO_ATRIBUICAO", "MULT_ATRIBUICAO", "DIV_ATRIBUICAO", "MOD_ATRIBUICAO", 
			"INCREMENTO", "DECREMENTO", "PONTOPONTO", "MAIS", "MENOS", "MULT", "DIVINT", 
			"DIV", "MOD", "CONCAT", "ATRIBUICAO", "MENORIGUAL", "MAIORIGUAL", "DIFERENTE", 
			"MENOR", "MAIOR", "IGUAL", "NAO", "E", "OU", "XOR", "ABREPARENTE", "FECHAPARENTE", 
			"ABRECHAVE", "FECHACHAVE", "ABRECOLCHETE", "FECHACOLCHETE", "VIRGULA", 
			"PONTIVIRGULA", "PONTO", "NUMDEC", "NUM", "CHAR", "STRING", "ID", "COMENTARIO_LINHA", 
			"COMENTARIO_BLOCO", "WS"
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
		public TerminalNode FIM() { return getToken(TarbaloParser.FIM, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public TerminalNode EOF() { return getToken(TarbaloParser.EOF, 0); }
		public List<DiretivaContext> diretiva() {
			return getRuleContexts(DiretivaContext.class);
		}
		public DiretivaContext diretiva(int i) {
			return getRuleContext(DiretivaContext.class,i);
		}
		public List<BlocoContext> bloco() {
			return getRuleContexts(BlocoContext.class);
		}
		public BlocoContext bloco(int i) {
			return getRuleContext(BlocoContext.class,i);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(INICIO);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PACOTE) {
				{
				{
				setState(99);
				diretiva();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				bloco();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 3)) & ~0x3f) == 0 && ((1L << (_la - 3)) & -9218868437225578749L) != 0) );
			setState(110);
			match(FIM);
			setState(111);
			match(PONTO);
			setState(112);
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
	public static class DeclaracaoContext extends ParserRuleContext {
		public DeclaracaoVariavelContext declaracaoVariavel() {
			return getRuleContext(DeclaracaoVariavelContext.class,0);
		}
		public DeclaracaoVetorContext declaracaoVetor() {
			return getRuleContext(DeclaracaoVetorContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDeclaracao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracao);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIAVEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				declaracaoVariavel();
				}
				break;
			case VETOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				declaracaoVetor();
				}
				break;
			case FUNCAO:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
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
		public TerminalNode VARIAVEL() { return getToken(TarbaloParser.VARIAVEL, 0); }
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDeclaracaoVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoVariavelContext declaracaoVariavel() throws RecognitionException {
		DeclaracaoVariavelContext _localctx = new DeclaracaoVariavelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracaoVariavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(VARIAVEL);
			setState(120);
			tipoVariavel();
			setState(121);
			match(ID);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATRIBUICAO) {
				{
				setState(122);
				match(ATRIBUICAO);
				setState(123);
				expressao();
				}
			}

			setState(126);
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
		public List<DimensaoContext> dimensao() {
			return getRuleContexts(DimensaoContext.class);
		}
		public DimensaoContext dimensao(int i) {
			return getRuleContext(DimensaoContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariavelContext variavel() throws RecognitionException {
		VariavelContext _localctx = new VariavelContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(ID);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABRECOLCHETE) {
				{
				{
				setState(129);
				dimensao();
				}
				}
				setState(134);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitTipoVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoVariavelContext tipoVariavel() throws RecognitionException {
		TipoVariavelContext _localctx = new TipoVariavelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipoVariavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 992L) != 0)) ) {
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
	public static class TipoCompostoContext extends ParserRuleContext {
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public List<DimensaoContext> dimensao() {
			return getRuleContexts(DimensaoContext.class);
		}
		public DimensaoContext dimensao(int i) {
			return getRuleContext(DimensaoContext.class,i);
		}
		public TipoCompostoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoComposto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterTipoComposto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitTipoComposto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitTipoComposto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoCompostoContext tipoComposto() throws RecognitionException {
		TipoCompostoContext _localctx = new TipoCompostoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipoComposto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			tipoVariavel();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABRECOLCHETE) {
				{
				{
				setState(138);
				dimensao();
				}
				}
				setState(143);
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
	public static class TipoRetornoContext extends ParserRuleContext {
		public TipoCompostoContext tipoComposto() {
			return getRuleContext(TipoCompostoContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitTipoRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoRetornoContext tipoRetorno() throws RecognitionException {
		TipoRetornoContext _localctx = new TipoRetornoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tipoRetorno);
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEIRO:
			case DECIMAL:
			case CARACTERE:
			case TEXTO_TIPO:
			case BOOLEANO_TIPO:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				tipoComposto();
				}
				break;
			case VAZIO:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
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
	public static class DimensaoContext extends ParserRuleContext {
		public TerminalNode ABRECOLCHETE() { return getToken(TarbaloParser.ABRECOLCHETE, 0); }
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode PONTOPONTO() { return getToken(TarbaloParser.PONTOPONTO, 0); }
		public DimensaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDimensao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDimensao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDimensao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensaoContext dimensao() throws RecognitionException {
		DimensaoContext _localctx = new DimensaoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dimensao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(ABRECOLCHETE);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785544707L) != 0)) {
				{
				setState(149);
				expressao();
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PONTOPONTO) {
					{
					setState(150);
					match(PONTOPONTO);
					setState(151);
					expressao();
					}
				}

				}
			}

			setState(156);
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
	public static class DeclaracaoVetorContext extends ParserRuleContext {
		public TerminalNode VETOR() { return getToken(TarbaloParser.VETOR, 0); }
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public List<DimensaoContext> dimensao() {
			return getRuleContexts(DimensaoContext.class);
		}
		public DimensaoContext dimensao(int i) {
			return getRuleContext(DimensaoContext.class,i);
		}
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public ValorAtribuicaoContext valorAtribuicao() {
			return getRuleContext(ValorAtribuicaoContext.class,0);
		}
		public DeclaracaoVetorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracaoVetor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDeclaracaoVetor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDeclaracaoVetor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDeclaracaoVetor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoVetorContext declaracaoVetor() throws RecognitionException {
		DeclaracaoVetorContext _localctx = new DeclaracaoVetorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaracaoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(VETOR);
			setState(159);
			tipoVariavel();
			setState(160);
			match(ID);
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(161);
				dimensao();
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ABRECOLCHETE );
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATRIBUICAO) {
				{
				setState(166);
				match(ATRIBUICAO);
				setState(167);
				valorAtribuicao();
				}
			}

			setState(170);
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
	public static class InicializacaoVetorContext extends ParserRuleContext {
		public TerminalNode ABRECOLCHETE() { return getToken(TarbaloParser.ABRECOLCHETE, 0); }
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
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
		public InicializacaoVetorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicializacaoVetor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterInicializacaoVetor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitInicializacaoVetor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitInicializacaoVetor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InicializacaoVetorContext inicializacaoVetor() throws RecognitionException {
		InicializacaoVetorContext _localctx = new InicializacaoVetorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_inicializacaoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(ABRECOLCHETE);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785544707L) != 0)) {
				{
				setState(173);
				expressao();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PONTIVIRGULA) {
					{
					{
					setState(174);
					match(PONTIVIRGULA);
					setState(175);
					expressao();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(183);
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
	public static class ValorAtribuicaoContext extends ParserRuleContext {
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public InicializacaoVetorContext inicializacaoVetor() {
			return getRuleContext(InicializacaoVetorContext.class,0);
		}
		public ValorAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valorAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterValorAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitValorAtribuicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitValorAtribuicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValorAtribuicaoContext valorAtribuicao() throws RecognitionException {
		ValorAtribuicaoContext _localctx = new ValorAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_valorAtribuicao);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VERDADEIRO:
			case FALSO:
			case MENOS:
			case NAO:
			case ABREPARENTE:
			case NUMDEC:
			case NUM:
			case CHAR:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				expressao();
				}
				break;
			case ABRECOLCHETE:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				inicializacaoVetor();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDeclaracaoFuncao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoFuncaoContext declaracaoFuncao() throws RecognitionException {
		DeclaracaoFuncaoContext _localctx = new DeclaracaoFuncaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_declaracaoFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(FUNCAO);
			setState(190);
			tipoRetorno();
			setState(191);
			match(ID);
			setState(192);
			match(ABREPARENTE);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 992L) != 0)) {
				{
				setState(193);
				parametros();
				}
			}

			setState(196);
			match(FECHAPARENTE);
			setState(197);
			match(ABRECHAVE);
			setState(198);
			bloco();
			setState(199);
			match(FECHACHAVE);
			setState(200);
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
		public List<TerminalNode> PONTO() { return getTokens(TarbaloParser.PONTO); }
		public TerminalNode PONTO(int i) {
			return getToken(TarbaloParser.PONTO, i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitParametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			parametro();
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTO) {
				{
				{
				setState(203);
				match(PONTO);
				setState(204);
				parametro();
				}
				}
				setState(209);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitParametro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_parametro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			tipoVariavel();
			setState(211);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitBloco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bloco);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(213);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(216); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public DeclaracaoContext declaracao() {
			return getRuleContext(DeclaracaoContext.class,0);
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
		public CmdParaCadaContext cmdParaCada() {
			return getRuleContext(CmdParaCadaContext.class,0);
		}
		public CmdBlocoContext cmdBloco() {
			return getRuleContext(CmdBlocoContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public RetornoContext retorno() {
			return getRuleContext(RetornoContext.class,0);
		}
		public PareContext pare() {
			return getRuleContext(PareContext.class,0);
		}
		public ContinuarContext continuar() {
			return getRuleContext(ContinuarContext.class,0);
		}
		public IncrementoPontoContext incrementoPonto() {
			return getRuleContext(IncrementoPontoContext.class,0);
		}
		public DecrementoPontoContext decrementoPonto() {
			return getRuleContext(DecrementoPontoContext.class,0);
		}
		public ChamadaFuncaoContext chamadaFuncao() {
			return getRuleContext(ChamadaFuncaoContext.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitComando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comando);
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				declaracao();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				atribuicao();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				leitura();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(221);
				escrita();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				cmdSe();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(223);
				cmdEnquanto();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(224);
				cmdFacaEnquanto();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(225);
				cmdPara();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(226);
				cmdParaCada();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(227);
				cmdBloco();
				setState(228);
				match(PONTO);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(230);
				retorno();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(231);
				pare();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(232);
				continuar();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(233);
				incrementoPonto();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(234);
				decrementoPonto();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(235);
				chamadaFuncao();
				setState(236);
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
	public static class CmdBlocoContext extends ParserRuleContext {
		public TerminalNode ABRECHAVE() { return getToken(TarbaloParser.ABRECHAVE, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode FECHACHAVE() { return getToken(TarbaloParser.FECHACHAVE, 0); }
		public CmdBlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdBloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdBloco(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdBloco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdBlocoContext cmdBloco() throws RecognitionException {
		CmdBlocoContext _localctx = new CmdBlocoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_cmdBloco);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(ABRECHAVE);
			setState(241);
			bloco();
			setState(242);
			match(FECHACHAVE);
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
	public static class DiretivaContext extends ParserRuleContext {
		public TerminalNode PACOTE() { return getToken(TarbaloParser.PACOTE, 0); }
		public TerminalNode STRING() { return getToken(TarbaloParser.STRING, 0); }
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public DiretivaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_diretiva; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDiretiva(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDiretiva(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDiretiva(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiretivaContext diretiva() throws RecognitionException {
		DiretivaContext _localctx = new DiretivaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_diretiva);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(PACOTE);
			setState(245);
			match(STRING);
			setState(246);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitLeitura(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeituraContext leitura() throws RecognitionException {
		LeituraContext _localctx = new LeituraContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_leitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(LEIA);
			setState(249);
			match(ABREPARENTE);
			setState(250);
			variavel();
			setState(251);
			match(FECHAPARENTE);
			setState(252);
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
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitEscrita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscritaContext escrita() throws RecognitionException {
		EscritaContext _localctx = new EscritaContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_escrita);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(ESCREVA);
			setState(255);
			match(ABREPARENTE);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785544707L) != 0)) {
				{
				setState(256);
				expressao();
				}
			}

			setState(259);
			match(FECHAPARENTE);
			setState(260);
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
	public static class AtribuicaoContext extends ParserRuleContext {
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public ValorAtribuicaoContext valorAtribuicao() {
			return getRuleContext(ValorAtribuicaoContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAtribuicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtribuicaoContext atribuicao() throws RecognitionException {
		AtribuicaoContext _localctx = new AtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_atribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			variavel();
			setState(265);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(263);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
				{
				setState(264);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(267);
			valorAtribuicao();
			setState(268);
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
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitIncremento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncrementoContext incremento() throws RecognitionException {
		IncrementoContext _localctx = new IncrementoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_incremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			variavel();
			setState(271);
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
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDecremento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecrementoContext decremento() throws RecognitionException {
		DecrementoContext _localctx = new DecrementoContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_decremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			variavel();
			setState(274);
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
	public static class IncrementoPontoContext extends ParserRuleContext {
		public IncrementoContext incremento() {
			return getRuleContext(IncrementoContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public IncrementoPontoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incrementoPonto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterIncrementoPonto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitIncrementoPonto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitIncrementoPonto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncrementoPontoContext incrementoPonto() throws RecognitionException {
		IncrementoPontoContext _localctx = new IncrementoPontoContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_incrementoPonto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			incremento();
			setState(277);
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
	public static class DecrementoPontoContext extends ParserRuleContext {
		public DecrementoContext decremento() {
			return getRuleContext(DecrementoContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(TarbaloParser.PONTO, 0); }
		public DecrementoPontoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decrementoPonto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDecrementoPonto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDecrementoPonto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDecrementoPonto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecrementoPontoContext decrementoPonto() throws RecognitionException {
		DecrementoPontoContext _localctx = new DecrementoPontoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_decrementoPonto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			decremento();
			setState(280);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdSe(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdSeContext cmdSe() throws RecognitionException {
		CmdSeContext _localctx = new CmdSeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(SE);
			setState(283);
			match(ABREPARENTE);
			setState(284);
			expressao();
			setState(285);
			match(FECHAPARENTE);
			setState(286);
			match(ABRECHAVE);
			setState(287);
			bloco();
			setState(288);
			match(FECHACHAVE);
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENAO) {
				{
				setState(289);
				match(SENAO);
				setState(290);
				match(ABRECHAVE);
				setState(291);
				bloco();
				setState(292);
				match(FECHACHAVE);
				}
			}

			setState(296);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdEnquanto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_cmdEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(ENQUANTO);
			setState(299);
			match(ABREPARENTE);
			setState(300);
			expressao();
			setState(301);
			match(FECHAPARENTE);
			setState(302);
			match(ABRECHAVE);
			setState(303);
			bloco();
			setState(304);
			match(FECHACHAVE);
			setState(305);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdFacaEnquanto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdFacaEnquantoContext cmdFacaEnquanto() throws RecognitionException {
		CmdFacaEnquantoContext _localctx = new CmdFacaEnquantoContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_cmdFacaEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(FACA);
			setState(308);
			match(ABRECHAVE);
			setState(309);
			bloco();
			setState(310);
			match(FECHACHAVE);
			setState(311);
			match(ENQUANTO);
			setState(312);
			match(ABREPARENTE);
			setState(313);
			expressao();
			setState(314);
			match(FECHAPARENTE);
			setState(315);
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
		public InicializacaoParaContext inicializacaoPara() {
			return getRuleContext(InicializacaoParaContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_cmdPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(PARA);
			setState(318);
			match(ABREPARENTE);
			setState(319);
			inicializacaoPara();
			setState(320);
			match(PONTO);
			setState(321);
			expressao();
			setState(322);
			match(PONTO);
			setState(323);
			atualizacaoPara();
			setState(324);
			match(FECHAPARENTE);
			setState(325);
			match(ABRECHAVE);
			setState(326);
			bloco();
			setState(327);
			match(FECHACHAVE);
			setState(328);
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
	public static class InicializacaoParaContext extends ParserRuleContext {
		public AtribuicaoParaContext atribuicaoPara() {
			return getRuleContext(AtribuicaoParaContext.class,0);
		}
		public TerminalNode VARIAVEL() { return getToken(TarbaloParser.VARIAVEL, 0); }
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public InicializacaoParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicializacaoPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterInicializacaoPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitInicializacaoPara(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitInicializacaoPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InicializacaoParaContext inicializacaoPara() throws RecognitionException {
		InicializacaoParaContext _localctx = new InicializacaoParaContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_inicializacaoPara);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				atribuicaoPara();
				}
				break;
			case VARIAVEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				match(VARIAVEL);
				setState(332);
				tipoVariavel();
				setState(333);
				match(ID);
				setState(334);
				match(ATRIBUICAO);
				setState(335);
				expressao();
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
	public static class AtribuicaoParaContext extends ParserRuleContext {
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public ValorAtribuicaoContext valorAtribuicao() {
			return getRuleContext(ValorAtribuicaoContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAtribuicaoPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtribuicaoParaContext atribuicaoPara() throws RecognitionException {
		AtribuicaoParaContext _localctx = new AtribuicaoParaContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_atribuicaoPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			variavel();
			setState(342);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(340);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
				{
				setState(341);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(344);
			valorAtribuicao();
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
		public IncrementoContext incremento() {
			return getRuleContext(IncrementoContext.class,0);
		}
		public DecrementoContext decremento() {
			return getRuleContext(DecrementoContext.class,0);
		}
		public AtribuicaoParaContext atribuicaoPara() {
			return getRuleContext(AtribuicaoParaContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAtualizacaoPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtualizacaoParaContext atualizacaoPara() throws RecognitionException {
		AtualizacaoParaContext _localctx = new AtualizacaoParaContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_atualizacaoPara);
		try {
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				incremento();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(347);
				decremento();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(348);
				atribuicaoPara();
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
	public static class CmdParaCadaContext extends ParserRuleContext {
		public TerminalNode PARACADA() { return getToken(TarbaloParser.PARACADA, 0); }
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public TerminalNode VARIAVEL() { return getToken(TarbaloParser.VARIAVEL, 0); }
		public TipoVariavelContext tipoVariavel() {
			return getRuleContext(TipoVariavelContext.class,0);
		}
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public TerminalNode EM() { return getToken(TarbaloParser.EM, 0); }
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
		public CmdParaCadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdParaCada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterCmdParaCada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitCmdParaCada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdParaCada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdParaCadaContext cmdParaCada() throws RecognitionException {
		CmdParaCadaContext _localctx = new CmdParaCadaContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_cmdParaCada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(PARACADA);
			setState(352);
			match(ABREPARENTE);
			setState(353);
			match(VARIAVEL);
			setState(354);
			tipoVariavel();
			setState(355);
			match(ID);
			setState(356);
			match(EM);
			setState(357);
			expressao();
			setState(358);
			match(FECHAPARENTE);
			setState(359);
			match(ABRECHAVE);
			setState(360);
			bloco();
			setState(361);
			match(FECHACHAVE);
			setState(362);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetornoContext retorno() throws RecognitionException {
		RetornoContext _localctx = new RetornoContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_retorno);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(RETORNE);
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785544707L) != 0)) {
				{
				setState(365);
				expressao();
				}
			}

			setState(368);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitPare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PareContext pare() throws RecognitionException {
		PareContext _localctx = new PareContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_pare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(PARE);
			setState(371);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitContinuar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinuarContext continuar() throws RecognitionException {
		ContinuarContext _localctx = new ContinuarContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_continuar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(CONTINUAR);
			setState(374);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_expressao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			expressaoXor();
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OU) {
				{
				{
				setState(377);
				match(OU);
				setState(378);
				expressaoXor();
				}
				}
				setState(383);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoXor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoXorContext expressaoXor() throws RecognitionException {
		ExpressaoXorContext _localctx = new ExpressaoXorContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_expressaoXor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			expressaoE();
			setState(389);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(385);
				match(XOR);
				setState(386);
				expressaoE();
				}
				}
				setState(391);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoEContext expressaoE() throws RecognitionException {
		ExpressaoEContext _localctx = new ExpressaoEContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressaoE);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			expressaoNegacao();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==E) {
				{
				{
				setState(393);
				match(E);
				setState(394);
				expressaoNegacao();
				}
				}
				setState(399);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoNegacao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoNegacaoContext expressaoNegacao() throws RecognitionException {
		ExpressaoNegacaoContext _localctx = new ExpressaoNegacaoContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_expressaoNegacao);
		try {
			setState(403);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAO:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				match(NAO);
				setState(401);
				expressaoNegacao();
				}
				break;
			case VERDADEIRO:
			case FALSO:
			case MENOS:
			case ABREPARENTE:
			case NUMDEC:
			case NUM:
			case CHAR:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoRelacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			expressaoAditiva();
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) {
				{
				setState(406);
				operadorRelacional();
				setState(407);
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
		public List<ExpressaoConcatenacaoContext> expressaoConcatenacao() {
			return getRuleContexts(ExpressaoConcatenacaoContext.class);
		}
		public ExpressaoConcatenacaoContext expressaoConcatenacao(int i) {
			return getRuleContext(ExpressaoConcatenacaoContext.class,i);
		}
		public List<TerminalNode> MAIS() { return getTokens(TarbaloParser.MAIS); }
		public TerminalNode MAIS(int i) {
			return getToken(TarbaloParser.MAIS, i);
		}
		public List<TerminalNode> MENOS() { return getTokens(TarbaloParser.MENOS); }
		public TerminalNode MENOS(int i) {
			return getToken(TarbaloParser.MENOS, i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoAditiva(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoAditivaContext expressaoAditiva() throws RecognitionException {
		ExpressaoAditivaContext _localctx = new ExpressaoAditivaContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_expressaoAditiva);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			expressaoConcatenacao();
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MAIS || _la==MENOS) {
				{
				{
				setState(412);
				_la = _input.LA(1);
				if ( !(_la==MAIS || _la==MENOS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(413);
				expressaoConcatenacao();
				}
				}
				setState(418);
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
	public static class ExpressaoConcatenacaoContext extends ParserRuleContext {
		public List<ExpressaoMultiplicativaContext> expressaoMultiplicativa() {
			return getRuleContexts(ExpressaoMultiplicativaContext.class);
		}
		public ExpressaoMultiplicativaContext expressaoMultiplicativa(int i) {
			return getRuleContext(ExpressaoMultiplicativaContext.class,i);
		}
		public List<TerminalNode> CONCAT() { return getTokens(TarbaloParser.CONCAT); }
		public TerminalNode CONCAT(int i) {
			return getToken(TarbaloParser.CONCAT, i);
		}
		public ExpressaoConcatenacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoConcatenacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterExpressaoConcatenacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitExpressaoConcatenacao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoConcatenacao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoConcatenacaoContext expressaoConcatenacao() throws RecognitionException {
		ExpressaoConcatenacaoContext _localctx = new ExpressaoConcatenacaoContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_expressaoConcatenacao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			expressaoMultiplicativa();
			setState(424);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONCAT) {
				{
				{
				setState(420);
				match(CONCAT);
				setState(421);
				expressaoMultiplicativa();
				}
				}
				setState(426);
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
		public List<OperandoContext> operando() {
			return getRuleContexts(OperandoContext.class);
		}
		public OperandoContext operando(int i) {
			return getRuleContext(OperandoContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoMultiplicativa(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoMultiplicativaContext expressaoMultiplicativa() throws RecognitionException {
		ExpressaoMultiplicativaContext _localctx = new ExpressaoMultiplicativaContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_expressaoMultiplicativa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			operando();
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) {
				{
				{
				setState(428);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(429);
				operando();
				}
				}
				setState(434);
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
	public static class OperandoContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(TarbaloParser.NUM, 0); }
		public TerminalNode NUMDEC() { return getToken(TarbaloParser.NUMDEC, 0); }
		public TerminalNode STRING() { return getToken(TarbaloParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(TarbaloParser.CHAR, 0); }
		public TerminalNode VERDADEIRO() { return getToken(TarbaloParser.VERDADEIRO, 0); }
		public TerminalNode FALSO() { return getToken(TarbaloParser.FALSO, 0); }
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public ChamadaFuncaoContext chamadaFuncao() {
			return getRuleContext(ChamadaFuncaoContext.class,0);
		}
		public TerminalNode ABREPARENTE() { return getToken(TarbaloParser.ABREPARENTE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHAPARENTE() { return getToken(TarbaloParser.FECHAPARENTE, 0); }
		public TerminalNode MENOS() { return getToken(TarbaloParser.MENOS, 0); }
		public OperandoContext operando() {
			return getRuleContext(OperandoContext.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitOperando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandoContext operando() throws RecognitionException {
		OperandoContext _localctx = new OperandoContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_operando);
		try {
			setState(449);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(435);
				match(NUM);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(436);
				match(NUMDEC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(437);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(438);
				match(CHAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(439);
				match(VERDADEIRO);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(440);
				match(FALSO);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(441);
				variavel();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(442);
				chamadaFuncao();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(443);
				match(ABREPARENTE);
				setState(444);
				expressao();
				setState(445);
				match(FECHAPARENTE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(447);
				match(MENOS);
				setState(448);
				operando();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitChamadaFuncao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChamadaFuncaoContext chamadaFuncao() throws RecognitionException {
		ChamadaFuncaoContext _localctx = new ChamadaFuncaoContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_chamadaFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(ID);
			setState(452);
			match(ABREPARENTE);
			setState(454);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785544707L) != 0)) {
				{
				setState(453);
				argumentos();
				}
			}

			setState(456);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitArgumentos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			expressao();
			setState(463);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTIVIRGULA) {
				{
				{
				setState(459);
				match(PONTIVIRGULA);
				setState(460);
				expressao();
				}
				}
				setState(465);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitOperadorRelacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperadorRelacionalContext operadorRelacional() throws RecognitionException {
		OperadorRelacionalContext _localctx = new OperadorRelacionalContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_operadorRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitOperadorAtribuicaoComposta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperadorAtribuicaoCompostaContext operadorAtribuicaoComposta() throws RecognitionException {
		OperadorAtribuicaoCompostaContext _localctx = new OperadorAtribuicaoCompostaContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_operadorAtribuicaoComposta);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4160749568L) != 0)) ) {
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
		"\u0004\u0001E\u01d7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0001\u0000"+
		"\u0005\u0000e\b\u0000\n\u0000\f\u0000h\t\u0000\u0001\u0000\u0004\u0000"+
		"k\b\u0000\u000b\u0000\f\u0000l\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001v\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002}\b"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003\u0083"+
		"\b\u0003\n\u0003\f\u0003\u0086\t\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u008c\b\u0005\n\u0005\f\u0005\u008f\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0093\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u0099\b\u0007\u0003\u0007\u009b\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0004\b\u00a3"+
		"\b\b\u000b\b\f\b\u00a4\u0001\b\u0001\b\u0003\b\u00a9\b\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00b1\b\t\n\t\f\t\u00b4\t\t"+
		"\u0003\t\u00b6\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0003\n\u00bc\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00c3"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00ce\b\f\n\f\f\f\u00d1\t\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0004\u000e\u00d7\b\u000e\u000b\u000e\f"+
		"\u000e\u00d8\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00ef\b\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0102\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u010a\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u0127\b\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u0152\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0157\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u015e\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0003!\u016f"+
		"\b!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001$"+
		"\u0001$\u0001$\u0005$\u017c\b$\n$\f$\u017f\t$\u0001%\u0001%\u0001%\u0005"+
		"%\u0184\b%\n%\f%\u0187\t%\u0001&\u0001&\u0001&\u0005&\u018c\b&\n&\f&\u018f"+
		"\t&\u0001\'\u0001\'\u0001\'\u0003\'\u0194\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0003(\u019a\b(\u0001)\u0001)\u0001)\u0005)\u019f\b)\n)\f)\u01a2\t)"+
		"\u0001*\u0001*\u0001*\u0005*\u01a7\b*\n*\f*\u01aa\t*\u0001+\u0001+\u0001"+
		"+\u0005+\u01af\b+\n+\f+\u01b2\t+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0003,\u01c2"+
		"\b,\u0001-\u0001-\u0001-\u0003-\u01c7\b-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0005.\u01ce\b.\n.\f.\u01d1\t.\u0001/\u0001/\u00010\u00010\u00010\u0000"+
		"\u00001\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`\u0000\u0005\u0001"+
		"\u0000\u0005\t\u0001\u0000#$\u0001\u0000%(\u0001\u0000+0\u0001\u0000\u001b"+
		"\u001f\u01e1\u0000b\u0001\u0000\u0000\u0000\u0002u\u0001\u0000\u0000\u0000"+
		"\u0004w\u0001\u0000\u0000\u0000\u0006\u0080\u0001\u0000\u0000\u0000\b"+
		"\u0087\u0001\u0000\u0000\u0000\n\u0089\u0001\u0000\u0000\u0000\f\u0092"+
		"\u0001\u0000\u0000\u0000\u000e\u0094\u0001\u0000\u0000\u0000\u0010\u009e"+
		"\u0001\u0000\u0000\u0000\u0012\u00ac\u0001\u0000\u0000\u0000\u0014\u00bb"+
		"\u0001\u0000\u0000\u0000\u0016\u00bd\u0001\u0000\u0000\u0000\u0018\u00ca"+
		"\u0001\u0000\u0000\u0000\u001a\u00d2\u0001\u0000\u0000\u0000\u001c\u00d6"+
		"\u0001\u0000\u0000\u0000\u001e\u00ee\u0001\u0000\u0000\u0000 \u00f0\u0001"+
		"\u0000\u0000\u0000\"\u00f4\u0001\u0000\u0000\u0000$\u00f8\u0001\u0000"+
		"\u0000\u0000&\u00fe\u0001\u0000\u0000\u0000(\u0106\u0001\u0000\u0000\u0000"+
		"*\u010e\u0001\u0000\u0000\u0000,\u0111\u0001\u0000\u0000\u0000.\u0114"+
		"\u0001\u0000\u0000\u00000\u0117\u0001\u0000\u0000\u00002\u011a\u0001\u0000"+
		"\u0000\u00004\u012a\u0001\u0000\u0000\u00006\u0133\u0001\u0000\u0000\u0000"+
		"8\u013d\u0001\u0000\u0000\u0000:\u0151\u0001\u0000\u0000\u0000<\u0153"+
		"\u0001\u0000\u0000\u0000>\u015d\u0001\u0000\u0000\u0000@\u015f\u0001\u0000"+
		"\u0000\u0000B\u016c\u0001\u0000\u0000\u0000D\u0172\u0001\u0000\u0000\u0000"+
		"F\u0175\u0001\u0000\u0000\u0000H\u0178\u0001\u0000\u0000\u0000J\u0180"+
		"\u0001\u0000\u0000\u0000L\u0188\u0001\u0000\u0000\u0000N\u0193\u0001\u0000"+
		"\u0000\u0000P\u0195\u0001\u0000\u0000\u0000R\u019b\u0001\u0000\u0000\u0000"+
		"T\u01a3\u0001\u0000\u0000\u0000V\u01ab\u0001\u0000\u0000\u0000X\u01c1"+
		"\u0001\u0000\u0000\u0000Z\u01c3\u0001\u0000\u0000\u0000\\\u01ca\u0001"+
		"\u0000\u0000\u0000^\u01d2\u0001\u0000\u0000\u0000`\u01d4\u0001\u0000\u0000"+
		"\u0000bf\u0005\u0001\u0000\u0000ce\u0003\"\u0011\u0000dc\u0001\u0000\u0000"+
		"\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ik\u0003"+
		"\u001c\u000e\u0000ji\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000"+
		"\u0000no\u0005\u0002\u0000\u0000op\u0005=\u0000\u0000pq\u0005\u0000\u0000"+
		"\u0001q\u0001\u0001\u0000\u0000\u0000rv\u0003\u0004\u0002\u0000sv\u0003"+
		"\u0010\b\u0000tv\u0003\u0016\u000b\u0000ur\u0001\u0000\u0000\u0000us\u0001"+
		"\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000v\u0003\u0001\u0000\u0000"+
		"\u0000wx\u0005\u0003\u0000\u0000xy\u0003\b\u0004\u0000y|\u0005B\u0000"+
		"\u0000z{\u0005*\u0000\u0000{}\u0003H$\u0000|z\u0001\u0000\u0000\u0000"+
		"|}\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f\u0005=\u0000"+
		"\u0000\u007f\u0005\u0001\u0000\u0000\u0000\u0080\u0084\u0005B\u0000\u0000"+
		"\u0081\u0083\u0003\u000e\u0007\u0000\u0082\u0081\u0001\u0000\u0000\u0000"+
		"\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0007\u0001\u0000\u0000\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u0088\u0007\u0000\u0000\u0000"+
		"\u0088\t\u0001\u0000\u0000\u0000\u0089\u008d\u0003\b\u0004\u0000\u008a"+
		"\u008c\u0003\u000e\u0007\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008f\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e\u000b\u0001\u0000\u0000\u0000\u008f"+
		"\u008d\u0001\u0000\u0000\u0000\u0090\u0093\u0003\n\u0005\u0000\u0091\u0093"+
		"\u0005\n\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0091\u0001"+
		"\u0000\u0000\u0000\u0093\r\u0001\u0000\u0000\u0000\u0094\u009a\u00059"+
		"\u0000\u0000\u0095\u0098\u0003H$\u0000\u0096\u0097\u0005\"\u0000\u0000"+
		"\u0097\u0099\u0003H$\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099"+
		"\u0001\u0000\u0000\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0095"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0005:\u0000\u0000\u009d\u000f\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u0005\u0004\u0000\u0000\u009f\u00a0\u0003"+
		"\b\u0004\u0000\u00a0\u00a2\u0005B\u0000\u0000\u00a1\u00a3\u0003\u000e"+
		"\u0007\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a8\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005*\u0000"+
		"\u0000\u00a7\u00a9\u0003\u0014\n\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000"+
		"\u00aa\u00ab\u0005=\u0000\u0000\u00ab\u0011\u0001\u0000\u0000\u0000\u00ac"+
		"\u00b5\u00059\u0000\u0000\u00ad\u00b2\u0003H$\u0000\u00ae\u00af\u0005"+
		"<\u0000\u0000\u00af\u00b1\u0003H$\u0000\u00b0\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00ad\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0005:\u0000\u0000\u00b8\u0013\u0001\u0000\u0000\u0000"+
		"\u00b9\u00bc\u0003H$\u0000\u00ba\u00bc\u0003\u0012\t\u0000\u00bb\u00b9"+
		"\u0001\u0000\u0000\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u0015"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0005\u000b\u0000\u0000\u00be\u00bf"+
		"\u0003\f\u0006\u0000\u00bf\u00c0\u0005B\u0000\u0000\u00c0\u00c2\u0005"+
		"5\u0000\u0000\u00c1\u00c3\u0003\u0018\f\u0000\u00c2\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c5\u00056\u0000\u0000\u00c5\u00c6\u00057\u0000\u0000"+
		"\u00c6\u00c7\u0003\u001c\u000e\u0000\u00c7\u00c8\u00058\u0000\u0000\u00c8"+
		"\u00c9\u0005=\u0000\u0000\u00c9\u0017\u0001\u0000\u0000\u0000\u00ca\u00cf"+
		"\u0003\u001a\r\u0000\u00cb\u00cc\u0005=\u0000\u0000\u00cc\u00ce\u0003"+
		"\u001a\r\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00d1\u0001\u0000"+
		"\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d0\u0019\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0003\b\u0004\u0000\u00d3\u00d4\u0003\u0006\u0003"+
		"\u0000\u00d4\u001b\u0001\u0000\u0000\u0000\u00d5\u00d7\u0003\u001e\u000f"+
		"\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000"+
		"\u0000\u00d9\u001d\u0001\u0000\u0000\u0000\u00da\u00ef\u0003\u0002\u0001"+
		"\u0000\u00db\u00ef\u0003(\u0014\u0000\u00dc\u00ef\u0003$\u0012\u0000\u00dd"+
		"\u00ef\u0003&\u0013\u0000\u00de\u00ef\u00032\u0019\u0000\u00df\u00ef\u0003"+
		"4\u001a\u0000\u00e0\u00ef\u00036\u001b\u0000\u00e1\u00ef\u00038\u001c"+
		"\u0000\u00e2\u00ef\u0003@ \u0000\u00e3\u00e4\u0003 \u0010\u0000\u00e4"+
		"\u00e5\u0005=\u0000\u0000\u00e5\u00ef\u0001\u0000\u0000\u0000\u00e6\u00ef"+
		"\u0003B!\u0000\u00e7\u00ef\u0003D\"\u0000\u00e8\u00ef\u0003F#\u0000\u00e9"+
		"\u00ef\u0003.\u0017\u0000\u00ea\u00ef\u00030\u0018\u0000\u00eb\u00ec\u0003"+
		"Z-\u0000\u00ec\u00ed\u0005=\u0000\u0000\u00ed\u00ef\u0001\u0000\u0000"+
		"\u0000\u00ee\u00da\u0001\u0000\u0000\u0000\u00ee\u00db\u0001\u0000\u0000"+
		"\u0000\u00ee\u00dc\u0001\u0000\u0000\u0000\u00ee\u00dd\u0001\u0000\u0000"+
		"\u0000\u00ee\u00de\u0001\u0000\u0000\u0000\u00ee\u00df\u0001\u0000\u0000"+
		"\u0000\u00ee\u00e0\u0001\u0000\u0000\u0000\u00ee\u00e1\u0001\u0000\u0000"+
		"\u0000\u00ee\u00e2\u0001\u0000\u0000\u0000\u00ee\u00e3\u0001\u0000\u0000"+
		"\u0000\u00ee\u00e6\u0001\u0000\u0000\u0000\u00ee\u00e7\u0001\u0000\u0000"+
		"\u0000\u00ee\u00e8\u0001\u0000\u0000\u0000\u00ee\u00e9\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ea\u0001\u0000\u0000\u0000\u00ee\u00eb\u0001\u0000\u0000"+
		"\u0000\u00ef\u001f\u0001\u0000\u0000\u0000\u00f0\u00f1\u00057\u0000\u0000"+
		"\u00f1\u00f2\u0003\u001c\u000e\u0000\u00f2\u00f3\u00058\u0000\u0000\u00f3"+
		"!\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005\u001a\u0000\u0000\u00f5\u00f6"+
		"\u0005A\u0000\u0000\u00f6\u00f7\u0005=\u0000\u0000\u00f7#\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\u0005\r\u0000\u0000\u00f9\u00fa\u00055\u0000"+
		"\u0000\u00fa\u00fb\u0003\u0006\u0003\u0000\u00fb\u00fc\u00056\u0000\u0000"+
		"\u00fc\u00fd\u0005=\u0000\u0000\u00fd%\u0001\u0000\u0000\u0000\u00fe\u00ff"+
		"\u0005\u000e\u0000\u0000\u00ff\u0101\u00055\u0000\u0000\u0100\u0102\u0003"+
		"H$\u0000\u0101\u0100\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000"+
		"\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0104\u00056\u0000\u0000"+
		"\u0104\u0105\u0005=\u0000\u0000\u0105\'\u0001\u0000\u0000\u0000\u0106"+
		"\u0109\u0003\u0006\u0003\u0000\u0107\u010a\u0005*\u0000\u0000\u0108\u010a"+
		"\u0003`0\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u0109\u0108\u0001\u0000"+
		"\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\u010c\u0003\u0014"+
		"\n\u0000\u010c\u010d\u0005=\u0000\u0000\u010d)\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0003\u0006\u0003\u0000\u010f\u0110\u0005 \u0000\u0000\u0110"+
		"+\u0001\u0000\u0000\u0000\u0111\u0112\u0003\u0006\u0003\u0000\u0112\u0113"+
		"\u0005!\u0000\u0000\u0113-\u0001\u0000\u0000\u0000\u0114\u0115\u0003*"+
		"\u0015\u0000\u0115\u0116\u0005=\u0000\u0000\u0116/\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0003,\u0016\u0000\u0118\u0119\u0005=\u0000\u0000\u01191"+
		"\u0001\u0000\u0000\u0000\u011a\u011b\u0005\u000f\u0000\u0000\u011b\u011c"+
		"\u00055\u0000\u0000\u011c\u011d\u0003H$\u0000\u011d\u011e\u00056\u0000"+
		"\u0000\u011e\u011f\u00057\u0000\u0000\u011f\u0120\u0003\u001c\u000e\u0000"+
		"\u0120\u0126\u00058\u0000\u0000\u0121\u0122\u0005\u0010\u0000\u0000\u0122"+
		"\u0123\u00057\u0000\u0000\u0123\u0124\u0003\u001c\u000e\u0000\u0124\u0125"+
		"\u00058\u0000\u0000\u0125\u0127\u0001\u0000\u0000\u0000\u0126\u0121\u0001"+
		"\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0128\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0005=\u0000\u0000\u01293\u0001\u0000\u0000"+
		"\u0000\u012a\u012b\u0005\u0011\u0000\u0000\u012b\u012c\u00055\u0000\u0000"+
		"\u012c\u012d\u0003H$\u0000\u012d\u012e\u00056\u0000\u0000\u012e\u012f"+
		"\u00057\u0000\u0000\u012f\u0130\u0003\u001c\u000e\u0000\u0130\u0131\u0005"+
		"8\u0000\u0000\u0131\u0132\u0005=\u0000\u0000\u01325\u0001\u0000\u0000"+
		"\u0000\u0133\u0134\u0005\u0012\u0000\u0000\u0134\u0135\u00057\u0000\u0000"+
		"\u0135\u0136\u0003\u001c\u000e\u0000\u0136\u0137\u00058\u0000\u0000\u0137"+
		"\u0138\u0005\u0011\u0000\u0000\u0138\u0139\u00055\u0000\u0000\u0139\u013a"+
		"\u0003H$\u0000\u013a\u013b\u00056\u0000\u0000\u013b\u013c\u0005=\u0000"+
		"\u0000\u013c7\u0001\u0000\u0000\u0000\u013d\u013e\u0005\u0013\u0000\u0000"+
		"\u013e\u013f\u00055\u0000\u0000\u013f\u0140\u0003:\u001d\u0000\u0140\u0141"+
		"\u0005=\u0000\u0000\u0141\u0142\u0003H$\u0000\u0142\u0143\u0005=\u0000"+
		"\u0000\u0143\u0144\u0003>\u001f\u0000\u0144\u0145\u00056\u0000\u0000\u0145"+
		"\u0146\u00057\u0000\u0000\u0146\u0147\u0003\u001c\u000e\u0000\u0147\u0148"+
		"\u00058\u0000\u0000\u0148\u0149\u0005=\u0000\u0000\u01499\u0001\u0000"+
		"\u0000\u0000\u014a\u0152\u0003<\u001e\u0000\u014b\u014c\u0005\u0003\u0000"+
		"\u0000\u014c\u014d\u0003\b\u0004\u0000\u014d\u014e\u0005B\u0000\u0000"+
		"\u014e\u014f\u0005*\u0000\u0000\u014f\u0150\u0003H$\u0000\u0150\u0152"+
		"\u0001\u0000\u0000\u0000\u0151\u014a\u0001\u0000\u0000\u0000\u0151\u014b"+
		"\u0001\u0000\u0000\u0000\u0152;\u0001\u0000\u0000\u0000\u0153\u0156\u0003"+
		"\u0006\u0003\u0000\u0154\u0157\u0005*\u0000\u0000\u0155\u0157\u0003`0"+
		"\u0000\u0156\u0154\u0001\u0000\u0000\u0000\u0156\u0155\u0001\u0000\u0000"+
		"\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u0159\u0003\u0014\n\u0000"+
		"\u0159=\u0001\u0000\u0000\u0000\u015a\u015e\u0003*\u0015\u0000\u015b\u015e"+
		"\u0003,\u0016\u0000\u015c\u015e\u0003<\u001e\u0000\u015d\u015a\u0001\u0000"+
		"\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015c\u0001\u0000"+
		"\u0000\u0000\u015e?\u0001\u0000\u0000\u0000\u015f\u0160\u0005\u0014\u0000"+
		"\u0000\u0160\u0161\u00055\u0000\u0000\u0161\u0162\u0005\u0003\u0000\u0000"+
		"\u0162\u0163\u0003\b\u0004\u0000\u0163\u0164\u0005B\u0000\u0000\u0164"+
		"\u0165\u0005\u0015\u0000\u0000\u0165\u0166\u0003H$\u0000\u0166\u0167\u0005"+
		"6\u0000\u0000\u0167\u0168\u00057\u0000\u0000\u0168\u0169\u0003\u001c\u000e"+
		"\u0000\u0169\u016a\u00058\u0000\u0000\u016a\u016b\u0005=\u0000\u0000\u016b"+
		"A\u0001\u0000\u0000\u0000\u016c\u016e\u0005\f\u0000\u0000\u016d\u016f"+
		"\u0003H$\u0000\u016e\u016d\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000"+
		"\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0171\u0005=\u0000"+
		"\u0000\u0171C\u0001\u0000\u0000\u0000\u0172\u0173\u0005\u0016\u0000\u0000"+
		"\u0173\u0174\u0005=\u0000\u0000\u0174E\u0001\u0000\u0000\u0000\u0175\u0176"+
		"\u0005\u0017\u0000\u0000\u0176\u0177\u0005=\u0000\u0000\u0177G\u0001\u0000"+
		"\u0000\u0000\u0178\u017d\u0003J%\u0000\u0179\u017a\u00053\u0000\u0000"+
		"\u017a\u017c\u0003J%\u0000\u017b\u0179\u0001\u0000\u0000\u0000\u017c\u017f"+
		"\u0001\u0000\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017d\u017e"+
		"\u0001\u0000\u0000\u0000\u017eI\u0001\u0000\u0000\u0000\u017f\u017d\u0001"+
		"\u0000\u0000\u0000\u0180\u0185\u0003L&\u0000\u0181\u0182\u00054\u0000"+
		"\u0000\u0182\u0184\u0003L&\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0184"+
		"\u0187\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0185"+
		"\u0186\u0001\u0000\u0000\u0000\u0186K\u0001\u0000\u0000\u0000\u0187\u0185"+
		"\u0001\u0000\u0000\u0000\u0188\u018d\u0003N\'\u0000\u0189\u018a\u0005"+
		"2\u0000\u0000\u018a\u018c\u0003N\'\u0000\u018b\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000"+
		"\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018eM\u0001\u0000\u0000\u0000"+
		"\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u00051\u0000\u0000\u0191"+
		"\u0194\u0003N\'\u0000\u0192\u0194\u0003P(\u0000\u0193\u0190\u0001\u0000"+
		"\u0000\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0194O\u0001\u0000\u0000"+
		"\u0000\u0195\u0199\u0003R)\u0000\u0196\u0197\u0003^/\u0000\u0197\u0198"+
		"\u0003R)\u0000\u0198\u019a\u0001\u0000\u0000\u0000\u0199\u0196\u0001\u0000"+
		"\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019aQ\u0001\u0000\u0000"+
		"\u0000\u019b\u01a0\u0003T*\u0000\u019c\u019d\u0007\u0001\u0000\u0000\u019d"+
		"\u019f\u0003T*\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f\u01a2\u0001"+
		"\u0000\u0000\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a1S\u0001\u0000\u0000\u0000\u01a2\u01a0\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a8\u0003V+\u0000\u01a4\u01a5\u0005)\u0000\u0000"+
		"\u01a5\u01a7\u0003V+\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a7\u01aa"+
		"\u0001\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a9U\u0001\u0000\u0000\u0000\u01aa\u01a8\u0001"+
		"\u0000\u0000\u0000\u01ab\u01b0\u0003X,\u0000\u01ac\u01ad\u0007\u0002\u0000"+
		"\u0000\u01ad\u01af\u0003X,\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af"+
		"\u01b2\u0001\u0000\u0000\u0000\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b0"+
		"\u01b1\u0001\u0000\u0000\u0000\u01b1W\u0001\u0000\u0000\u0000\u01b2\u01b0"+
		"\u0001\u0000\u0000\u0000\u01b3\u01c2\u0005?\u0000\u0000\u01b4\u01c2\u0005"+
		">\u0000\u0000\u01b5\u01c2\u0005A\u0000\u0000\u01b6\u01c2\u0005@\u0000"+
		"\u0000\u01b7\u01c2\u0005\u0018\u0000\u0000\u01b8\u01c2\u0005\u0019\u0000"+
		"\u0000\u01b9\u01c2\u0003\u0006\u0003\u0000\u01ba\u01c2\u0003Z-\u0000\u01bb"+
		"\u01bc\u00055\u0000\u0000\u01bc\u01bd\u0003H$\u0000\u01bd\u01be\u0005"+
		"6\u0000\u0000\u01be\u01c2\u0001\u0000\u0000\u0000\u01bf\u01c0\u0005$\u0000"+
		"\u0000\u01c0\u01c2\u0003X,\u0000\u01c1\u01b3\u0001\u0000\u0000\u0000\u01c1"+
		"\u01b4\u0001\u0000\u0000\u0000\u01c1\u01b5\u0001\u0000\u0000\u0000\u01c1"+
		"\u01b6\u0001\u0000\u0000\u0000\u01c1\u01b7\u0001\u0000\u0000\u0000\u01c1"+
		"\u01b8\u0001\u0000\u0000\u0000\u01c1\u01b9\u0001\u0000\u0000\u0000\u01c1"+
		"\u01ba\u0001\u0000\u0000\u0000\u01c1\u01bb\u0001\u0000\u0000\u0000\u01c1"+
		"\u01bf\u0001\u0000\u0000\u0000\u01c2Y\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0005B\u0000\u0000\u01c4\u01c6\u00055\u0000\u0000\u01c5\u01c7\u0003\\"+
		".\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000"+
		"\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u00056\u0000\u0000"+
		"\u01c9[\u0001\u0000\u0000\u0000\u01ca\u01cf\u0003H$\u0000\u01cb\u01cc"+
		"\u0005<\u0000\u0000\u01cc\u01ce\u0003H$\u0000\u01cd\u01cb\u0001\u0000"+
		"\u0000\u0000\u01ce\u01d1\u0001\u0000\u0000\u0000\u01cf\u01cd\u0001\u0000"+
		"\u0000\u0000\u01cf\u01d0\u0001\u0000\u0000\u0000\u01d0]\u0001\u0000\u0000"+
		"\u0000\u01d1\u01cf\u0001\u0000\u0000\u0000\u01d2\u01d3\u0007\u0003\u0000"+
		"\u0000\u01d3_\u0001\u0000\u0000\u0000\u01d4\u01d5\u0007\u0004\u0000\u0000"+
		"\u01d5a\u0001\u0000\u0000\u0000$flu|\u0084\u008d\u0092\u0098\u009a\u00a4"+
		"\u00a8\u00b2\u00b5\u00bb\u00c2\u00cf\u00d8\u00ee\u0101\u0109\u0126\u0151"+
		"\u0156\u015d\u016e\u017d\u0185\u018d\u0193\u0199\u01a0\u01a8\u01b0\u01c1"+
		"\u01c6\u01cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}