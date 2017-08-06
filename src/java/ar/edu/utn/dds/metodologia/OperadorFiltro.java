package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Empresa;
import ar.edu.utn.dds.Periodo;

import java.util.function.Predicate;

/**
 * Created by andres on 06/08/17.
 */
public class OperadorFiltro extends Operador {

    private Predicate<Periodo> expresionFiltrado(String nombreIndicador) {
        return null;
    }

    public Boolean filtrar(Empresa empresa, String indicador, Modificador modificador) {
        return null; //TODO Completar
    }

}
