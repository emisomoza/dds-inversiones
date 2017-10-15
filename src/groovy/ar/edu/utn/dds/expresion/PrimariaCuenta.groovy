package ar.edu.utn.dds.expresion

import ar.edu.utn.dds.model.Cuenta
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("cue")
class PrimariaCuenta extends PrimariaVariable {
    private Cuenta cuenta;

    @JsonCreator
    PrimariaCuenta(@JsonProperty("nombre") String nombre) {
        super(nombre)
    }

    Cuenta getCuenta() {
        return cuenta
    }

    void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta
    }

    @Override
    Double getValor() {
        return cuenta.getValor()
    }
}