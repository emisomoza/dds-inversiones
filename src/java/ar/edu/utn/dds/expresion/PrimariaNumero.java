package ar.edu.utn.dds.expresion;

public class PrimariaNumero extends Primaria {
    private Double valor;

    public PrimariaNumero(Double valor) {
        this.valor = valor;
    }

    @Override
    protected Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}