package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(Operacion.class),
    @JsonSubTypes.Type(Funcion.class),
    @JsonSubTypes.Type(Primaria.class)
})
public abstract class Expresion {
    protected boolean negado = false;

    public Double getResultado() {
        if(esNegado()) {
            return -1 * getValor();
        }
        return getValor();
    }

    public boolean esNegado() {
        return negado;
    }

    public void setNegado(boolean negado) {
        this.negado = negado;
    }

    protected abstract Double getValor();
}

