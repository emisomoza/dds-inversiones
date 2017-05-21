package ar.utn.edu.dds.expresion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by andres on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OperadorSuma.class, name = "sum"),
    @JsonSubTypes.Type(value = OperadorResta.class, name = "res"),
    @JsonSubTypes.Type(value = OperadorMultiplicacion.class, name = "mul"),
    @JsonSubTypes.Type(value = OperadorDivicion.class, name = "div")
})
public interface IOperador {

    String getSimbolo();

    Double operar(IExpresion lExpresion, IExpresion rExpresion);
}
