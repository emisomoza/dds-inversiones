package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonSubTypes([
    @JsonSubTypes.Type(FuncionBinaria.class),
    @JsonSubTypes.Type(FuncionEstatica.class),
    @JsonSubTypes.Type(FuncionUnaria.class)
])
abstract class Funcion extends Expresion {
    protected String nombre;

    Funcion(String nombre) {
        this.nombre = nombre
    }

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    @Override
    abstract Double getValor()
}
