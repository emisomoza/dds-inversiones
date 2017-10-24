package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroDecreciente

class OperadorFiltroDecrecienteMapper extends OperadorFiltroMapper {

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroDecreciente()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {

    }
}
