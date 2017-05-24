package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class PrimariaNumero extends Primaria {
    private Double value;

    public PrimariaNumero(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
