package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Periodo;

import java.util.List;

/**
 * Created by andres on 06/08/17.
 */
public abstract class ModificadorFiltro extends Modificador {

    public abstract List<Periodo> filtrarPeriodos(List<Periodo> periodos);
}