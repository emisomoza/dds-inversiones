package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("fes")
class FuncionEstatica extends Funcion {
    public static final String PI = "pi"

    @JsonCreator
    FuncionEstatica(@JsonProperty("nombre") String nombre) {
        super(nombre)
    }
}
