package ar.edu.utn.dds.model

class Empresa implements Serializable {

    private Long id
    private String nombre
    private List<Periodo> periodos

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
}
