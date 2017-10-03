package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.model.Periodo

class ModificadorOrdenadorSum extends ModificadorOrdenador {

    @Override
    Float reducir(List<Periodo> periodos, String indicador) {
        return periodos.collect {
                    try {
                        return it.consultarCuenta(indicador);
                    } catch (CuentaNoExisteException e) {
                        throw new MetodologiaException();
                    }
                }.sum()
    }
}