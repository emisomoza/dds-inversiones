package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("pot")
class OperacionPotencia extends Operacion {

    @JsonCreator
    OperacionPotencia(@JsonProperty("expresionIzquierda") Expresion expresionIzquierda, @JsonProperty("expresionDerecha") Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha)
    }
}