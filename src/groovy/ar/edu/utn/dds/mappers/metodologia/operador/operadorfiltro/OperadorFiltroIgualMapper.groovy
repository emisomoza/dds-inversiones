package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroIgual

class OperadorFiltroIgualMapper extends OperadorFiltroMapper {

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroIgual()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {

    }
}
