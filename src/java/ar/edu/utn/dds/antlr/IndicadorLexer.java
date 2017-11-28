// Generated from /home/rodrigo/repository/dds-inversiones/src/antlr4/ar.edu.utn.antlr/Indicador.g4 by ANTLR 4.7
package ar.edu.utn.dds.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IndicadorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNC_STATIC=1, PI_FUNC=2, FUNC_UNARY=3, SQRT_FUNC=4, FUNC_BINARY=5, ROOT_FUNC=6, 
		IND=7, CUE=8, ASSIGN=9, LPAR=10, RPAR=11, COMMA=12, RAISE_OP=13, MULTIPLICATIVE_OP=14, 
		ADDITIVE_OP=15, VAR=16, DOUBLE=17, NUMBER=18, DIGIT=19, WORD=20, LETTER=21, 
		WS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FUNC_STATIC", "PI_FUNC", "FUNC_UNARY", "SQRT_FUNC", "FUNC_BINARY", "ROOT_FUNC", 
		"IND", "CUE", "ASSIGN", "LPAR", "RPAR", "COMMA", "RAISE_OP", "MULTIPLICATIVE_OP", 
		"ADDITIVE_OP", "VAR", "DOUBLE", "NUMBER", "DIGIT", "WORD", "LETTER", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'pi'", null, "'sqrt'", null, "'root'", "'ind'", "'cue'", 
		"'='", "'('", "')'", "','", "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNC_STATIC", "PI_FUNC", "FUNC_UNARY", "SQRT_FUNC", "FUNC_BINARY", 
		"ROOT_FUNC", "IND", "CUE", "ASSIGN", "LPAR", "RPAR", "COMMA", "RAISE_OP", 
		"MULTIPLICATIVE_OP", "ADDITIVE_OP", "VAR", "DOUBLE", "NUMBER", "DIGIT", 
		"WORD", "LETTER", "WS"
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


	public IndicadorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Indicador.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\21\5\21\\\n\21\3\21\5\21_\n\21\3\22\3\22\3\22"+
		"\5\22d\n\22\3\23\6\23g\n\23\r\23\16\23h\3\24\3\24\3\25\6\25n\n\25\r\25"+
		"\16\25o\3\26\3\26\3\27\6\27u\n\27\r\27\16\27v\3\27\3\27\2\2\30\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30\3\2\6\4\2,,\61\61\4\2--//\4\2C\\c|\5\2\13\f"+
		"\17\17\"\"\2\177\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\64\3\2\2\2\t\66\3\2\2\2\13;\3\2\2\2\r"+
		"=\3\2\2\2\17B\3\2\2\2\21F\3\2\2\2\23J\3\2\2\2\25L\3\2\2\2\27N\3\2\2\2"+
		"\31P\3\2\2\2\33R\3\2\2\2\35T\3\2\2\2\37V\3\2\2\2!X\3\2\2\2#`\3\2\2\2%"+
		"f\3\2\2\2\'j\3\2\2\2)m\3\2\2\2+q\3\2\2\2-t\3\2\2\2/\60\5\5\3\2\60\4\3"+
		"\2\2\2\61\62\7r\2\2\62\63\7k\2\2\63\6\3\2\2\2\64\65\5\t\5\2\65\b\3\2\2"+
		"\2\66\67\7u\2\2\678\7s\2\289\7t\2\29:\7v\2\2:\n\3\2\2\2;<\5\r\7\2<\f\3"+
		"\2\2\2=>\7t\2\2>?\7q\2\2?@\7q\2\2@A\7v\2\2A\16\3\2\2\2BC\7k\2\2CD\7p\2"+
		"\2DE\7f\2\2E\20\3\2\2\2FG\7e\2\2GH\7w\2\2HI\7g\2\2I\22\3\2\2\2JK\7?\2"+
		"\2K\24\3\2\2\2LM\7*\2\2M\26\3\2\2\2NO\7+\2\2O\30\3\2\2\2PQ\7.\2\2Q\32"+
		"\3\2\2\2RS\7`\2\2S\34\3\2\2\2TU\t\2\2\2U\36\3\2\2\2VW\t\3\2\2W \3\2\2"+
		"\2X[\5)\25\2YZ\7a\2\2Z\\\5)\25\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2]_\5%"+
		"\23\2^]\3\2\2\2^_\3\2\2\2_\"\3\2\2\2`c\5%\23\2ab\7\60\2\2bd\5%\23\2ca"+
		"\3\2\2\2cd\3\2\2\2d$\3\2\2\2eg\5\'\24\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2"+
		"hi\3\2\2\2i&\3\2\2\2jk\4\62;\2k(\3\2\2\2ln\5+\26\2ml\3\2\2\2no\3\2\2\2"+
		"om\3\2\2\2op\3\2\2\2p*\3\2\2\2qr\t\4\2\2r,\3\2\2\2su\t\5\2\2ts\3\2\2\2"+
		"uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\b\27\2\2y.\3\2\2\2\t\2[^ch"+
		"ov\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}