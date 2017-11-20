package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.TipoCuenaInvalidoException

class TipoCuenta implements Serializable {

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

    void validarConsistencia() {
        if(this.getDescripcion() == null || this.getDescripcion().size() == 0)
            throw new TipoCuenaInvalidoException("El tipo de cuenta debe tener descripcion")
    }
}
