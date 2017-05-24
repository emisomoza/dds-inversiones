package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class FuncionEstatica extends Funcion {
    public FuncionEstatica(String name) {
        super(name);
    }

    @Override
    public Double getValue() {
        switch (name) {
            case "pi":
                return Math.PI;
            default: return null;
        }
    }
}
