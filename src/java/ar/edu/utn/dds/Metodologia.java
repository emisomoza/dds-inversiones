package ar.edu.utn.dds;

import ar.edu.utn.dds.metodologia.Modificador;
import ar.edu.utn.dds.metodologia.Operador;
import ar.edu.utn.dds.metodologia.OperadorFiltro;
import ar.edu.utn.dds.metodologia.OperadorOrdenador;

import java.util.List;

/**
 * Created by andres on 06/08/17.
 */
public class Metodologia {

    private String indicador;
    private List<OperadorFiltro> operadoresFiltro;
    private List<OperadorOrdenador> operadoresOrden;
    private Modificador modificador;

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public List<OperadorFiltro> getOperadoresFiltro() {
        return operadoresFiltro;
    }

    public void setOperadoresFiltro(List<OperadorFiltro> operadoresFiltro) {
        this.operadoresFiltro = operadoresFiltro;
    }

    public List<OperadorOrdenador> getOperadoresOrden() {
        return operadoresOrden;
    }

    public void setOperadoresOrden(List<OperadorOrdenador> operadoresOrden) {
        this.operadoresOrden = operadoresOrden;
    }

    public Modificador getModificador() {
        return modificador;
    }

    public void setModificador(Modificador modificador) {
        this.modificador = modificador;
    }

    public Boolean filtrar(Empresa empresa) {
        return this.operadoresFiltro.stream()
                .allMatch(operador -> operador.filtrar(empresa, this.indicador, this.modificador));
    }
}
