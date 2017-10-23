package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroMayor

class OperadorFiltroMayorMapper extends OperadorFiltroMapper {

    private static final String COMPARADOR = "valor"

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroMayor()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {
        OperadorFiltroMayor operador = (OperadorFiltroMayor) operadorFiltro
        operador.setComparador((Double) rawOperador.get(COMPARADOR))
    }
}
