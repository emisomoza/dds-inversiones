package ar.edu.utn.dds.expresion;

public abstract class Operacion extends Expresion {
    protected Expresion expresionIzquierda;
    protected Expresion expresionDerecha;

    Operacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = expresionIzquierda;
        this.expresionDerecha = expresionDerecha;
    }

    @Override
    protected abstract Double getValor();
}
