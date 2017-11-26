package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo

class ModificadorOrdenadorProm extends ModificadorOrdenador {

    @Override
    Double reducir(List<Periodo> periodos, IndicadorService indicadorService, Indicador indicador) {
        def suma = periodos.collect {
            try {
                return indicadorService.aplicar(it, indicador)
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException("No se puede aplicar la metodologia porque no se encontro una cuenta")
            }
        }.sum()

        return suma / periodos.size()
    }
}