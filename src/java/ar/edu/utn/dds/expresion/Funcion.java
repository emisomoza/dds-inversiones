package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.List;

@JsonSubTypes({
    @JsonSubTypes.Type(FuncionBinaria.class),
    @JsonSubTypes.Type(FuncionEstatica.class),
    @JsonSubTypes.Type(FuncionUnaria.class)
})
public abstract class Funcion extends Expresion {
    protected String nombre;

    public Funcion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected abstract Double getValorSinSigno();
}
