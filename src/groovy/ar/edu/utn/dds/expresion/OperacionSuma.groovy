package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("suma")
class OperacionSuma extends Operacion {

    @JsonCreator
    OperacionSuma(@JsonProperty("expresionIzquierda") Expresion expresionIzquierda, @JsonProperty("expresionDerecha") Expresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha)
    }

    @Override
    Double getValor() {
        return expresionIzquierda.getValor() + expresionDerecha.getValor()
    }
}
