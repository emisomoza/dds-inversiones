package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ind")
public class PrimariaIndicador extends PrimariaVariable {
    @JsonCreator
    public PrimariaIndicador(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }

    @Override
    public String getKey() {
        return "ind";
    }
}
