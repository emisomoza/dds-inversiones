package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.PeriodoInvalidoException

import java.time.LocalDate

class Periodo implements Serializable {

    private Long id
    private List<Cuenta> cuentas
    private LocalDate fechaInicio
    private LocalDate fechaFin

    Periodo(){}

    Periodo(LocalDate fechaComienzo, LocalDate fechaFin) {
        this.fechaInicio = fechaComienzo
        this.fechaFin = fechaFin
        this.cuentas = new ArrayList<Cuenta>()
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    List<Cuenta> getCuentas() {
        return cuentas
    }

    void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas
    }

    void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio
    }

    LocalDate getFechaInicio() {
        return fechaInicio
    }

    void getFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio
    }

    LocalDate getFechaFin() {
        return fechaFin
    }

    void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin
    }

    void validarConsistencia() {
        if(this.fechaInicio == null || this.fechaFin == null)
            throw new PeriodoInvalidoException("El periodo debe tener fecha de inicio y fecha de fin")
    }
}