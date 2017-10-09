package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("fbi")
class FuncionBinaria extends Funcion {
    public static final String ROOT = "root"

    private Expresion primerParametro
    private Expresion segundoParametro

    @JsonCreator
    FuncionBinaria(@JsonProperty("nombre") String nombre, @JsonProperty("primerParametro") Expresion primerParametro, @JsonProperty("segundoParametro") Expresion segundoParametro) {
        super(nombre);
        this.primerParametro = primerParametro
        this.segundoParametro = segundoParametro
    }

    Expresion getPrimerParametro() {
        return primerParametro
    }

    Expresion getSegundoParametro() {
        return segundoParametro
    }
}