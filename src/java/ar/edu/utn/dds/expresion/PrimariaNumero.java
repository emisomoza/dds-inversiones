package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("num")
public class PrimariaNumero extends Primaria {
    private Double valor;

    @JsonCreator
    public PrimariaNumero(@JsonProperty("valor") Double valor) {
        this.valor = valor;
    }

    @Override
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}