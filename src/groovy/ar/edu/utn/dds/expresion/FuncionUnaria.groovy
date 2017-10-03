package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

import static java.lang.Math.sqrt

@JsonTypeName("fun")
class FuncionUnaria extends Funcion {
    private Expresion expresion

    @JsonCreator
    FuncionUnaria(@JsonProperty("nombre") String nombre, @JsonProperty("expresion") Expresion expresion) {
        super(nombre)
        this.expresion = expresion
    }

    Expresion getExpresion() {
        return expresion
    }

    @Override
    Double getValor() {
        switch (nombre) {
            case "sqrt":
                return sqrt(expresion.getValor())
            default:
                return null
        }
    }
}