package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.*;

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
	@JsonProperty("negado")
    protected boolean negado = false;

	@JsonIgnore
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

	public boolean isNegado() {
		return negado;
	}

	public Double getValor() {
		if(esNegado()) {
			return -1 * getValorSinSigno();
		}
		return getValorSinSigno();
	}

	@JsonIgnore
    protected abstract Double getValorSinSigno();
}

