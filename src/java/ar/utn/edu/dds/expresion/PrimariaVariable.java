package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public class PrimariaVariable extends Primaria {
    private String name;

    public PrimariaVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getValue() {
        return 0D;
    }
}
