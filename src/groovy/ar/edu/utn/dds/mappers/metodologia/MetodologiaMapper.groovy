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

    private static final String NOMBRE_TAG = "nombre"
    private static final String OPERADOR = "operador"

    private static final String OPERADORES_FILTRO_TAG = "operadoresfiltro"
    private static final String OPERADOR_FILTRO_CRECIENTE = "creciente"
    private static final String OPERADOR_FILTRO_DECRECIENTE = "decreciente"
    private static final String OPERADOR_FILTRO_IGUAL = "igual"
    private static final String OPERADOR_FILTRO_MAYOR = ">"
    private static final String OPERADOR_FILTRO_MENOR = "<"

    private static final String OPERADOR_ORDEN_TAG = "operadororden"
    private static final String OPERADOR_ORDENADOR_MAYOR_TAG = ">"
    private static final String OPERADOR_ORDENADOR_MENOR_TAG = "<"


    Metodologia mapear(Map<String, Object> rawMetodologia) {
        Metodologia nuevaMetodologia = new Metodologia()

        nuevaMetodologia.setNombre((String) rawMetodologia.get(NOMBRE_TAG))
        nuevaMetodologia.setOperadoresFiltro(this.mapearOperadoresFiltro((Map<String, Object>) rawMetodologia.get(OPERADORES_FILTRO_TAG)))
        nuevaMetodologia.setOperadorOrden(this.mapearOperadorOrden((Map<String, Object>) rawMetodologia.get(OPERADOR_ORDEN_TAG)))

        return nuevaMetodologia
    }

    protected List<OperadorFiltro> mapearOperadoresFiltro(Map<String, Object> rawOperadoresFiltro) {
        return rawOperadoresFiltro.values().parallelStream()
                .map({rawOperador -> this.mapearOperadorFiltro((Map) rawOperador)})
                .collect(Collectors.toList())
    }

    protected OperadorFiltro mapearOperadorFiltro(Map<String, Object> rawOperadorFiltro) {
        OperadorFiltroMapperStrategy mapper = this.operadorFiltroMapperStrategy().get(rawOperadorFiltro.get(OPERADOR)).newInstance()
        mapper.mapear(rawOperadorFiltro)
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
        OperadorOrdenadorMapperStrategy mapper = this.operadorOrdenadorMapperStrategy().get(rawOperadoresFiltro.get(OPERADOR)).newInstance()
        return mapper.mapear(rawOperadoresFiltro)
    }

    protected Map<String, Class<? extends OperadorOrdenadorMapperStrategy>> operadorOrdenadorMapperStrategy() {
        Map<String, Class<? extends OperadorOrdenadorMapperStrategy>> strategyMap = new HashMap<>()

        strategyMap.put(OPERADOR_ORDENADOR_MAYOR_TAG, OperadorOrdenadorMayorMapper.class)
        strategyMap.put(OPERADOR_ORDENADOR_MENOR_TAG, OperadorOrdenadorMenorMapper.class)

        return strategyMap
    }
}
