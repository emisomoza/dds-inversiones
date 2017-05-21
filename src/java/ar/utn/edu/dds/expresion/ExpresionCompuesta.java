package ar.utn.edu.dds.expresion;

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

    public IExpresion getlExpresion() {
        return lExpresion;
    }

    public void setlExpresion(IExpresion lExpresion) {
        this.lExpresion = lExpresion;
    }

    public IExpresion getrExpresion() {
        return rExpresion;
    }

    public void setrExpresion(IExpresion rExpresion) {
        this.rExpresion = rExpresion;
    }

    public IOperador getOperador() {
        return operador;
    }

    public void setOperador(IOperador operador) {
        this.operador = operador;
    }

    @Override
    public Double getResultado() {
        return operador.operar(lExpresion, rExpresion);
    }
}
