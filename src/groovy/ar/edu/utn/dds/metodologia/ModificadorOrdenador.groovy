package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo

abstract class ModificadorOrdenador {

    abstract Double reducir(List<Periodo> periodos, IndicadorService indicadorService, Indicador indicador);
}
