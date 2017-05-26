package ar.edu.utn.dds.expresion;

public class OperacionResta extends Operacion {
    public OperacionResta(Expresion expresionIzquierda, Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    protected Double getValor() {
        return expresionIzquierda.getResultado() - expresionDerecha.getResultado();
    }
}