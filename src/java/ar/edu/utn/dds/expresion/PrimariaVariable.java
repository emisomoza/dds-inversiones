package ar.edu.utn.dds.expresion;

import ar.edu.utn.dds.ContenedorCalculables;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(PrimariaCuenta.class),
        @JsonSubTypes.Type(PrimariaIndicador.class)
})
public abstract class PrimariaVariable extends Primaria {
    protected String nombre;

    public PrimariaVariable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Double getValor() {
        ContenedorCalculables contenedor = ContenedorCalculables.getInstance();
        return contenedor.getCalculablePorNombre(nombre).getValor();
    }
}
