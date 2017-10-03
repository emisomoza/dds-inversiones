package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.model.Periodo

class ModificadorOrdenadorProm extends ModificadorOrdenador {

    @Override
    Float reducir(List<Periodo> periodos, String indicador) {
        def suma = periodos.collect {
            try {
                return it.consultarCuenta(indicador);
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException();
            }
        }.sum()

        return suma / periodos.size();
    }
}