package ar.edu.utn.dds.model


class Cuenta implements Serializable {

    private Long id
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

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    public String getNombre() {
        return tipo.getDescripcion()
    }

    public Double getValor() {
        return valor
    }

    public void setValor(Double valor) {
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
}
