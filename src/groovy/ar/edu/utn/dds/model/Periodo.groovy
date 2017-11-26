package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.PeriodoInvalidoException
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer

import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
class Periodo implements Serializable {

    private Long id
    private List<Cuenta> cuentas

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaInicio

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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