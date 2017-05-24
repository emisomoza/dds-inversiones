package ar.utn.edu.dds.expresion;

/**
 * Created by andres on 24/05/17.
 */
public abstract class Funcion extends Expresion {
    protected String name;

    public Funcion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public abstract Double getValue();
}
