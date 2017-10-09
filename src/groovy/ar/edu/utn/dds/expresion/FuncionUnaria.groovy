package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

import static java.lang.Math.sqrt

@JsonTypeName("fun")
class FuncionUnaria extends Funcion {
    public static final String SQRT = "sqrt"

    private Expresion expresion

    @JsonCreator
    FuncionUnaria(@JsonProperty("nombre") String nombre, @JsonProperty("expresion") Expresion expresion) {
        super(nombre)
        this.expresion = expresion
    }

    Expresion getExpresion() {
        return expresion
    }
}