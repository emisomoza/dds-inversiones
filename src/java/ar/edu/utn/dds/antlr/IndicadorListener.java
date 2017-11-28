// Generated from /home/rodrigo/repository/dds-inversiones/src/antlr4/ar.edu.utn.antlr/Indicador.g4 by ANTLR 4.7
package ar.edu.utn.dds.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IndicadorParser}.
 */
public interface IndicadorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 */
	void enterIndicador(IndicadorParser.IndicadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 */
	void exitIndicador(IndicadorParser.IndicadorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additive_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_operation(IndicadorParser.Additive_operationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additive_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_operation(IndicadorParser.Additive_operationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code term}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTerm(IndicadorParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code term}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTerm(IndicadorParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicative_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_operation(IndicadorParser.Multiplicative_operationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicative_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_operation(IndicadorParser.Multiplicative_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#termino}.
	 * @param ctx the parse tree
	 */
	void enterTermino(IndicadorParser.TerminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#termino}.
	 * @param ctx the parse tree
	 */
	void exitTermino(IndicadorParser.TerminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(IndicadorParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(IndicadorParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#raise_operation}.
	 * @param ctx the parse tree
	 */
	void enterRaise_operation(IndicadorParser.Raise_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#raise_operation}.
	 * @param ctx the parse tree
	 */
	void exitRaise_operation(IndicadorParser.Raise_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#signed_termino}.
	 * @param ctx the parse tree
	 */
	void enterSigned_termino(IndicadorParser.Signed_terminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#signed_termino}.
	 * @param ctx the parse tree
	 */
	void exitSigned_termino(IndicadorParser.Signed_terminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#between_parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterBetween_parenthesis(IndicadorParser.Between_parenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#between_parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitBetween_parenthesis(IndicadorParser.Between_parenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(IndicadorParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(IndicadorParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#function_static}.
	 * @param ctx the parse tree
	 */
	void enterFunction_static(IndicadorParser.Function_staticContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#function_static}.
	 * @param ctx the parse tree
	 */
	void exitFunction_static(IndicadorParser.Function_staticContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#function_unary}.
	 * @param ctx the parse tree
	 */
	void enterFunction_unary(IndicadorParser.Function_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#function_unary}.
	 * @param ctx the parse tree
	 */
	void exitFunction_unary(IndicadorParser.Function_unaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#function_binary}.
	 * @param ctx the parse tree
	 */
	void enterFunction_binary(IndicadorParser.Function_binaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#function_binary}.
	 * @param ctx the parse tree
	 */
	void exitFunction_binary(IndicadorParser.Function_binaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(IndicadorParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(IndicadorParser.PrimaryContext ctx);
}