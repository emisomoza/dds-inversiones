package ar.edu.utn.dds

/**
 * Created by esomoza on 5/13/17.
 */
public class Cuenta {

    private String nombre;
    private float valor;

    public Cuenta (String nombre, float valor) {
        this.setNombre(nombre);
        this.setValor(valor);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
