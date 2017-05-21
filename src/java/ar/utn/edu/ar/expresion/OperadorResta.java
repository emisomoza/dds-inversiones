package ar.utn.edu.ar.expresion;

/**
 * Created by andres on 21/05/17.
 */
public class OperadorResta implements IOperador {

    @Override
    public String getSimbolo() {
        return "-";
    }

    @Override
    public Double operar(IExpresion lExpresion, IExpresion rExpresion) {
        return lExpresion.getResultado() - rExpresion.getResultado();
    }

}
