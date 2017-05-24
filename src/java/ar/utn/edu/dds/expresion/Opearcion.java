package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public abstract class Opearcion extends Expresion {
    protected Expresion leftExpression;
    protected Expresion rightExpression;

    Opearcion(Expresion leftExpression, Expresion rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public abstract Double getValue();
}
