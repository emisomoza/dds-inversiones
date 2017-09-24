package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("fbi")
class FuncionBinaria extends Funcion {
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

    @Override
    Double getValor() {
        switch (nombre) {
            case "root":
                return Math.pow(segundoParametro.getValor(), 1.0 / primerParametro.getValor())
            default:
                return null
        }
    }
}