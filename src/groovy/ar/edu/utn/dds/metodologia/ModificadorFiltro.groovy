package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Periodo

abstract class ModificadorFiltro {

    abstract List<Periodo> filtrarPeriodos(List<Periodo> periodos)
}