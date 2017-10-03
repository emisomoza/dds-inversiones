package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Periodo

abstract class ModificadorOrdenador {

    abstract Float reducir(List<Periodo> periodos, String indicador);
}
