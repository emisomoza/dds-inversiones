package ar.edu.utn.dds.expresion;

public class OperacionMultiplicacion extends Operacion {
    public OperacionMultiplicacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
	    super(expresionIzquierda, expresionDerecha);
    }

	@Override
	protected Double getValor() {
		return expresionIzquierda.getResultado() * expresionDerecha.getResultado();
	}
}