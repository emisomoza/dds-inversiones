package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("cue")
class PrimariaCuenta extends PrimariaVariable {
    @JsonCreator
    PrimariaCuenta(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }
}