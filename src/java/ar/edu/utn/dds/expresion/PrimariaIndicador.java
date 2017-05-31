package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by andres on 29/05/17.
 */
@JsonTypeName("ind")
public class PrimariaIndicador extends PrimariaVariable {
    @JsonCreator
    public PrimariaIndicador(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }

    @JsonIgnore
    @Override
    public String getKey() {
        return "ind";
    }
}
