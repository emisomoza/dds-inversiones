package ar.edu.utn.dds.expresion;

import ar.edu.utn.dds.ContenedorCalculablesEnUso;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("var")
public class PrimariaVariable extends Primaria {
    private String nombre;

    @JsonCreator
    public PrimariaVariable(@JsonProperty("nombre") String nombre) {
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
}
