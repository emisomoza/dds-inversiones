package ar.edu.utn.dds.model

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador


class Metodologia implements Serializable {
    private String nombre
    private List<OperadorFiltro> operadoresFiltro
    private OperadorOrdenador operadorOrden

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    List<OperadorFiltro> getOperadoresFiltro() {
        return operadoresFiltro;
    }

    void setOperadoresFiltro(List<OperadorFiltro> operadoresFiltro) {
        this.operadoresFiltro = operadoresFiltro;
    }

    OperadorOrdenador getOperadorOrden() {
        return operadorOrden
    }

    void setOperadorOrden(OperadorOrdenador operadorOrden) {
        this.operadorOrden = operadorOrden
    }

    List<Empresa> filtrar(List<Empresa> empresas, IndicadorService indicadorService) {
        return empresas.findAll { this.filtrar(it, indicadorService) }
    }

    Boolean filtrar(Empresa empresa, IndicadorService indicadorService) {
        return this.operadoresFiltro.every { it.filtrar(empresa, indicadorService) }
    }

    List<Empresa> ordenar(List<Empresa> empresas, IndicadorService indicadorService) {
        return this.operadorOrden.ordenar(empresas);
    }
}