package ar.edu.utn.dds.expresion;

public class OperacionDivision extends Operacion {
    public OperacionDivision(Expresion expresionIzquierda, Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    protected Double getValor() {
        return expresionIzquierda.getResultado() / expresionDerecha.getResultado();
    }
}
