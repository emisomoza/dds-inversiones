package ar.edu.utn.dds;

import ar.edu.utn.dds.expresion.Expresion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andres on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Indicador {

    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("expresion")
    private Expresion expresion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Double aplicar() {
        return expresion.getResultado();
    }
}
