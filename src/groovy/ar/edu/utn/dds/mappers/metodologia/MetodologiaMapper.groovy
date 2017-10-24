package ar.edu.utn.dds.mappers.metodologia

import ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro.*
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMapperStrategy
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMayorMapper
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMenorMapper
import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador
import ar.edu.utn.dds.model.Metodologia

import java.util.stream.Collectors

class MetodologiaMapper {

    protected static final String NOMBRE = "nombre"
    protected static final String OPERADOR = "operador"

    protected static final String OPERADORES_FILTRO = "operadoresfiltro"
    protected static final String OPERADOR_FILTRO_CRECIENTE = "creciente"
    protected static final String OPERADOR_FILTRO_DECRECIENTE = "decreciente"
    protected static final String OPERADOR_FILTRO_IGUAL = "igual"
    protected static final String OPERADOR_FILTRO_MAYOR = ">"
    protected static final String OPERADOR_FILTRO_MENOR = "<"

    protected static final String OPERADOR_ORDEN = "operadororden"
    protected static final String OPERADOR_ORDENADOR_MAYOR = ">"
    protected static final String OPERADOR_ORDENADOR_MENOR = "<"


    Metodologia mapear(Map<String, Object> rawMetodologia) {
        Metodologia nuevaMetodologia = new Metodologia()

        nuevaMetodologia.setNombre((String) rawMetodologia.get(NOMBRE))
        nuevaMetodologia.setOperadoresFiltro(this.mapearOperadoresFiltro((Map<String, Object>) rawMetodologia.get(OPERADORES_FILTRO)))
        nuevaMetodologia.setOperadorOrden(this.mapearOperadorOrden((Map<String, Object>) rawMetodologia.get(OPERADOR_ORDEN)))

        return nuevaMetodologia
    }

    protected List<OperadorFiltro> mapearOperadoresFiltro(Map<String, Object> rawOperadoresFiltro) {
        return rawOperadoresFiltro.values().parallelStream()
                .map({rawOperador -> this.mapearOperadorFiltro((Map) rawOperador)})
                .collect(Collectors.toList())
    }

    protected OperadorFiltro mapearOperadorFiltro(Map<String, Object> rawOperadorFiltro) {
        OperadorFiltroMapperStrategy mapper = this.operadorFiltroMapper((String) rawOperadorFiltro.get(OPERADOR))
        return mapper.mapear(rawOperadorFiltro)
    }

    protected OperadorFiltroMapperStrategy operadorFiltroMapper(String operador) {
        return this.operadorFiltroMapperStrategy().get(operador).newInstance()
    }

    protected Map<String, Class<? extends OperadorFiltroMapperStrategy>> operadorFiltroMapperStrategy() {
        Map<String, Class<? extends OperadorFiltroMapperStrategy>> strategyMap = new HashMap<>()

        strategyMap.put(OPERADOR_FILTRO_CRECIENTE, OperadorFiltroCrecienteMapper.class)
        strategyMap.put(OPERADOR_FILTRO_DECRECIENTE, OperadorFiltroDecrecienteMapper.class)
        strategyMap.put(OPERADOR_FILTRO_IGUAL, OperadorFiltroIgualMapper.class)
        strategyMap.put(OPERADOR_FILTRO_MAYOR, OperadorFiltroMayorMapper.class)
        strategyMap.put(OPERADOR_FILTRO_MENOR, OperadorFiltroMenorMapper.class)

        return strategyMap
    }

    protected OperadorOrdenador mapearOperadorOrden(Map<String, Object> rawOperadoresFiltro) {
        OperadorOrdenadorMapperStrategy mapper = this.operadorOrdenadorMapper((String) rawOperadoresFiltro.get(OPERADOR))
        return mapper.mapear(rawOperadoresFiltro)
    }

    protected OperadorOrdenadorMapperStrategy operadorOrdenadorMapper(String operador) {
        return this.operadorOrdenadorMapperStrategy().get(operador).newInstance()
    }

    protected Map<String, Class<? extends OperadorOrdenadorMapperStrategy>> operadorOrdenadorMapperStrategy() {
        Map<String, Class<? extends OperadorOrdenadorMapperStrategy>> strategyMap = new HashMap<>()

        strategyMap.put(OPERADOR_ORDENADOR_MAYOR, OperadorOrdenadorMayorMapper.class)
        strategyMap.put(OPERADOR_ORDENADOR_MENOR, OperadorOrdenadorMenorMapper.class)

        return strategyMap
    }
}
