package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
    @JsonSubTypes.Type(PrimariaNumero.class),
    @JsonSubTypes.Type(PrimariaVariable.class)
})
public abstract class Primaria extends Expresion {

    @JsonProperty
    @Override
    public abstract Double getValor();
}
