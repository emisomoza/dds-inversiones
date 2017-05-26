package ar.edu.utn.dds.expresion;

public class FuncionEstatica extends Funcion {
    public FuncionEstatica(String nombre) {
        super(nombre);
    }

    @Override
    protected Double getValor() {
        switch (nombre) {
            case "pi":
                return Math.PI;
            default: return null;
        }
    }
}
