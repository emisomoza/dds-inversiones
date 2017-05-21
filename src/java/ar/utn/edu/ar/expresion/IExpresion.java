package ar.utn.edu.ar.expresion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by andres on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ExpresionCompuesta.class, name = "exp"),
    @JsonSubTypes.Type(value = Termino.class, name = "ter")
})
public interface IExpresion {

    Double getResultado();
}
