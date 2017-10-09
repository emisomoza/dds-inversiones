package ar.edu.utn.dds.expresion

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes([
    @JsonSubTypes.Type(Operacion.class),
    @JsonSubTypes.Type(Funcion.class),
    @JsonSubTypes.Type(Primaria.class),
    @JsonSubTypes.Type(ExpresionNegada.class)
])
abstract class Expresion {
}
