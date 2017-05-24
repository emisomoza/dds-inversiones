package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class OperacionResta extends Opearcion {
    public OperacionResta(Expresion leftExpression, Expresion rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Double getValue() {
        return leftExpression.getValue() - rightExpression.getValue();
    }
}
