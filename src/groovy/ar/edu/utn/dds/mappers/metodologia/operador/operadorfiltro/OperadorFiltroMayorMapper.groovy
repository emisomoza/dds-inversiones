package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorFiltroMayor

class OperadorFiltroMayorMapper extends OperadorFiltroMapper {

    protected static final String COMPARADOR = "comparador"

    @Override
    OperadorFiltro getInstance() {
        return new OperadorFiltroMayor()
    }

    @Override
    void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador) {
        OperadorFiltroMayor operador = (OperadorFiltroMayor) operadorFiltro
        operador.setComparador(Double.valueOf((String) rawOperador.get(COMPARADOR)))
    }
}
