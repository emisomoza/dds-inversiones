package ar.edu.utn.dds.expresion;

import ar.edu.utn.dds.ContenedorCalculablesEnUso;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(PrimariaCuenta.class),
        @JsonSubTypes.Type(PrimariaIndicador.class)
})
public abstract class PrimariaVariable extends Primaria {
    private String nombre;

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
	    ContenedorCalculablesEnUso contenedor = ContenedorCalculablesEnUso.getInstance();
        return contenedor.getCalculable(nombre).getValor();
    }

    @Override
    public String getValue() {
        return this.getNombre();
    }
}
