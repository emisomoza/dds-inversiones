package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cue")
public class PrimariaCuenta extends PrimariaVariable {
    @JsonCreator
    public PrimariaCuenta(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }
}
