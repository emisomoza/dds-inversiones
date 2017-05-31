package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

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

    public Expresion getPrimerParametro() {
        return primerParametro;
    }

    public Expresion getSegundoParametro() {
        return segundoParametro;
    }

    @Override
    protected Double getValorSinSigno() {
        switch (nombre) {
            case "root":
                return Math.pow(segundoParametro.getValor(), 1.0/primerParametro.getValor());
            default:
                return null;
        }
    }
}
