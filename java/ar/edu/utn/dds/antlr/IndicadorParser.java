// Generated from /home/rodrigo/repository/dds-inversiones/src/antlr4/ar.edu.utn.antlr/Indicador.g4 by ANTLR 4.7
package ar.edu.utn.dds.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IndicadorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNC_STATIC=1, PI_FUNC=2, FUNC_UNARY=3, SQRT_FUNC=4, FUNC_BINARY=5, ROOT_FUNC=6, 
		IND=7, CUE=8, VAR=9, DOUBLE=10, NUMBER=11, DIGIT=12, WORD=13, LETTER=14, 
		ASSIGN=15, LPAR=16, RPAR=17, COMMA=18, RAISE_OP=19, MULTIPLICATIVE_OP=20, 
		ADDITIVE_OP=21, WS=22;
	public static final int
		RULE_expression = 0, RULE_between_parenthesis = 1, RULE_signed_termino = 2, 
		RULE_termino = 3, RULE_terminal = 4, RULE_raise_operation = 5, RULE_function = 6, 
		RULE_function_static = 7, RULE_function_unary = 8, RULE_function_binary = 9, 
		RULE_primary = 10;
	public static final String[] ruleNames = {
		"expression", "between_parenthesis", "signed_termino", "termino", "terminal", 
		"raise_operation", "function", "function_static", "function_unary", "function_binary", 
		"primary"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'pi'", null, "'sqrt'", null, "'root'", "'ind'", "'cue'", 
		null, null, null, null, null, null, "'='", "'('", "')'", "','", "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNC_STATIC", "PI_FUNC", "FUNC_UNARY", "SQRT_FUNC", "FUNC_BINARY", 
		"ROOT_FUNC", "IND", "CUE", "VAR", "DOUBLE", "NUMBER", "DIGIT", "WORD", 
		"LETTER", "ASSIGN", "LPAR", "RPAR", "COMMA", "RAISE_OP", "MULTIPLICATIVE_OP", 
		"ADDITIVE_OP", "WS"
	};
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
	public String getGrammarFileName() { return "Indicador.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IndicadorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Additive_operationContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADDITIVE_OP() { return getToken(IndicadorParser.ADDITIVE_OP, 0); }
		public Additive_operationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterAdditive_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitAdditive_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitAdditive_operation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TermContext extends ExpressionContext {
		public TerminoContext termino() {
			return getRuleContext(TerminoContext.class,0);
		}
		public TermContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Multiplicative_operationContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULTIPLICATIVE_OP() { return getToken(IndicadorParser.MULTIPLICATIVE_OP, 0); }
		public Multiplicative_operationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterMultiplicative_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitMultiplicative_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitMultiplicative_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new TermContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(23);
			termino();
			}
			_ctx.stop = _input.LT(-1);
			setState(33);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(31);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new Multiplicative_operationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(25);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(26);
						match(MULTIPLICATIVE_OP);
						setState(27);
						expression(3);
						}
						break;
					case 2:
						{
						_localctx = new Additive_operationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(28);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(29);
						match(ADDITIVE_OP);
						setState(30);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(35);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Between_parenthesisContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(IndicadorParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(IndicadorParser.RPAR, 0); }
		public Between_parenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_parenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterBetween_parenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitBetween_parenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitBetween_parenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_parenthesisContext between_parenthesis() throws RecognitionException {
		Between_parenthesisContext _localctx = new Between_parenthesisContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_between_parenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(LPAR);
			setState(37);
			expression(0);
			setState(38);
			match(RPAR);
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

	public static class Signed_terminoContext extends ParserRuleContext {
		public TerminalNode ADDITIVE_OP() { return getToken(IndicadorParser.ADDITIVE_OP, 0); }
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public Raise_operationContext raise_operation() {
			return getRuleContext(Raise_operationContext.class,0);
		}
		public Signed_terminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_termino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterSigned_termino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitSigned_termino(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitSigned_termino(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Signed_terminoContext signed_termino() throws RecognitionException {
		Signed_terminoContext _localctx = new Signed_terminoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_signed_termino);
		try {
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				match(ADDITIVE_OP);
				setState(41);
				terminal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(ADDITIVE_OP);
				setState(43);
				raise_operation();
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

	public static class TerminoContext extends ParserRuleContext {
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public Raise_operationContext raise_operation() {
			return getRuleContext(Raise_operationContext.class,0);
		}
		public Signed_terminoContext signed_termino() {
			return getRuleContext(Signed_terminoContext.class,0);
		}
		public TerminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterTermino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitTermino(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitTermino(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_termino);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				terminal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				raise_operation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				signed_termino();
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

	public static class TerminalContext extends ParserRuleContext {
		public Between_parenthesisContext between_parenthesis() {
			return getRuleContext(Between_parenthesisContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitTerminal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitTerminal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_terminal);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				between_parenthesis();
				}
				break;
			case FUNC_STATIC:
			case FUNC_UNARY:
			case FUNC_BINARY:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				function();
				}
				break;
			case IND:
			case CUE:
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				primary();
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

	public static class Raise_operationContext extends ParserRuleContext {
		public List<TerminalContext> terminal() {
			return getRuleContexts(TerminalContext.class);
		}
		public TerminalContext terminal(int i) {
			return getRuleContext(TerminalContext.class,i);
		}
		public TerminalNode RAISE_OP() { return getToken(IndicadorParser.RAISE_OP, 0); }
		public Raise_operationContext raise_operation() {
			return getRuleContext(Raise_operationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Raise_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterRaise_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitRaise_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitRaise_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Raise_operationContext raise_operation() throws RecognitionException {
		Raise_operationContext _localctx = new Raise_operationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_raise_operation);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				terminal();
				setState(57);
				match(RAISE_OP);
				setState(58);
				terminal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				terminal();
				setState(61);
				match(RAISE_OP);
				setState(62);
				raise_operation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				terminal();
				setState(65);
				match(RAISE_OP);
				setState(66);
				expression(0);
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

	public static class FunctionContext extends ParserRuleContext {
		public Function_staticContext function_static() {
			return getRuleContext(Function_staticContext.class,0);
		}
		public Function_unaryContext function_unary() {
			return getRuleContext(Function_unaryContext.class,0);
		}
		public Function_binaryContext function_binary() {
			return getRuleContext(Function_binaryContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNC_STATIC:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				function_static();
				}
				break;
			case FUNC_UNARY:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				function_unary();
				}
				break;
			case FUNC_BINARY:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				function_binary();
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

	public static class Function_staticContext extends ParserRuleContext {
		public TerminalNode FUNC_STATIC() { return getToken(IndicadorParser.FUNC_STATIC, 0); }
		public TerminalNode LPAR() { return getToken(IndicadorParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(IndicadorParser.RPAR, 0); }
		public Function_staticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_static; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterFunction_static(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitFunction_static(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitFunction_static(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_staticContext function_static() throws RecognitionException {
		Function_staticContext _localctx = new Function_staticContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_static);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(FUNC_STATIC);
			setState(76);
			match(LPAR);
			setState(77);
			match(RPAR);
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

	public static class Function_unaryContext extends ParserRuleContext {
		public TerminalNode FUNC_UNARY() { return getToken(IndicadorParser.FUNC_UNARY, 0); }
		public TerminalNode LPAR() { return getToken(IndicadorParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(IndicadorParser.RPAR, 0); }
		public Function_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterFunction_unary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitFunction_unary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitFunction_unary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_unaryContext function_unary() throws RecognitionException {
		Function_unaryContext _localctx = new Function_unaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function_unary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(FUNC_UNARY);
			setState(80);
			match(LPAR);
			setState(81);
			expression(0);
			setState(82);
			match(RPAR);
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

	public static class Function_binaryContext extends ParserRuleContext {
		public TerminalNode FUNC_BINARY() { return getToken(IndicadorParser.FUNC_BINARY, 0); }
		public TerminalNode LPAR() { return getToken(IndicadorParser.LPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(IndicadorParser.COMMA, 0); }
		public TerminalNode RPAR() { return getToken(IndicadorParser.RPAR, 0); }
		public Function_binaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterFunction_binary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitFunction_binary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitFunction_binary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_binaryContext function_binary() throws RecognitionException {
		Function_binaryContext _localctx = new Function_binaryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_function_binary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(FUNC_BINARY);
			setState(85);
			match(LPAR);
			setState(86);
			expression(0);
			setState(87);
			match(COMMA);
			setState(88);
			expression(0);
			setState(89);
			match(RPAR);
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

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode IND() { return getToken(IndicadorParser.IND, 0); }
		public TerminalNode LPAR() { return getToken(IndicadorParser.LPAR, 0); }
		public TerminalNode VAR() { return getToken(IndicadorParser.VAR, 0); }
		public TerminalNode RPAR() { return getToken(IndicadorParser.RPAR, 0); }
		public TerminalNode CUE() { return getToken(IndicadorParser.CUE, 0); }
		public TerminalNode DOUBLE() { return getToken(IndicadorParser.DOUBLE, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicadorListener ) ((IndicadorListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IndicadorVisitor ) return ((IndicadorVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primary);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IND:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(IND);
				setState(92);
				match(LPAR);
				setState(93);
				match(VAR);
				setState(94);
				match(RPAR);
				}
				break;
			case CUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				match(CUE);
				setState(96);
				match(LPAR);
				setState(97);
				match(VAR);
				setState(98);
				match(RPAR);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(DOUBLE);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30i\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2%\13\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4/\n\4\3\5\3\5\3\5\5\5\64\n\5\3\6\3\6"+
		"\3\6\5\69\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7G\n\7"+
		"\3\b\3\b\3\b\5\bL\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fg\n\f\3"+
		"\f\2\3\2\r\2\4\6\b\n\f\16\20\22\24\26\2\2\2j\2\30\3\2\2\2\4&\3\2\2\2\6"+
		".\3\2\2\2\b\63\3\2\2\2\n8\3\2\2\2\fF\3\2\2\2\16K\3\2\2\2\20M\3\2\2\2\22"+
		"Q\3\2\2\2\24V\3\2\2\2\26f\3\2\2\2\30\31\b\2\1\2\31\32\5\b\5\2\32#\3\2"+
		"\2\2\33\34\f\4\2\2\34\35\7\26\2\2\35\"\5\2\2\5\36\37\f\3\2\2\37 \7\27"+
		"\2\2 \"\5\2\2\4!\33\3\2\2\2!\36\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2"+
		"$\3\3\2\2\2%#\3\2\2\2&\'\7\22\2\2\'(\5\2\2\2()\7\23\2\2)\5\3\2\2\2*+\7"+
		"\27\2\2+/\5\n\6\2,-\7\27\2\2-/\5\f\7\2.*\3\2\2\2.,\3\2\2\2/\7\3\2\2\2"+
		"\60\64\5\n\6\2\61\64\5\f\7\2\62\64\5\6\4\2\63\60\3\2\2\2\63\61\3\2\2\2"+
		"\63\62\3\2\2\2\64\t\3\2\2\2\659\5\4\3\2\669\5\16\b\2\679\5\26\f\28\65"+
		"\3\2\2\28\66\3\2\2\28\67\3\2\2\29\13\3\2\2\2:;\5\n\6\2;<\7\25\2\2<=\5"+
		"\n\6\2=G\3\2\2\2>?\5\n\6\2?@\7\25\2\2@A\5\f\7\2AG\3\2\2\2BC\5\n\6\2CD"+
		"\7\25\2\2DE\5\2\2\2EG\3\2\2\2F:\3\2\2\2F>\3\2\2\2FB\3\2\2\2G\r\3\2\2\2"+
		"HL\5\20\t\2IL\5\22\n\2JL\5\24\13\2KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\17\3"+
		"\2\2\2MN\7\3\2\2NO\7\22\2\2OP\7\23\2\2P\21\3\2\2\2QR\7\5\2\2RS\7\22\2"+
		"\2ST\5\2\2\2TU\7\23\2\2U\23\3\2\2\2VW\7\7\2\2WX\7\22\2\2XY\5\2\2\2YZ\7"+
		"\24\2\2Z[\5\2\2\2[\\\7\23\2\2\\\25\3\2\2\2]^\7\t\2\2^_\7\22\2\2_`\7\13"+
		"\2\2`g\7\23\2\2ab\7\n\2\2bc\7\22\2\2cd\7\13\2\2dg\7\23\2\2eg\7\f\2\2f"+
		"]\3\2\2\2fa\3\2\2\2fe\3\2\2\2g\27\3\2\2\2\n!#.\638FKf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}