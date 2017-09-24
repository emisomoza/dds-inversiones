package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("num")
class PrimariaNumero extends Primaria {
    private Double valor

    @JsonCreator
    PrimariaNumero(@JsonProperty("valor") Double valor) {
        this.valor = valor
    }

    @JsonProperty
    @Override
    Double getValor() {
        return valor
    }

    void setValor(Double valor) {
        this.valor = valor
    }
}
