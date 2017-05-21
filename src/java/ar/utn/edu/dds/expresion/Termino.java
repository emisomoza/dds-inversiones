package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 21/05/17.
 */
public class Termino implements IExpresion {

    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public Double getResultado() {
        return valor;
    }
}
