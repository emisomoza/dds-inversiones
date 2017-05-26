package ar.edu.utn.dds.expresion;

public class OperacionSuma extends Operacion {
    public OperacionSuma(Expresion expresionIzquierda, Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    protected Double getValor() {
        return expresionIzquierda.getResultado() + expresionDerecha.getResultado();
    }
}