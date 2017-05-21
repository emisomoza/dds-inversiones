package ar.utn.edu.dds;

import ar.utn.edu.dds.expresion.IExpresion;
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
    private IExpresion expresion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public IExpresion getExpresion() {
        return expresion;
    }

    public void setExpresion(IExpresion expresion) {
        this.expresion = expresion;
    }

    public Double aplicar() {
        return expresion.getResultado();
    }
}
