package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonIgnoreProperties(value = ["valor"])
@JsonSubTypes([
    @JsonSubTypes.Type(OperacionSuma.class),
    @JsonSubTypes.Type(OperacionResta.class),
    @JsonSubTypes.Type(OperacionMultiplicacion.class),
    @JsonSubTypes.Type(OperacionDivision.class),
    @JsonSubTypes.Type(OperacionPotencia.class)
])
abstract class Operacion extends Expresion {

    protected Expresion expresionIzquierda
    protected Expresion expresionDerecha

    Operacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = expresionIzquierda
        this.expresionDerecha = expresionDerecha
    }

    Expresion getExpresionIzquierda() {
        return expresionIzquierda
    }

    Expresion getExpresionDerecha() {
        return expresionDerecha
    }

    @Override
    abstract Double getValor()
}
