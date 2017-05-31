package ar.edu.utn.dds.visitor;

import ar.edu.utn.dds.antlr.IndicadorBaseVisitor;
import ar.edu.utn.dds.antlr.IndicadorParser;
import ar.edu.utn.dds.expresion.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.HashMap;

public class IndicadorCustomVisitor extends IndicadorBaseVisitor {
	@Override
	public Expresion visit(ParseTree tree) {
		return (Expresion) super.visit(tree);
	}

	@Override
	public Expresion visitChildren(RuleNode node) {
		return (Expresion) super.visitChildren(node);
	}

	@Override
	public Expresion visitSigned_termino(IndicadorParser.Signed_terminoContext ctx) {
		Expresion expresion;
		if(ctx.ADDITIVE_OP().getText().equals("-")) {
			expresion = visitChildren(ctx);
			expresion.setNegado(true);
		} else {
			expresion = visitChildren(ctx);
		}
		return expresion;
	}

	@Override
	public Expresion visitBetween_parenthesis(IndicadorParser.Between_parenthesisContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public OperacionPotencia visitRaise_operation(IndicadorParser.Raise_operationContext ctx) {
		Expresion expresionIzquierda = visit(ctx.getChild(0));
		Expresion expresionDerecha = visit(ctx.getChild(2));
		return new OperacionPotencia(expresionIzquierda, expresionDerecha);
	}

	@Override
	public Operacion visitAdditive_operation(IndicadorParser.Additive_operationContext ctx) {
		Expresion expresionIzquierda = visit(ctx.expression(0));
		Expresion expresionDerecha = visit(ctx.expression(1));

		if(ctx.ADDITIVE_OP().getText().equals("+")) {
			return new OperacionSuma(expresionIzquierda, expresionDerecha);
		} else {
			return new OperacionResta(expresionIzquierda, expresionDerecha);
		}
	}

	@Override
	public Operacion visitMultiplicative_operation(IndicadorParser.Multiplicative_operationContext ctx) {
		Expresion expresionIzquierda = visit(ctx.expression(0));
		Expresion expresionDerecha = visit(ctx.expression(1));

		if(ctx.MULTIPLICATIVE_OP().getText().equals("*")) {
			return new OperacionMultiplicacion(expresionIzquierda, expresionDerecha);
		} else {
			return new OperacionDivision(expresionIzquierda, expresionDerecha);
		}
	}

	@Override
	public Primaria visitPrimary(IndicadorParser.PrimaryContext ctx) {
		if(ctx.IND() != null) {
			return new PrimariaIndicador(ctx.VAR().getText());
		} else {
			if(ctx.CUE() != null) {
				return new PrimariaCuenta(ctx.VAR().getText());
			} else {
				Double valor = new Double(ctx.DOUBLE().getText());
				return new PrimariaNumero(valor);
			}
		}
	}

	@Override
	public FuncionEstatica visitFunction_static(IndicadorParser.Function_staticContext ctx) {
		String nombreFuncion = ctx.getStart().getText();

		return new FuncionEstatica(nombreFuncion);
	}

	@Override
	public FuncionUnaria visitFunction_unary(IndicadorParser.Function_unaryContext ctx) {
		String nombreFuncion = ctx.getStart().getText();
		Expresion parametro = visit(ctx.expression());

		return new FuncionUnaria(nombreFuncion, parametro);
	}

	@Override
	public FuncionBinaria visitFunction_binary(IndicadorParser.Function_binaryContext ctx) {
		Expresion primerParametro = visit(ctx.expression(0));
		Expresion segundoParametro = visit(ctx.expression(1));

		String fuctionName = ctx.getStart().getText();

		return new FuncionBinaria(fuctionName, primerParametro, segundoParametro);
	}
}
