package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroMenor

class OperadorFiltroMenorMapper extends OperadorFiltroMapper {

    private static final String COMPARADOR = "valor"

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroMenor()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {
        OperadorFiltroMenor operador = (OperadorFiltroMenor) operadorFiltro
        operador.setComparador((Double) rawOperador.get(COMPARADOR))
    }
}
