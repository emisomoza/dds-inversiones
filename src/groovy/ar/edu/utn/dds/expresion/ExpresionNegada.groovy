package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("negacion")
class ExpresionNegada extends Expresion {
    Expresion expresion

    @JsonCreator
    ExpresionNegada(@JsonProperty("expresion") Expresion expresion) {
        this.expresion = expresion
    }
}
