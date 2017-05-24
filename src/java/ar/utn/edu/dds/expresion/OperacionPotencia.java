package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class OperacionPotencia extends Opearcion {
    public OperacionPotencia(Expresion leftExpression, Expresion rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Double getValue() {
        return Math.pow(leftExpression.getValue(), rightExpression.getValue());
    }
}