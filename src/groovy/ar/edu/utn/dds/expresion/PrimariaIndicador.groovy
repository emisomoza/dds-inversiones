package ar.edu.utn.dds.expresion

import ar.edu.utn.dds.model.Indicador
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("ind")
class PrimariaIndicador extends PrimariaVariable {
    private Indicador indicador

    @JsonCreator
    PrimariaIndicador(@JsonProperty("nombre") String nombre) {
        super(nombre)
    }

    Indicador getIndicador() {
        return indicador
    }

    void setIndicador(Indicador indicador) {
        this.indicador = indicador
    }

    @Override
    Double getValor() {
        return null
    }
}