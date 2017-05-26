package ar.edu.utn.dds.expresion;

public class PrimariaVariable extends Primaria {
    private String nombre;

    public PrimariaVariable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected Double getValor() {
        return 0D;
    }
}
