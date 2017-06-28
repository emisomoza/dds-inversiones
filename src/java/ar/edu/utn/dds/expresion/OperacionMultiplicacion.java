package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("mult")
public class OperacionMultiplicacion extends Operacion {

	@JsonCreator
    public OperacionMultiplicacion(@JsonProperty("expresionIzquierda") Expresion expresionIzquierda, @JsonProperty("expresionDerecha") Expresion expresionDerecha) {
	    super(expresionIzquierda, expresionDerecha);
    }

	@Override
	public Double getValor() {
		return expresionIzquierda.getValor() * expresionDerecha.getValor();
	}
}