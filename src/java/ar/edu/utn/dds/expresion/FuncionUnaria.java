package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Math.sqrt;

@JsonTypeName("fun")
public class FuncionUnaria extends Funcion {
    private Expresion expresion;

    @JsonCreator
    public FuncionUnaria(@JsonProperty("nombre") String nombre, @JsonProperty("expresion") Expresion expresion) {
        super(nombre);
        this.expresion = expresion;
    }

    @Override
    protected Double getValor() {
        switch (nombre) {
            case "sqrt":
                return sqrt(expresion.getResultado());
            default:
                return null;
        }
    }
}