package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.OperadorOrdenador

interface OperadorOrdenadorMapperStrategy {

    OperadorOrdenador mapear(Map<String, Object> rawOperadorOrdenador)

}