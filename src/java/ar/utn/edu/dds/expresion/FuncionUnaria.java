package ar.utn.edu.dds.expresion;

import static java.lang.Math.sqrt;

/**
 * Created by andres on 24/05/17.
 */
public class FuncionUnaria extends Funcion {
    private Expresion expression;

    public FuncionUnaria(String name, Expresion expression) {
        super(name);
        this.expression = expression;
    }

    @Override
    public Double getValue() {
        switch (name) {
            case "sqrt":
                return sqrt(expression.getValue());
            default:
                return null;
        }
    }
}