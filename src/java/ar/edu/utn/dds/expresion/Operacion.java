package ar.edu.utn.dds.expresion;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
    @JsonSubTypes.Type(OperacionSuma.class),
    @JsonSubTypes.Type(OperacionResta.class),
    @JsonSubTypes.Type(OperacionMultiplicacion.class),
    @JsonSubTypes.Type(OperacionDivision.class),
    @JsonSubTypes.Type(OperacionPotencia.class)
})
public abstract class Operacion extends Expresion {

    protected Expresion expresionIzquierda;
    protected Expresion expresionDerecha;

    Operacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = expresionIzquierda;
        this.expresionDerecha = expresionDerecha;
    }

    @Override
    protected abstract Double getValor();
}
