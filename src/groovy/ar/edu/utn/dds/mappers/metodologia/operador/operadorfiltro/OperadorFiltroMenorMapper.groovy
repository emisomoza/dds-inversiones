package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroMenor

class OperadorFiltroMenorMapper extends OperadorFiltroMapper {

    protected static final String COMPARADOR = "comparador"

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroMenor()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {
        OperadorFiltroMenor operador = (OperadorFiltroMenor) operadorFiltro
        operador.setComparador(Double.valueOf((String) rawOperador.get(COMPARADOR)))
    }
}
