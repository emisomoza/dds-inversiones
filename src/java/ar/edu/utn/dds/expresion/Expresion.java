package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(Operacion.class),
    @JsonSubTypes.Type(Funcion.class),
    @JsonSubTypes.Type(Primaria.class)
})
public abstract class Expresion {
	protected List<Expresion> children;
    protected boolean negado = false;

	public List<Expresion> getChildren() {
		return children;
	}

	public void setChildren(List<Expresion> children) {
		this.children = children;
	}

	public boolean esNegado() {
        return negado;
    }

    public void setNegado(boolean negado) {
        this.negado = negado;
    }

	public Double getValor() {
		if(esNegado()) {
			return -1 * getValorSinSigno();
		}
		return getValorSinSigno();
	}

    protected abstract Double getValorSinSigno();
}

