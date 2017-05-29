package ar.edu.utn.dds;

import ar.edu.utn.dds.expresion.Expresion;
import ar.edu.utn.dds.expresion.PrimariaVariable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Indicador implements Calculable {

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

    public Double getValor() {
    	cargarCalculables();
        return expresion.getValor();
    }

    private void cargarCalculables() {
    	List<String> nombres = obtenerNombresDeCalculables(expresion);


    }

	public List<String> obtenerNombresDeCalculables(Expresion expresion) {
		List<String> nombres = new ArrayList<String>();

		if(expresion.getChildren().isEmpty() && expresion instanceof Calculable) {
			Calculable calculable = (Calculable) expresion;
			nombres.add(calculable.getNombre());
		} else {
			for(Expresion unaExpresion : expresion.getChildren()) {
				nombres.addAll(obtenerNombresDeCalculables(unaExpresion));
			}
		}

		return nombres;
	}
}
