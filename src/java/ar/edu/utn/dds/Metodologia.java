package ar.edu.utn.dds;

import ar.edu.utn.dds.metodologia.Modificador;
import ar.edu.utn.dds.metodologia.Operador;
import ar.edu.utn.dds.metodologia.OperadorFiltro;
import ar.edu.utn.dds.metodologia.OperadorOrdenador;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andres on 06/08/17.
 */
public class Metodologia {

    private List<OperadorFiltro> operadoresFiltro;
    private OperadorOrdenador operadorOrden;

    public List<OperadorFiltro> getOperadoresFiltro() {
        return operadoresFiltro;
    }

    public void setOperadoresFiltro(List<OperadorFiltro> operadoresFiltro) {
        this.operadoresFiltro = operadoresFiltro;
    }

    public OperadorOrdenador getOperadoresOrden() {
        return operadorOrden;
    }

    public void setOperadoresOrden(OperadorOrdenador operadoresOrden) {
        this.operadorOrden = operadoresOrden;
    }

    public List<Empresa> filtrar(List<Empresa> empresas) {
        return empresas.stream()
                .filter(this::filtrar)
                .collect(Collectors.toList());
    }

    public Boolean filtrar(Empresa empresa) {
        return this.operadoresFiltro.stream()
                .allMatch(operador -> operador.filtrar(empresa));
    }

    public List<Empresa> ordenar(List<Empresa> empresas) {
        return this.operadorOrden.ordenar(empresas);
    }
}
