package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.model.Periodo;
import ar.edu.utn.dds.exceptions.CuentaNoExisteException;
import ar.edu.utn.dds.exceptions.MetodologiaException;

import java.util.List;

/**
 * Created by andres on 06/08/17.
 */
public class ModificadorOrdenadorSum extends ModificadorOrdenador {

    @Override
    public Float reducir(List<Periodo> periodos, String indicador) {
        return periodos.stream()
                .map(periodo -> {
                    try {
                        return periodo.consultarCuenta(indicador);
                    } catch (CuentaNoExisteException e) {
                        throw new MetodologiaException();
                    }
                })
                .reduce((c1, c2) -> c1 + c2).get();
    }
}
