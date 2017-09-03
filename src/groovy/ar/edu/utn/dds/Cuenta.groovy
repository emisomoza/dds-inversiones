package ar.edu.utn.dds

class Cuenta implements Calculable {

    private Long id
    private String nombre
    private Double valor

    Cuenta() {

    }

    Cuenta (String nombre, Double valor) {
        this.setNombre(nombre)
        this.setValor(valor)
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
