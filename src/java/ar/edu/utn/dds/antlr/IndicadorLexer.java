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
		VAR=7, DOUBLE=8, NUMBER=9, DIGIT=10, WORD=11, LETTER=12, ASSIGN=13, LPAR=14, 
		RPAR=15, COMMA=16, RAISE_OP=17, MULTIPLICATIVE_OP=18, ADDITIVE_OP=19, 
		WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FUNC_STATIC", "PI_FUNC", "FUNC_UNARY", "SQRT_FUNC", "FUNC_BINARY", "ROOT_FUNC", 
		"VAR", "DOUBLE", "NUMBER", "DIGIT", "WORD", "LETTER", "ASSIGN", "LPAR", 
		"RPAR", "COMMA", "RAISE_OP", "MULTIPLICATIVE_OP", "ADDITIVE_OP", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'pi'", null, "'sqrt'", null, "'root'", null, null, null, 
		null, null, null, "'='", "'('", "')'", "','", "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNC_STATIC", "PI_FUNC", "FUNC_UNARY", "SQRT_FUNC", "FUNC_BINARY", 
		"ROOT_FUNC", "VAR", "DOUBLE", "NUMBER", "DIGIT", "WORD", "LETTER", "ASSIGN", 
		"LPAR", "RPAR", "COMMA", "RAISE_OP", "MULTIPLICATIVE_OP", "ADDITIVE_OP", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26n\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\5\bB\n\b\3\b\5\bE\n\b"+
		"\3\t\3\t\3\t\5\tJ\n\t\3\n\6\nM\n\n\r\n\16\nN\3\13\3\13\3\f\6\fT\n\f\r"+
		"\f\16\fU\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\6\25i\n\25\r\25\16\25j\3\25\3\25\2\2\26\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26\3\2\6\4\2C\\c|\4\2,,\61\61\4\2--//\5\2\13\f\17\17\""+
		"\"\2s\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r"+
		"\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2"+
		"\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2"+
		"#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7\60"+
		"\3\2\2\2\t\62\3\2\2\2\13\67\3\2\2\2\r9\3\2\2\2\17>\3\2\2\2\21F\3\2\2\2"+
		"\23L\3\2\2\2\25P\3\2\2\2\27S\3\2\2\2\31W\3\2\2\2\33Y\3\2\2\2\35[\3\2\2"+
		"\2\37]\3\2\2\2!_\3\2\2\2#a\3\2\2\2%c\3\2\2\2\'e\3\2\2\2)h\3\2\2\2+,\5"+
		"\5\3\2,\4\3\2\2\2-.\7r\2\2./\7k\2\2/\6\3\2\2\2\60\61\5\t\5\2\61\b\3\2"+
		"\2\2\62\63\7u\2\2\63\64\7s\2\2\64\65\7t\2\2\65\66\7v\2\2\66\n\3\2\2\2"+
		"\678\5\r\7\28\f\3\2\2\29:\7t\2\2:;\7q\2\2;<\7q\2\2<=\7v\2\2=\16\3\2\2"+
		"\2>A\5\27\f\2?@\7a\2\2@B\5\27\f\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\5\23"+
		"\n\2DC\3\2\2\2DE\3\2\2\2E\20\3\2\2\2FI\5\23\n\2GH\7\60\2\2HJ\5\23\n\2"+
		"IG\3\2\2\2IJ\3\2\2\2J\22\3\2\2\2KM\5\25\13\2LK\3\2\2\2MN\3\2\2\2NL\3\2"+
		"\2\2NO\3\2\2\2O\24\3\2\2\2PQ\4\62;\2Q\26\3\2\2\2RT\5\31\r\2SR\3\2\2\2"+
		"TU\3\2\2\2US\3\2\2\2UV\3\2\2\2V\30\3\2\2\2WX\t\2\2\2X\32\3\2\2\2YZ\7?"+
		"\2\2Z\34\3\2\2\2[\\\7*\2\2\\\36\3\2\2\2]^\7+\2\2^ \3\2\2\2_`\7.\2\2`\""+
		"\3\2\2\2ab\7`\2\2b$\3\2\2\2cd\t\3\2\2d&\3\2\2\2ef\t\4\2\2f(\3\2\2\2gi"+
		"\t\5\2\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\b\25\2\2"+
		"m*\3\2\2\2\t\2ADINUj\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}