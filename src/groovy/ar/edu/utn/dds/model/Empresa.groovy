package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.EmpresaInvalidoException

class Empresa implements Serializable {

    private Long id
    private String nombre
    private List<Periodo> periodos

    Empresa() {
    }

    Empresa(String nombre) {
        this.nombre = nombre
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    List<Periodo> getPeriodos() {
        return periodos
    }

    void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos
    }

    void validarConsistencia() {
        if(this.getNombre() == null || this.getNombre().size() == 0)
            throw new EmpresaInvalidoException("La empresa debe tener nombre")
    }
}
