package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class OpearcionMultiplicacion extends Opearcion {
    public OpearcionMultiplicacion(Expresion leftExpression, Expresion rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Double getValue() {
        return leftExpression.getValue() * rightExpression.getValue();
    }
}
