package ar.edu.utn.dds.expresion


import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonSubTypes([
    @JsonSubTypes.Type(PrimariaCuenta.class),
    @JsonSubTypes.Type(PrimariaIndicador.class)
])
abstract class PrimariaVariable extends Primaria {
    protected String nombre

    PrimariaVariable(String nombre) {
        this.nombre = nombre
    }

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    @Override
    abstract Double getValor()
}
