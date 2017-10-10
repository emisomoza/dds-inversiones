package ar.edu.utn.dds.expresion

import ar.edu.utn.dds.model.ContenedorCalculables
import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonSubTypes([
    @JsonSubTypes.Type(PrimariaCuenta.class),
    @JsonSubTypes.Type(PrimariaIndicador.class)
])
abstract class PrimariaVariable extends Primaria {
    protected String nombre

    PrimariaVariable(String nombre) {
        this.nombre = nombre
    }

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    @Override
    Double getValor() {
        ContenedorCalculables contenedor = ContenedorCalculables.getInstance()
        return contenedor.getCalculablePorNombre(nombre).getValor()
    }
}