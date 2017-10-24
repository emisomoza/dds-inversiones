package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroCreciente

class OperadorFiltroCrecienteMapper extends OperadorFiltroMapper {

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroCreciente()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {

    }
}
