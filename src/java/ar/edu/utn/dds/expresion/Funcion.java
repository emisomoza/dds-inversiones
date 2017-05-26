package ar.edu.utn.dds.expresion;

public abstract class Funcion extends Expresion {
    protected String nombre;

    public Funcion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected abstract Double getValor();
}
