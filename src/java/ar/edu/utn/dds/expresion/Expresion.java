package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.*;

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

	@JsonIgnore
	public List<Expresion> getChildren() {
		return children;
	}

	public void setChildren(List<Expresion> children) {
		this.children = children;
	}

	@JsonIgnore
	public abstract Double getValor();
}

