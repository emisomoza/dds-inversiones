package ar.edu.utn.dds.expresion;

public class OperacionPotencia extends Operacion {
    public OperacionPotencia(Expresion expresionIzquierda, Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    public Double getValor() {
        return Math.pow(expresionIzquierda.getResultado(), expresionDerecha.getResultado());
    }
}