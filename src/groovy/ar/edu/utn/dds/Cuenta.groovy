package ar.edu.utn.dds

public class Cuenta implements Calculable {

    private String nombre;
    private Double valor;

    public Cuenta (String nombre, Double valor) {
        this.setNombre(nombre);
        this.setValor(valor);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
