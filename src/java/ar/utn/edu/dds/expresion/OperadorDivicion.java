package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 21/05/17.
 */
public class OperadorDivicion implements IOperador {

    @Override
    public String getSimbolo() {
        return "/";
    }

    @Override
    public Double operar(IExpresion lExpresion, IExpresion rExpresion) {
        return lExpresion.getResultado() / rExpresion.getResultado();
    }

}
