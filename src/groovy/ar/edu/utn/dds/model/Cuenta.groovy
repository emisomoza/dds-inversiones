package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.CuentaInvalidoException
import ar.edu.utn.dds.exceptions.ElementoInvalidoException


class Cuenta implements Serializable {

    private Long empresa
    private Long periodo
    private TipoCuenta tipo
    private Double valor

    Cuenta() {

    }

    Cuenta (String nombre, Double valor) {
        this.setTipo(new TipoCuenta())
        this.getTipo().setDescripcion(nombre)
        this.setValor(valor)
    }

    Cuenta(Long empresa, Long periodo, String nombre, Double valor) {
        this(nombre, valor)
        this.empresa = empresa
        this.periodo = periodo
    }

    Cuenta(Long empresa, Long periodo, TipoCuenta tipoCuenta, Double valor) {
        this.tipo = tipoCuenta
        this.valor = valor
        this.empresa = empresa
        this.periodo = periodo
    }

    String getNombre() {
        return tipo.getDescripcion()
    }

    Double getValor() {
        return valor
    }

    void setValor(Double valor) {
        this.valor = valor
    }

    Long getEmpresa() {
        return empresa
    }

    void setEmpresa(Long empresa) {
        this.empresa = empresa
    }

    Long getPeriodo() {
        return periodo
    }

    void setPeriodo(Long periodo) {
        this.periodo = periodo
    }

    TipoCuenta getTipo() {
        return tipo
    }

    void setTipo(TipoCuenta tipo) {
        this.tipo = tipo
    }

    void validarConsistencia() {
        if(this.getEmpresa() == null)
            throw new CuentaInvalidoException("La cuenta debe tener una empresa asociada")

        if(this.getPeriodo() == null)
            throw new CuentaInvalidoException("La cuenta debe tener un periodo asociado")

        if(this.getValor() == null)
            throw new CuentaInvalidoException("La cuenta debe tener un valor")

        if(this.getTipo() == null)
            throw new CuentaInvalidoException("La cuenta debe tener un tipo asociado")

        try {
            this.getTipo().validarConsistencia()
        } catch (ElementoInvalidoException e) {
            throw new CuentaInvalidoException(e.getMessage(), e)
        } catch (RuntimeException e) {
            throw new CuentaInvalidoException("Ocurrio un problema validando la cuenta", e)
        }
    }
}
