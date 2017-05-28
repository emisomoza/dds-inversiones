package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("fbi")
public class FuncionBinaria extends Funcion {
    private Expresion primerParametro;
    private Expresion segundoParametro;

    @JsonCreator
    public FuncionBinaria(@JsonProperty("nombre") String nombre, @JsonProperty("primerParametro") Expresion primerParametro, @JsonProperty("segundoParametro") Expresion segundoParametro) {
        super(nombre);
        this.primerParametro = primerParametro;
        this.segundoParametro = segundoParametro;
    }

    @Override
    protected Double getValor() {
        switch (nombre) {
            case "root":
                return Math.pow(segundoParametro.getResultado(), 1.0/primerParametro.getResultado());
            default:
                return null;
        }
    }
}
