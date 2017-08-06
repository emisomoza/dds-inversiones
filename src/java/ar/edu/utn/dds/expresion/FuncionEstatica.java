package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("fes")
public class FuncionEstatica extends Funcion {

    @JsonCreator
    public FuncionEstatica(@JsonProperty("nombre") String nombre) {
        super(nombre);
    }

    @Override
    public Double getValor() {
        switch (nombre) {
            case "pi":
                return Math.PI;
            default: return null;
        }
    }
}
