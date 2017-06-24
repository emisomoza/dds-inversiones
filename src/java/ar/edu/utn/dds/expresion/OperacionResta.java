package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("resta")
public class OperacionResta extends Operacion {

    @JsonCreator
    public OperacionResta(@JsonProperty("expresionIzquierda") Expresion expresionIzquierda, @JsonProperty("expresionDerecha") Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    public Double getValor() {
        return expresionIzquierda.getValor() - expresionDerecha.getValor();
    }
}