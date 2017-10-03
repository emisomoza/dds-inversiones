package ar.edu.utn.dds.model

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador


class Metodologia implements Serializable {

    private List<OperadorFiltro> operadoresFiltro;
    private OperadorOrdenador operadorOrden;

    List<OperadorFiltro> getOperadoresFiltro() {
        return operadoresFiltro;
    }

    void setOperadoresFiltro(List<OperadorFiltro> operadoresFiltro) {
        this.operadoresFiltro = operadoresFiltro;
    }

    OperadorOrdenador getOperadoresOrden() {
        return operadorOrden;
    }

    void setOperadoresOrden(OperadorOrdenador operadoresOrden) {
        this.operadorOrden = operadoresOrden;
    }

    List<Empresa> filtrar(List<Empresa> empresas) {
        return empresas.findAll { this.filtrar(it) }
    }

    Boolean filtrar(Empresa empresa) {
        return this.operadoresFiltro.every { it.filtrar(empresa) }
    }

    List<Empresa> ordenar(List<Empresa> empresas) {
        return this.operadorOrden.ordenar(empresas);
    }
}