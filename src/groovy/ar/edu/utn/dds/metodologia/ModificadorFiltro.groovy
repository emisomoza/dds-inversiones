package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Periodo

abstract class ModificadorFiltro implements Serializable {

    abstract List<Periodo> filtrarPeriodos(List<Periodo> periodos)
}