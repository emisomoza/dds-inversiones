package ar.edu.utn.dds.expresion;

import ar.edu.utn.dds.utilidades.mapper.Mapeable;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
    @JsonSubTypes.Type(PrimariaNumero.class),
    @JsonSubTypes.Type(PrimariaVariable.class)
})
public abstract class Primaria extends Expresion implements Mapeable<String, String> {

    @Override
    public abstract Double getValor();
}
