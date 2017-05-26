package ar.edu.utn.dds.expresion;

public abstract class Expresion {
    protected boolean negado = false;

    public Double getResultado() {
        if(esNegado()) {
            return -1 * getValor();
        }
        return getValor();
    }

    public boolean esNegado() {
        return negado;
    }

    public void setNegado(boolean negado) {
        this.negado = negado;
    }

    protected abstract Double getValor();
}

