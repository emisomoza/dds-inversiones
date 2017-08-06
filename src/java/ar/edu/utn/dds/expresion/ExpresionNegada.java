package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("negacion")
public class ExpresionNegada extends Expresion {
	Expresion expresion;

	@JsonCreator
	public ExpresionNegada(@JsonProperty("expresion") Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public Double getValor() {
		return -1 * expresion.getValor();
	}
}
