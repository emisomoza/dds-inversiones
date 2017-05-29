// Generated from /home/andres/dev/utn/dds/dds-inversiones/src/antlr4/ar.edu.utn.antlr/Indicador.g4 by ANTLR 4.7
package ar.edu.utn.dds.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IndicadorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IndicadorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code additive_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_operation(IndicadorParser.Additive_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code term}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(IndicadorParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicative_operation}
	 * labeled alternative in {@link IndicadorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_operation(IndicadorParser.Multiplicative_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#between_parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_parenthesis(IndicadorParser.Between_parenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#signed_termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigned_termino(IndicadorParser.Signed_terminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermino(IndicadorParser.TerminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#terminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminal(IndicadorParser.TerminalContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#raise_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_operation(IndicadorParser.Raise_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(IndicadorParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#function_static}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_static(IndicadorParser.Function_staticContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#function_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_unary(IndicadorParser.Function_unaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#function_binary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_binary(IndicadorParser.Function_binaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link IndicadorParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(IndicadorParser.PrimaryContext ctx);
}