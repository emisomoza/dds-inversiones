package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro

interface OperadorFiltroMapperStrategy {

    OperadorFiltro mapear(Map<String, Object> rawOperadorFiltro)

}