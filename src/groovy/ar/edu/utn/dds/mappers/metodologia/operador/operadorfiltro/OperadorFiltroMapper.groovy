package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.ModificadorFiltroSiempre
import ar.edu.utn.dds.metodologia.OperadorFiltro

abstract class OperadorFiltroMapper implements OperadorFiltroMapperStrategy {

    private static final String INDICADOR = "indicador"

    abstract OperadorFiltro getInstance()
    abstract void initialize(OperadorFiltro operadorFiltro, Map<String, Object> rawOperador)

    @Override
    OperadorFiltro mapear(Map<String, Object> rawOperador) {
        OperadorFiltro operador = this.getInstance()

        operador.setModificador(new ModificadorFiltroSiempre())
        operador.setIndicador((String) rawOperador.get(INDICADOR))

        this.initialize(operador, rawOperador)

        return operador
    }
}
