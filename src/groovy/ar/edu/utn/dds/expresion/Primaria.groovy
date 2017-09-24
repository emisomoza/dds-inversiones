package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonSubTypes([
    @JsonSubTypes.Type(PrimariaNumero.class),
    @JsonSubTypes.Type(PrimariaVariable.class)
])
abstract class Primaria extends Expresion {


    @JsonIgnore(false)
    @Override
    abstract Double getValor()
}
