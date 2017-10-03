package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Periodo

class ModificadorFiltroSiempre extends ModificadorFiltro {

    List<Periodo> filtrarPeriodos(List<Periodo> periodos) {
        return periodos;
    }
}
