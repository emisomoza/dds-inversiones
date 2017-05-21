package ar.utn.edu.ar.expresion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andres on 21/05/17.
 */
public class ExpresionCompuesta implements IExpresion {

    @JsonProperty("lExpresion")
    private IExpresion lExpresion;
    @JsonProperty("rExpresion")
    private IExpresion rExpresion;
    @JsonProperty("operador")
    private IOperador operador;

    @Override
    public Double getResultado() {
        return operador.operar(lExpresion, rExpresion);
    }
}
