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
		EM=21, PARE=22, CONTINUAR=23, VERDADEIRO=24, FALSO=25, SOMA_ATRIBUICAO=26, 
		SUBTRACAO_ATRIBUICAO=27, MULT_ATRIBUICAO=28, DIV_ATRIBUICAO=29, MOD_ATRIBUICAO=30, 
		CONCAT_ATRIBUICAO=31, INCREMENTO=32, DECREMENTO=33, PONTOPONTO=34, MAIS=35, 
		MENOS=36, MULT=37, DIVINT=38, DIV=39, MOD=40, CONCAT=41, ATRIBUICAO=42, 
		MENOR=43, MAIOR=44, MENORIGUAL=45, MAIORIGUAL=46, IGUAL=47, DIFERENTE=48, 
		NAO=49, E=50, OU=51, XOR=52, ABREPARENTE=53, FECHAPARENTE=54, ABRECHAVE=55, 
		FECHACHAVE=56, ABRECOLCHETE=57, FECHACOLCHETE=58, VIRGULA=59, PONTIVIRGULA=60, 
		PONTO=61, NUMDEC=62, NUM=63, CHAR=64, STRING=65, ID=66, COMENTARIO_LINHA=67, 
		COMENTARIO_BLOCO=68, WS=69;
	public static final int
		RULE_programa = 0, RULE_declaracao = 1, RULE_declaracaoVariavel = 2, RULE_variavel = 3, 
		RULE_tipoVariavel = 4, RULE_tipoRetorno = 5, RULE_dimensaoVetor = 6, RULE_declaracaoVetor = 7, 
		RULE_inicializacaoVetor = 8, RULE_declaracaoFuncao = 9, RULE_parametros = 10, 
		RULE_parametro = 11, RULE_bloco = 12, RULE_comando = 13, RULE_leitura = 14, 
		RULE_escrita = 15, RULE_selecaoVariavel = 16, RULE_atribuicao = 17, RULE_incremento = 18, 
		RULE_decremento = 19, RULE_incrementoPonto = 20, RULE_decrementoPonto = 21, 
		RULE_cmdSe = 22, RULE_cmdEnquanto = 23, RULE_cmdFacaEnquanto = 24, RULE_cmdPara = 25, 
		RULE_atribuicaoPara = 26, RULE_atualizacaoPara = 27, RULE_cmdParaCada = 28, 
		RULE_retorno = 29, RULE_pare = 30, RULE_continuar = 31, RULE_expressao = 32, 
		RULE_expressaoXor = 33, RULE_expressaoE = 34, RULE_expressaoNegacao = 35, 
		RULE_expressaoRelacional = 36, RULE_expressaoAditiva = 37, RULE_expressaoMultiplicativa = 38, 
		RULE_expressaoUnaria = 39, RULE_operando = 40, RULE_acessoVetor = 41, 
		RULE_acessoDimensao = 42, RULE_chamadaFuncao = 43, RULE_argumentos = 44, 
		RULE_operadorRelacional = 45, RULE_operadorAtribuicaoComposta = 46;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracao", "declaracaoVariavel", "variavel", "tipoVariavel", 
			"tipoRetorno", "dimensaoVetor", "declaracaoVetor", "inicializacaoVetor", 
			"declaracaoFuncao", "parametros", "parametro", "bloco", "comando", "leitura", 
			"escrita", "selecaoVariavel", "atribuicao", "incremento", "decremento", 
			"incrementoPonto", "decrementoPonto", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
			"cmdPara", "atribuicaoPara", "atualizacaoPara", "cmdParaCada", "retorno", 
			"pare", "continuar", "expressao", "expressaoXor", "expressaoE", "expressaoNegacao", 
			"expressaoRelacional", "expressaoAditiva", "expressaoMultiplicativa", 
			"expressaoUnaria", "operando", "acessoVetor", "acessoDimensao", "chamadaFuncao", 
			"argumentos", "operadorRelacional", "operadorAtribuicaoComposta"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'prog'", "'fimprog'", "'var'", "'vtr'", "'int'", "'dec'", "'car'", 
			"'texto'", "'logico'", "'vazio'", "'func'", "'retorne'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'faca'", "'para'", "'pancada'", "'em'", 
			"'pare'", "'continuar'", "'VDD'", "'FAKE'", "'+:'", "'-:'", "'*:'", "'/:'", 
			"'%:'", "'&:'", "'++'", "'--'", "'..'", "'+'", "'-'", "'*'", "'//'", 
			"'/'", "'%'", "'&'", "':'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", 
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
			"PARE", "CONTINUAR", "VERDADEIRO", "FALSO", "SOMA_ATRIBUICAO", "SUBTRACAO_ATRIBUICAO", 
			"MULT_ATRIBUICAO", "DIV_ATRIBUICAO", "MOD_ATRIBUICAO", "CONCAT_ATRIBUICAO", 
			"INCREMENTO", "DECREMENTO", "PONTOPONTO", "MAIS", "MENOS", "MULT", "DIVINT", 
			"DIV", "MOD", "CONCAT", "ATRIBUICAO", "MENOR", "MAIOR", "MENORIGUAL", 
			"MAIORIGUAL", "IGUAL", "DIFERENTE", "NAO", "E", "OU", "XOR", "ABREPARENTE", 
			"FECHAPARENTE", "ABRECHAVE", "FECHACHAVE", "ABRECOLCHETE", "FECHACOLCHETE", 
			"VIRGULA", "PONTIVIRGULA", "PONTO", "NUMDEC", "NUM", "CHAR", "STRING", 
			"ID", "COMENTARIO_LINHA", "COMENTARIO_BLOCO", "WS"
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
			setState(94);
			match(INICIO);
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(95);
				bloco();
				}
				}
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 3)) & ~0x3f) == 0 && ((1L << (_la - 3)) & -9223372036852949245L) != 0) );
			setState(100);
			match(FIM);
			setState(101);
			match(PONTO);
			setState(102);
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
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIAVEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				declaracaoVariavel();
				}
				break;
			case VETOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				declaracaoVetor();
				}
				break;
			case FUNCAO:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
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
			setState(109);
			match(VARIAVEL);
			setState(110);
			tipoVariavel();
			setState(111);
			match(ID);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATRIBUICAO) {
				{
				setState(112);
				match(ATRIBUICAO);
				setState(113);
				expressao();
				}
			}

			setState(116);
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
		public List<DimensaoVetorContext> dimensaoVetor() {
			return getRuleContexts(DimensaoVetorContext.class);
		}
		public DimensaoVetorContext dimensaoVetor(int i) {
			return getRuleContext(DimensaoVetorContext.class,i);
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
			setState(118);
			match(ID);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABRECOLCHETE) {
				{
				{
				setState(119);
				dimensaoVetor();
				}
				}
				setState(124);
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
			setState(125);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitTipoRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoRetornoContext tipoRetorno() throws RecognitionException {
		TipoRetornoContext _localctx = new TipoRetornoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipoRetorno);
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEIRO:
			case DECIMAL:
			case CARACTERE:
			case TEXTO_TIPO:
			case BOOLEANO_TIPO:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				tipoVariavel();
				}
				break;
			case VAZIO:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
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
	public static class DimensaoVetorContext extends ParserRuleContext {
		public TerminalNode ABRECOLCHETE() { return getToken(TarbaloParser.ABRECOLCHETE, 0); }
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
		public TerminalNode NUM() { return getToken(TarbaloParser.NUM, 0); }
		public DimensaoVetorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensaoVetor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterDimensaoVetor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitDimensaoVetor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDimensaoVetor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensaoVetorContext dimensaoVetor() throws RecognitionException {
		DimensaoVetorContext _localctx = new DimensaoVetorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dimensaoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(ABRECOLCHETE);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUM) {
				{
				setState(132);
				match(NUM);
				}
			}

			setState(135);
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
		public List<DimensaoVetorContext> dimensaoVetor() {
			return getRuleContexts(DimensaoVetorContext.class);
		}
		public DimensaoVetorContext dimensaoVetor(int i) {
			return getRuleContext(DimensaoVetorContext.class,i);
		}
		public TerminalNode ATRIBUICAO() { return getToken(TarbaloParser.ATRIBUICAO, 0); }
		public InicializacaoVetorContext inicializacaoVetor() {
			return getRuleContext(InicializacaoVetorContext.class,0);
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
		enterRule(_localctx, 14, RULE_declaracaoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(VETOR);
			setState(138);
			tipoVariavel();
			setState(139);
			match(ID);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				dimensaoVetor();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ABRECOLCHETE );
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATRIBUICAO) {
				{
				setState(145);
				match(ATRIBUICAO);
				setState(146);
				inicializacaoVetor();
				}
			}

			setState(149);
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
		enterRule(_localctx, 16, RULE_inicializacaoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(ABRECOLCHETE);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785546755L) != 0)) {
				{
				setState(152);
				expressao();
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PONTIVIRGULA) {
					{
					{
					setState(153);
					match(PONTIVIRGULA);
					setState(154);
					expressao();
					}
					}
					setState(159);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(162);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitDeclaracaoFuncao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoFuncaoContext declaracaoFuncao() throws RecognitionException {
		DeclaracaoFuncaoContext _localctx = new DeclaracaoFuncaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declaracaoFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(FUNCAO);
			setState(165);
			tipoRetorno();
			setState(166);
			match(ID);
			setState(167);
			match(ABREPARENTE);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 992L) != 0)) {
				{
				setState(168);
				parametros();
				}
			}

			setState(171);
			match(FECHAPARENTE);
			setState(172);
			match(ABRECHAVE);
			setState(173);
			bloco();
			setState(174);
			match(FECHACHAVE);
			setState(175);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitParametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			parametro();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTIVIRGULA) {
				{
				{
				setState(178);
				match(PONTIVIRGULA);
				setState(179);
				parametro();
				}
				}
				setState(184);
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
		enterRule(_localctx, 22, RULE_parametro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			tipoVariavel();
			setState(186);
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
		enterRule(_localctx, 24, RULE_bloco);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(188);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(191); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitComando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comando);
		try {
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				declaracao();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				atribuicao();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				leitura();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				escrita();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				cmdSe();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(198);
				cmdEnquanto();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(199);
				cmdFacaEnquanto();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(200);
				cmdPara();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(201);
				cmdParaCada();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(202);
				retorno();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(203);
				pare();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(204);
				continuar();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(205);
				incrementoPonto();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(206);
				decrementoPonto();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(207);
				chamadaFuncao();
				setState(208);
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
		public SelecaoVariavelContext selecaoVariavel() {
			return getRuleContext(SelecaoVariavelContext.class,0);
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
		enterRule(_localctx, 28, RULE_leitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(LEIA);
			setState(213);
			match(ABREPARENTE);
			setState(214);
			selecaoVariavel();
			setState(215);
			match(FECHAPARENTE);
			setState(216);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitEscrita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscritaContext escrita() throws RecognitionException {
		EscritaContext _localctx = new EscritaContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_escrita);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(ESCREVA);
			setState(219);
			match(ABREPARENTE);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785546755L) != 0)) {
				{
				setState(220);
				expressao();
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PONTIVIRGULA) {
					{
					{
					setState(221);
					match(PONTIVIRGULA);
					setState(222);
					expressao();
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(230);
			match(FECHAPARENTE);
			setState(231);
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
	public static class SelecaoVariavelContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TarbaloParser.ID, 0); }
		public AcessoVetorContext acessoVetor() {
			return getRuleContext(AcessoVetorContext.class,0);
		}
		public SelecaoVariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selecaoVariavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).enterSelecaoVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TarbaloListener ) ((TarbaloListener)listener).exitSelecaoVariavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitSelecaoVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelecaoVariavelContext selecaoVariavel() throws RecognitionException {
		SelecaoVariavelContext _localctx = new SelecaoVariavelContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_selecaoVariavel);
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
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
		public SelecaoVariavelContext selecaoVariavel() {
			return getRuleContext(SelecaoVariavelContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAtribuicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtribuicaoContext atribuicao() throws RecognitionException {
		AtribuicaoContext _localctx = new AtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_atribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			selecaoVariavel();
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(238);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
			case CONCAT_ATRIBUICAO:
				{
				setState(239);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(242);
			expressao();
			setState(243);
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
		public SelecaoVariavelContext selecaoVariavel() {
			return getRuleContext(SelecaoVariavelContext.class,0);
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
		enterRule(_localctx, 36, RULE_incremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			selecaoVariavel();
			setState(246);
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
		public SelecaoVariavelContext selecaoVariavel() {
			return getRuleContext(SelecaoVariavelContext.class,0);
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
		enterRule(_localctx, 38, RULE_decremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			selecaoVariavel();
			setState(249);
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
		enterRule(_localctx, 40, RULE_incrementoPonto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			incremento();
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
		enterRule(_localctx, 42, RULE_decrementoPonto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			decremento();
			setState(255);
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
		enterRule(_localctx, 44, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(SE);
			setState(258);
			match(ABREPARENTE);
			setState(259);
			expressao();
			setState(260);
			match(FECHAPARENTE);
			setState(261);
			match(ABRECHAVE);
			setState(262);
			bloco();
			setState(263);
			match(FECHACHAVE);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENAO) {
				{
				setState(264);
				match(SENAO);
				setState(265);
				match(ABRECHAVE);
				setState(266);
				bloco();
				setState(267);
				match(FECHACHAVE);
				}
			}

			setState(271);
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
		enterRule(_localctx, 46, RULE_cmdEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(ENQUANTO);
			setState(274);
			match(ABREPARENTE);
			setState(275);
			expressao();
			setState(276);
			match(FECHAPARENTE);
			setState(277);
			match(ABRECHAVE);
			setState(278);
			bloco();
			setState(279);
			match(FECHACHAVE);
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
		enterRule(_localctx, 48, RULE_cmdFacaEnquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(FACA);
			setState(283);
			match(ABRECHAVE);
			setState(284);
			bloco();
			setState(285);
			match(FECHACHAVE);
			setState(286);
			match(ENQUANTO);
			setState(287);
			match(ABREPARENTE);
			setState(288);
			expressao();
			setState(289);
			match(FECHAPARENTE);
			setState(290);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitCmdPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cmdPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(PARA);
			setState(293);
			match(ABREPARENTE);
			setState(294);
			atribuicaoPara();
			setState(295);
			match(PONTO);
			setState(296);
			expressao();
			setState(297);
			match(PONTO);
			setState(298);
			atualizacaoPara();
			setState(299);
			match(FECHAPARENTE);
			setState(300);
			match(ABRECHAVE);
			setState(301);
			bloco();
			setState(302);
			match(FECHACHAVE);
			setState(303);
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
		public SelecaoVariavelContext selecaoVariavel() {
			return getRuleContext(SelecaoVariavelContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAtribuicaoPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtribuicaoParaContext atribuicaoPara() throws RecognitionException {
		AtribuicaoParaContext _localctx = new AtribuicaoParaContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_atribuicaoPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			selecaoVariavel();
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATRIBUICAO:
				{
				setState(306);
				match(ATRIBUICAO);
				}
				break;
			case SOMA_ATRIBUICAO:
			case SUBTRACAO_ATRIBUICAO:
			case MULT_ATRIBUICAO:
			case DIV_ATRIBUICAO:
			case MOD_ATRIBUICAO:
			case CONCAT_ATRIBUICAO:
				{
				setState(307);
				operadorAtribuicaoComposta();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(310);
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
		enterRule(_localctx, 54, RULE_atualizacaoPara);
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				incremento();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				decremento();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
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
		enterRule(_localctx, 56, RULE_cmdParaCada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(PARACADA);
			setState(318);
			match(ABREPARENTE);
			setState(319);
			match(VARIAVEL);
			setState(320);
			tipoVariavel();
			setState(321);
			match(ID);
			setState(322);
			match(EM);
			setState(323);
			expressao();
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
		enterRule(_localctx, 58, RULE_retorno);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(RETORNE);
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785546755L) != 0)) {
				{
				setState(331);
				expressao();
				}
			}

			setState(334);
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
		enterRule(_localctx, 60, RULE_pare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(PARE);
			setState(337);
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
		enterRule(_localctx, 62, RULE_continuar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(CONTINUAR);
			setState(340);
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
		enterRule(_localctx, 64, RULE_expressao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			expressaoXor();
			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OU) {
				{
				{
				setState(343);
				match(OU);
				setState(344);
				expressaoXor();
				}
				}
				setState(349);
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
		enterRule(_localctx, 66, RULE_expressaoXor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			expressaoE();
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(351);
				match(XOR);
				setState(352);
				expressaoE();
				}
				}
				setState(357);
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
		enterRule(_localctx, 68, RULE_expressaoE);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			expressaoNegacao();
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==E) {
				{
				{
				setState(359);
				match(E);
				setState(360);
				expressaoNegacao();
				}
				}
				setState(365);
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
		enterRule(_localctx, 70, RULE_expressaoNegacao);
		try {
			setState(369);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAO:
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				match(NAO);
				setState(367);
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
				setState(368);
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
		enterRule(_localctx, 72, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			expressaoAditiva();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) {
				{
				setState(372);
				operadorRelacional();
				setState(373);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoAditiva(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoAditivaContext expressaoAditiva() throws RecognitionException {
		ExpressaoAditivaContext _localctx = new ExpressaoAditivaContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_expressaoAditiva);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			expressaoMultiplicativa();
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2302102470656L) != 0)) {
				{
				{
				setState(378);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2302102470656L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(379);
				expressaoMultiplicativa();
				}
				}
				setState(384);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoMultiplicativa(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoMultiplicativaContext expressaoMultiplicativa() throws RecognitionException {
		ExpressaoMultiplicativaContext _localctx = new ExpressaoMultiplicativaContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressaoMultiplicativa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			expressaoUnaria();
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) {
				{
				{
				setState(386);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(387);
				expressaoUnaria();
				}
				}
				setState(392);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitExpressaoUnaria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoUnariaContext expressaoUnaria() throws RecognitionException {
		ExpressaoUnariaContext _localctx = new ExpressaoUnariaContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_expressaoUnaria);
		try {
			setState(398);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MENOS:
				enterOuterAlt(_localctx, 1);
				{
				setState(393);
				match(MENOS);
				setState(394);
				expressaoUnaria();
				}
				break;
			case MAIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
				match(MAIS);
				setState(396);
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
				setState(397);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitOperando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandoContext operando() throws RecognitionException {
		OperandoContext _localctx = new OperandoContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_operando);
		try {
			setState(413);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				match(NUM);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				match(NUMDEC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(402);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(403);
				match(CHAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(404);
				match(VERDADEIRO);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(405);
				match(FALSO);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(406);
				match(ID);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(407);
				acessoVetor();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(408);
				chamadaFuncao();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(409);
				match(ABREPARENTE);
				setState(410);
				expressao();
				setState(411);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAcessoVetor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcessoVetorContext acessoVetor() throws RecognitionException {
		AcessoVetorContext _localctx = new AcessoVetorContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_acessoVetor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(ID);
			setState(417); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(416);
				acessoDimensao();
				}
				}
				setState(419); 
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
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode FECHACOLCHETE() { return getToken(TarbaloParser.FECHACOLCHETE, 0); }
		public TerminalNode PONTOPONTO() { return getToken(TarbaloParser.PONTOPONTO, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TarbaloVisitor ) return ((TarbaloVisitor<? extends T>)visitor).visitAcessoDimensao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcessoDimensaoContext acessoDimensao() throws RecognitionException {
		AcessoDimensaoContext _localctx = new AcessoDimensaoContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_acessoDimensao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(ABRECOLCHETE);
			setState(422);
			expressao();
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PONTOPONTO) {
				{
				setState(423);
				match(PONTOPONTO);
				setState(424);
				expressao();
				}
			}

			setState(427);
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
		enterRule(_localctx, 86, RULE_chamadaFuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(ID);
			setState(430);
			match(ABREPARENTE);
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 8521785546755L) != 0)) {
				{
				setState(431);
				argumentos();
				}
			}

			setState(434);
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
		enterRule(_localctx, 88, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			expressao();
			setState(441);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTIVIRGULA) {
				{
				{
				setState(437);
				match(PONTIVIRGULA);
				setState(438);
				expressao();
				}
				}
				setState(443);
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
		enterRule(_localctx, 90, RULE_operadorRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
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
		public TerminalNode CONCAT_ATRIBUICAO() { return getToken(TarbaloParser.CONCAT_ATRIBUICAO, 0); }
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
		enterRule(_localctx, 92, RULE_operadorAtribuicaoComposta);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4227858432L) != 0)) ) {
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
		"\u0004\u0001E\u01c1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0001\u0000\u0001\u0000\u0004\u0000a\b\u0000\u000b"+
		"\u0000\f\u0000b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001l\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002s\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003y\b\u0003\n\u0003"+
		"\f\u0003|\t\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u0082\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u0086\b\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0004\u0007\u008e\b\u0007\u000b\u0007\f\u0007\u008f\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0094\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0005\b\u009c\b\b\n\b\f\b\u009f\t\b\u0003\b\u00a1\b"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00aa"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0005\n\u00b5\b\n\n\n\f\n\u00b8\t\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0004\f\u00be\b\f\u000b\f\f\f\u00bf\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d3\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00e0\b\u000f\n"+
		"\u000f\f\u000f\u00e3\t\u000f\u0003\u000f\u00e5\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00ec\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00f1\b\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u010e\b\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0135"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0003"+
		"\u001b\u013c\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003\u001d\u014d"+
		"\b\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0005 \u015a\b \n"+
		" \f \u015d\t \u0001!\u0001!\u0001!\u0005!\u0162\b!\n!\f!\u0165\t!\u0001"+
		"\"\u0001\"\u0001\"\u0005\"\u016a\b\"\n\"\f\"\u016d\t\"\u0001#\u0001#\u0001"+
		"#\u0003#\u0172\b#\u0001$\u0001$\u0001$\u0001$\u0003$\u0178\b$\u0001%\u0001"+
		"%\u0001%\u0005%\u017d\b%\n%\f%\u0180\t%\u0001&\u0001&\u0001&\u0005&\u0185"+
		"\b&\n&\f&\u0188\t&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u018f"+
		"\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0003(\u019e\b(\u0001)\u0001)\u0004)\u01a2\b)\u000b"+
		")\f)\u01a3\u0001*\u0001*\u0001*\u0001*\u0003*\u01aa\b*\u0001*\u0001*\u0001"+
		"+\u0001+\u0001+\u0003+\u01b1\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0005"+
		",\u01b8\b,\n,\f,\u01bb\t,\u0001-\u0001-\u0001.\u0001.\u0001.\u0000\u0000"+
		"/\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\\u0000\u0005\u0001\u0000"+
		"\u0005\t\u0002\u0000#$))\u0001\u0000%(\u0001\u0000+0\u0001\u0000\u001a"+
		"\u001f\u01cc\u0000^\u0001\u0000\u0000\u0000\u0002k\u0001\u0000\u0000\u0000"+
		"\u0004m\u0001\u0000\u0000\u0000\u0006v\u0001\u0000\u0000\u0000\b}\u0001"+
		"\u0000\u0000\u0000\n\u0081\u0001\u0000\u0000\u0000\f\u0083\u0001\u0000"+
		"\u0000\u0000\u000e\u0089\u0001\u0000\u0000\u0000\u0010\u0097\u0001\u0000"+
		"\u0000\u0000\u0012\u00a4\u0001\u0000\u0000\u0000\u0014\u00b1\u0001\u0000"+
		"\u0000\u0000\u0016\u00b9\u0001\u0000\u0000\u0000\u0018\u00bd\u0001\u0000"+
		"\u0000\u0000\u001a\u00d2\u0001\u0000\u0000\u0000\u001c\u00d4\u0001\u0000"+
		"\u0000\u0000\u001e\u00da\u0001\u0000\u0000\u0000 \u00eb\u0001\u0000\u0000"+
		"\u0000\"\u00ed\u0001\u0000\u0000\u0000$\u00f5\u0001\u0000\u0000\u0000"+
		"&\u00f8\u0001\u0000\u0000\u0000(\u00fb\u0001\u0000\u0000\u0000*\u00fe"+
		"\u0001\u0000\u0000\u0000,\u0101\u0001\u0000\u0000\u0000.\u0111\u0001\u0000"+
		"\u0000\u00000\u011a\u0001\u0000\u0000\u00002\u0124\u0001\u0000\u0000\u0000"+
		"4\u0131\u0001\u0000\u0000\u00006\u013b\u0001\u0000\u0000\u00008\u013d"+
		"\u0001\u0000\u0000\u0000:\u014a\u0001\u0000\u0000\u0000<\u0150\u0001\u0000"+
		"\u0000\u0000>\u0153\u0001\u0000\u0000\u0000@\u0156\u0001\u0000\u0000\u0000"+
		"B\u015e\u0001\u0000\u0000\u0000D\u0166\u0001\u0000\u0000\u0000F\u0171"+
		"\u0001\u0000\u0000\u0000H\u0173\u0001\u0000\u0000\u0000J\u0179\u0001\u0000"+
		"\u0000\u0000L\u0181\u0001\u0000\u0000\u0000N\u018e\u0001\u0000\u0000\u0000"+
		"P\u019d\u0001\u0000\u0000\u0000R\u019f\u0001\u0000\u0000\u0000T\u01a5"+
		"\u0001\u0000\u0000\u0000V\u01ad\u0001\u0000\u0000\u0000X\u01b4\u0001\u0000"+
		"\u0000\u0000Z\u01bc\u0001\u0000\u0000\u0000\\\u01be\u0001\u0000\u0000"+
		"\u0000^`\u0005\u0001\u0000\u0000_a\u0003\u0018\f\u0000`_\u0001\u0000\u0000"+
		"\u0000ab\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0005\u0002\u0000\u0000ef\u0005"+
		"=\u0000\u0000fg\u0005\u0000\u0000\u0001g\u0001\u0001\u0000\u0000\u0000"+
		"hl\u0003\u0004\u0002\u0000il\u0003\u000e\u0007\u0000jl\u0003\u0012\t\u0000"+
		"kh\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kj\u0001\u0000\u0000"+
		"\u0000l\u0003\u0001\u0000\u0000\u0000mn\u0005\u0003\u0000\u0000no\u0003"+
		"\b\u0004\u0000or\u0005B\u0000\u0000pq\u0005*\u0000\u0000qs\u0003@ \u0000"+
		"rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000tu\u0005=\u0000\u0000u\u0005\u0001\u0000\u0000\u0000vz\u0005B\u0000"+
		"\u0000wy\u0003\f\u0006\u0000xw\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000"+
		"\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{\u0007\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0007\u0000\u0000\u0000"+
		"~\t\u0001\u0000\u0000\u0000\u007f\u0082\u0003\b\u0004\u0000\u0080\u0082"+
		"\u0005\n\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0080\u0001"+
		"\u0000\u0000\u0000\u0082\u000b\u0001\u0000\u0000\u0000\u0083\u0085\u0005"+
		"9\u0000\u0000\u0084\u0086\u0005?\u0000\u0000\u0085\u0084\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0005:\u0000\u0000\u0088\r\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0005\u0004\u0000\u0000\u008a\u008b\u0003\b\u0004\u0000\u008b"+
		"\u008d\u0005B\u0000\u0000\u008c\u008e\u0003\f\u0006\u0000\u008d\u008c"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0093"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0005*\u0000\u0000\u0092\u0094\u0003"+
		"\u0010\b\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0096\u0005=\u0000"+
		"\u0000\u0096\u000f\u0001\u0000\u0000\u0000\u0097\u00a0\u00059\u0000\u0000"+
		"\u0098\u009d\u0003@ \u0000\u0099\u009a\u0005<\u0000\u0000\u009a\u009c"+
		"\u0003@ \u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000"+
		"\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000"+
		"\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000"+
		"\u0000\u0000\u00a0\u0098\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005:\u0000"+
		"\u0000\u00a3\u0011\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\u000b\u0000"+
		"\u0000\u00a5\u00a6\u0003\n\u0005\u0000\u00a6\u00a7\u0005B\u0000\u0000"+
		"\u00a7\u00a9\u00055\u0000\u0000\u00a8\u00aa\u0003\u0014\n\u0000\u00a9"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u00056\u0000\u0000\u00ac\u00ad"+
		"\u00057\u0000\u0000\u00ad\u00ae\u0003\u0018\f\u0000\u00ae\u00af\u0005"+
		"8\u0000\u0000\u00af\u00b0\u0005=\u0000\u0000\u00b0\u0013\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b6\u0003\u0016\u000b\u0000\u00b2\u00b3\u0005<\u0000\u0000"+
		"\u00b3\u00b5\u0003\u0016\u000b\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u0015\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ba\u0003\b\u0004\u0000\u00ba"+
		"\u00bb\u0003\u0006\u0003\u0000\u00bb\u0017\u0001\u0000\u0000\u0000\u00bc"+
		"\u00be\u0003\u001a\r\u0000\u00bd\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c0\u0019\u0001\u0000\u0000\u0000\u00c1\u00d3"+
		"\u0003\u0002\u0001\u0000\u00c2\u00d3\u0003\"\u0011\u0000\u00c3\u00d3\u0003"+
		"\u001c\u000e\u0000\u00c4\u00d3\u0003\u001e\u000f\u0000\u00c5\u00d3\u0003"+
		",\u0016\u0000\u00c6\u00d3\u0003.\u0017\u0000\u00c7\u00d3\u00030\u0018"+
		"\u0000\u00c8\u00d3\u00032\u0019\u0000\u00c9\u00d3\u00038\u001c\u0000\u00ca"+
		"\u00d3\u0003:\u001d\u0000\u00cb\u00d3\u0003<\u001e\u0000\u00cc\u00d3\u0003"+
		">\u001f\u0000\u00cd\u00d3\u0003(\u0014\u0000\u00ce\u00d3\u0003*\u0015"+
		"\u0000\u00cf\u00d0\u0003V+\u0000\u00d0\u00d1\u0005=\u0000\u0000\u00d1"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d2\u00c1\u0001\u0000\u0000\u0000\u00d2"+
		"\u00c2\u0001\u0000\u0000\u0000\u00d2\u00c3\u0001\u0000\u0000\u0000\u00d2"+
		"\u00c4\u0001\u0000\u0000\u0000\u00d2\u00c5\u0001\u0000\u0000\u0000\u00d2"+
		"\u00c6\u0001\u0000\u0000\u0000\u00d2\u00c7\u0001\u0000\u0000\u0000\u00d2"+
		"\u00c8\u0001\u0000\u0000\u0000\u00d2\u00c9\u0001\u0000\u0000\u0000\u00d2"+
		"\u00ca\u0001\u0000\u0000\u0000\u00d2\u00cb\u0001\u0000\u0000\u0000\u00d2"+
		"\u00cc\u0001\u0000\u0000\u0000\u00d2\u00cd\u0001\u0000\u0000\u0000\u00d2"+
		"\u00ce\u0001\u0000\u0000\u0000\u00d2\u00cf\u0001\u0000\u0000\u0000\u00d3"+
		"\u001b\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005\r\u0000\u0000\u00d5\u00d6"+
		"\u00055\u0000\u0000\u00d6\u00d7\u0003 \u0010\u0000\u00d7\u00d8\u00056"+
		"\u0000\u0000\u00d8\u00d9\u0005=\u0000\u0000\u00d9\u001d\u0001\u0000\u0000"+
		"\u0000\u00da\u00db\u0005\u000e\u0000\u0000\u00db\u00e4\u00055\u0000\u0000"+
		"\u00dc\u00e1\u0003@ \u0000\u00dd\u00de\u0005<\u0000\u0000\u00de\u00e0"+
		"\u0003@ \u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00e0\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e4\u00dc\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e7\u00056\u0000"+
		"\u0000\u00e7\u00e8\u0005=\u0000\u0000\u00e8\u001f\u0001\u0000\u0000\u0000"+
		"\u00e9\u00ec\u0005B\u0000\u0000\u00ea\u00ec\u0003R)\u0000\u00eb\u00e9"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00ec!\u0001"+
		"\u0000\u0000\u0000\u00ed\u00f0\u0003 \u0010\u0000\u00ee\u00f1\u0005*\u0000"+
		"\u0000\u00ef\u00f1\u0003\\.\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f3\u0003@ \u0000\u00f3\u00f4\u0005=\u0000\u0000\u00f4#\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0003 \u0010\u0000\u00f6\u00f7\u0005 \u0000"+
		"\u0000\u00f7%\u0001\u0000\u0000\u0000\u00f8\u00f9\u0003 \u0010\u0000\u00f9"+
		"\u00fa\u0005!\u0000\u0000\u00fa\'\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0003$\u0012\u0000\u00fc\u00fd\u0005=\u0000\u0000\u00fd)\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0003&\u0013\u0000\u00ff\u0100\u0005=\u0000\u0000"+
		"\u0100+\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u000f\u0000\u0000\u0102"+
		"\u0103\u00055\u0000\u0000\u0103\u0104\u0003@ \u0000\u0104\u0105\u0005"+
		"6\u0000\u0000\u0105\u0106\u00057\u0000\u0000\u0106\u0107\u0003\u0018\f"+
		"\u0000\u0107\u010d\u00058\u0000\u0000\u0108\u0109\u0005\u0010\u0000\u0000"+
		"\u0109\u010a\u00057\u0000\u0000\u010a\u010b\u0003\u0018\f\u0000\u010b"+
		"\u010c\u00058\u0000\u0000\u010c\u010e\u0001\u0000\u0000\u0000\u010d\u0108"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0001\u0000\u0000\u0000\u010f\u0110\u0005=\u0000\u0000\u0110-\u0001\u0000"+
		"\u0000\u0000\u0111\u0112\u0005\u0011\u0000\u0000\u0112\u0113\u00055\u0000"+
		"\u0000\u0113\u0114\u0003@ \u0000\u0114\u0115\u00056\u0000\u0000\u0115"+
		"\u0116\u00057\u0000\u0000\u0116\u0117\u0003\u0018\f\u0000\u0117\u0118"+
		"\u00058\u0000\u0000\u0118\u0119\u0005=\u0000\u0000\u0119/\u0001\u0000"+
		"\u0000\u0000\u011a\u011b\u0005\u0012\u0000\u0000\u011b\u011c\u00057\u0000"+
		"\u0000\u011c\u011d\u0003\u0018\f\u0000\u011d\u011e\u00058\u0000\u0000"+
		"\u011e\u011f\u0005\u0011\u0000\u0000\u011f\u0120\u00055\u0000\u0000\u0120"+
		"\u0121\u0003@ \u0000\u0121\u0122\u00056\u0000\u0000\u0122\u0123\u0005"+
		"=\u0000\u0000\u01231\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u0013\u0000"+
		"\u0000\u0125\u0126\u00055\u0000\u0000\u0126\u0127\u00034\u001a\u0000\u0127"+
		"\u0128\u0005=\u0000\u0000\u0128\u0129\u0003@ \u0000\u0129\u012a\u0005"+
		"=\u0000\u0000\u012a\u012b\u00036\u001b\u0000\u012b\u012c\u00056\u0000"+
		"\u0000\u012c\u012d\u00057\u0000\u0000\u012d\u012e\u0003\u0018\f\u0000"+
		"\u012e\u012f\u00058\u0000\u0000\u012f\u0130\u0005=\u0000\u0000\u01303"+
		"\u0001\u0000\u0000\u0000\u0131\u0134\u0003 \u0010\u0000\u0132\u0135\u0005"+
		"*\u0000\u0000\u0133\u0135\u0003\\.\u0000\u0134\u0132\u0001\u0000\u0000"+
		"\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000"+
		"\u0000\u0136\u0137\u0003@ \u0000\u01375\u0001\u0000\u0000\u0000\u0138"+
		"\u013c\u0003$\u0012\u0000\u0139\u013c\u0003&\u0013\u0000\u013a\u013c\u0003"+
		"4\u001a\u0000\u013b\u0138\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000"+
		"\u0000\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013c7\u0001\u0000\u0000"+
		"\u0000\u013d\u013e\u0005\u0014\u0000\u0000\u013e\u013f\u00055\u0000\u0000"+
		"\u013f\u0140\u0005\u0003\u0000\u0000\u0140\u0141\u0003\b\u0004\u0000\u0141"+
		"\u0142\u0005B\u0000\u0000\u0142\u0143\u0005\u0015\u0000\u0000\u0143\u0144"+
		"\u0003@ \u0000\u0144\u0145\u00056\u0000\u0000\u0145\u0146\u00057\u0000"+
		"\u0000\u0146\u0147\u0003\u0018\f\u0000\u0147\u0148\u00058\u0000\u0000"+
		"\u0148\u0149\u0005=\u0000\u0000\u01499\u0001\u0000\u0000\u0000\u014a\u014c"+
		"\u0005\f\u0000\u0000\u014b\u014d\u0003@ \u0000\u014c\u014b\u0001\u0000"+
		"\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000"+
		"\u0000\u0000\u014e\u014f\u0005=\u0000\u0000\u014f;\u0001\u0000\u0000\u0000"+
		"\u0150\u0151\u0005\u0016\u0000\u0000\u0151\u0152\u0005=\u0000\u0000\u0152"+
		"=\u0001\u0000\u0000\u0000\u0153\u0154\u0005\u0017\u0000\u0000\u0154\u0155"+
		"\u0005=\u0000\u0000\u0155?\u0001\u0000\u0000\u0000\u0156\u015b\u0003B"+
		"!\u0000\u0157\u0158\u00053\u0000\u0000\u0158\u015a\u0003B!\u0000\u0159"+
		"\u0157\u0001\u0000\u0000\u0000\u015a\u015d\u0001\u0000\u0000\u0000\u015b"+
		"\u0159\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000\u0000\u0000\u015c"+
		"A\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015e\u0163"+
		"\u0003D\"\u0000\u015f\u0160\u00054\u0000\u0000\u0160\u0162\u0003D\"\u0000"+
		"\u0161\u015f\u0001\u0000\u0000\u0000\u0162\u0165\u0001\u0000\u0000\u0000"+
		"\u0163\u0161\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000"+
		"\u0164C\u0001\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166"+
		"\u016b\u0003F#\u0000\u0167\u0168\u00052\u0000\u0000\u0168\u016a\u0003"+
		"F#\u0000\u0169\u0167\u0001\u0000\u0000\u0000\u016a\u016d\u0001\u0000\u0000"+
		"\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000"+
		"\u0000\u016cE\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000\u0000\u0000"+
		"\u016e\u016f\u00051\u0000\u0000\u016f\u0172\u0003F#\u0000\u0170\u0172"+
		"\u0003H$\u0000\u0171\u016e\u0001\u0000\u0000\u0000\u0171\u0170\u0001\u0000"+
		"\u0000\u0000\u0172G\u0001\u0000\u0000\u0000\u0173\u0177\u0003J%\u0000"+
		"\u0174\u0175\u0003Z-\u0000\u0175\u0176\u0003J%\u0000\u0176\u0178\u0001"+
		"\u0000\u0000\u0000\u0177\u0174\u0001\u0000\u0000\u0000\u0177\u0178\u0001"+
		"\u0000\u0000\u0000\u0178I\u0001\u0000\u0000\u0000\u0179\u017e\u0003L&"+
		"\u0000\u017a\u017b\u0007\u0001\u0000\u0000\u017b\u017d\u0003L&\u0000\u017c"+
		"\u017a\u0001\u0000\u0000\u0000\u017d\u0180\u0001\u0000\u0000\u0000\u017e"+
		"\u017c\u0001\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f"+
		"K\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0181\u0186"+
		"\u0003N\'\u0000\u0182\u0183\u0007\u0002\u0000\u0000\u0183\u0185\u0003"+
		"N\'\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0185\u0188\u0001\u0000"+
		"\u0000\u0000\u0186\u0184\u0001\u0000\u0000\u0000\u0186\u0187\u0001\u0000"+
		"\u0000\u0000\u0187M\u0001\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000"+
		"\u0000\u0189\u018a\u0005$\u0000\u0000\u018a\u018f\u0003N\'\u0000\u018b"+
		"\u018c\u0005#\u0000\u0000\u018c\u018f\u0003N\'\u0000\u018d\u018f\u0003"+
		"P(\u0000\u018e\u0189\u0001\u0000\u0000\u0000\u018e\u018b\u0001\u0000\u0000"+
		"\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018fO\u0001\u0000\u0000\u0000"+
		"\u0190\u019e\u0005?\u0000\u0000\u0191\u019e\u0005>\u0000\u0000\u0192\u019e"+
		"\u0005A\u0000\u0000\u0193\u019e\u0005@\u0000\u0000\u0194\u019e\u0005\u0018"+
		"\u0000\u0000\u0195\u019e\u0005\u0019\u0000\u0000\u0196\u019e\u0005B\u0000"+
		"\u0000\u0197\u019e\u0003R)\u0000\u0198\u019e\u0003V+\u0000\u0199\u019a"+
		"\u00055\u0000\u0000\u019a\u019b\u0003@ \u0000\u019b\u019c\u00056\u0000"+
		"\u0000\u019c\u019e\u0001\u0000\u0000\u0000\u019d\u0190\u0001\u0000\u0000"+
		"\u0000\u019d\u0191\u0001\u0000\u0000\u0000\u019d\u0192\u0001\u0000\u0000"+
		"\u0000\u019d\u0193\u0001\u0000\u0000\u0000\u019d\u0194\u0001\u0000\u0000"+
		"\u0000\u019d\u0195\u0001\u0000\u0000\u0000\u019d\u0196\u0001\u0000\u0000"+
		"\u0000\u019d\u0197\u0001\u0000\u0000\u0000\u019d\u0198\u0001\u0000\u0000"+
		"\u0000\u019d\u0199\u0001\u0000\u0000\u0000\u019eQ\u0001\u0000\u0000\u0000"+
		"\u019f\u01a1\u0005B\u0000\u0000\u01a0\u01a2\u0003T*\u0000\u01a1\u01a0"+
		"\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000\u01a3\u01a1"+
		"\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4S\u0001"+
		"\u0000\u0000\u0000\u01a5\u01a6\u00059\u0000\u0000\u01a6\u01a9\u0003@ "+
		"\u0000\u01a7\u01a8\u0005\"\u0000\u0000\u01a8\u01aa\u0003@ \u0000\u01a9"+
		"\u01a7\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa"+
		"\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0005:\u0000\u0000\u01acU\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ae\u0005B\u0000\u0000\u01ae\u01b0\u00055\u0000"+
		"\u0000\u01af\u01b1\u0003X,\u0000\u01b0\u01af\u0001\u0000\u0000\u0000\u01b0"+
		"\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2"+
		"\u01b3\u00056\u0000\u0000\u01b3W\u0001\u0000\u0000\u0000\u01b4\u01b9\u0003"+
		"@ \u0000\u01b5\u01b6\u0005<\u0000\u0000\u01b6\u01b8\u0003@ \u0000\u01b7"+
		"\u01b5\u0001\u0000\u0000\u0000\u01b8\u01bb\u0001\u0000\u0000\u0000\u01b9"+
		"\u01b7\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba"+
		"Y\u0001\u0000\u0000\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bc\u01bd"+
		"\u0007\u0003\u0000\u0000\u01bd[\u0001\u0000\u0000\u0000\u01be\u01bf\u0007"+
		"\u0004\u0000\u0000\u01bf]\u0001\u0000\u0000\u0000#bkrz\u0081\u0085\u008f"+
		"\u0093\u009d\u00a0\u00a9\u00b6\u00bf\u00d2\u00e1\u00e4\u00eb\u00f0\u010d"+
		"\u0134\u013b\u014c\u015b\u0163\u016b\u0171\u0177\u017e\u0186\u018e\u019d"+
		"\u01a3\u01a9\u01b0\u01b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}