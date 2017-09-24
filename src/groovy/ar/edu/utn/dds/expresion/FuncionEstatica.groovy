package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("fes")
class FuncionEstatica extends Funcion {

    @JsonCreator
    FuncionEstatica(@JsonProperty("nombre") String nombre) {
        super(nombre)
    }

    @Override
    Double getValor() {
        switch (nombre) {
            case "pi":
                return Math.PI
            default: return null
        }
    }
}
