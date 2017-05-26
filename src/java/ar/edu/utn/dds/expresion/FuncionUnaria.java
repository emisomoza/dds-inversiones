package ar.edu.utn.dds.expresion;

import static java.lang.Math.sqrt;

public class FuncionUnaria extends Funcion {
    private Expresion expresion;

    public FuncionUnaria(String nombre, Expresion expresion) {
        super(nombre);
        this.expresion = expresion;
    }

    @Override
    protected Double getValor() {
        switch (nombre) {
            case "sqrt":
                return sqrt(expresion.getResultado());
            default:
                return null;
        }
    }
}