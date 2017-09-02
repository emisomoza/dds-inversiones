package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.model.Periodo;

import java.util.List;

/**
 * Created by andres on 06/08/17.
 */
public abstract class ModificadorOrdenador extends Modificador {

    public abstract Float reducir(List<Periodo> periodos, String indicador);
}
