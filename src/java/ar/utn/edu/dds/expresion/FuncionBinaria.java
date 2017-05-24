package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class FuncionBinaria extends Funcion {
    private Expresion firstParamExpression;
    private Expresion secondParamExpression;

    public FuncionBinaria(String name, Expresion firstParamExpression, Expresion secondParamExpression) {
        super(name);
        this.firstParamExpression = firstParamExpression;
        this.secondParamExpression = secondParamExpression;
    }

    @Override
    public Double getValue() {
        switch (name) {
            case "root":
                return Math.pow(secondParamExpression.getValue(), 1.0/firstParamExpression.getValue());
            default:
                return null;
        }
    }
}
