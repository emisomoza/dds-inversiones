package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pot")
public class OperacionPotencia extends Operacion {

    @JsonCreator
    public OperacionPotencia(@JsonProperty("expresionIzquierda") Expresion expresionIzquierda, @JsonProperty("expresionDerecha") Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha);
    }

    @Override
    protected Double getValorSinSigno() {
        return Math.pow(expresionIzquierda.getValor(), expresionDerecha.getValor());
    }
}