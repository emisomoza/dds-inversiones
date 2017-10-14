package ar.edu.utn.dds.model

class TipoCuenta {

    private Long id
    private String descripcion

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getDescripcion() {
        return descripcion
    }

    void setDescripcion(String descripcion) {
        this.descripcion = descripcion
    }
}
