package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by andres on 29/05/17.
 */
@JsonTypeName("cue")
public class PrimariaCuenta extends PrimariaVariable {
    @JsonCreator
    public PrimariaCuenta(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }
}
