package ar.edu.utn.dds.expresion;

import ar.edu.utn.dds.ContenedorCalculablesEnUso;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    protected Double getValorSinSigno() {
	    ContenedorCalculablesEnUso contenedor = ContenedorCalculablesEnUso.getInstance();
        return contenedor.getCalculable(nombre).getValor();
    }

    @JsonIgnore
    @Override
    public String getValue() {
        return this.getNombre();
    }
}
