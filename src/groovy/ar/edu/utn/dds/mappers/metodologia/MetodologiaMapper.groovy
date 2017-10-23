package ar.edu.utn.dds.mappers.metodologia

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador
import ar.edu.utn.dds.metodologia.OperadorOrdenadorMenor
import ar.edu.utn.dds.model.Metodologia

class MetodologiaMapper {

    private static final String NOMBRE_TAG = "nombre"
    private static final String INDICADOR = "indicador"
    private static final String VALOR = "valor"
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

    private static final String MODIFICADOR = "modificador"
    private static final String MODIFICADOR_FILTRO_SIEMPRE = "siempre"
    private static final String MODIFICADOR_ORDENADOR_PROM = "promedio"
    private static final String MODIFICADOR_ORDENADOR_SUM = "suma"

    Metodologia mapear(Map<String, Object> rawMetodologia) {
        Metodologia nuevaMetodologia = new Metodologia()

        nuevaMetodologia.setNombre((String) rawMetodologia.get(NOMBRE_TAG))
        nuevaMetodologia.setOperadoresFiltro(this.mapearOperadoresFiltro((Map<String, Object>) rawMetodologia.get(OPERADORES_FILTRO_TAG)))
        nuevaMetodologia.setOperadorOrden(this.mapearOperadorOrden((Map<String, Object>) rawMetodologia.get(OPERADOR_ORDEN_TAG)))

        return nuevaMetodologia
    }

    protected List<OperadorFiltro> mapearOperadoresFiltro(Map<String, Object> rawOperadoresFiltro) {
        List<OperadorFiltro> operadoresFiltro = new ArrayList<>()

        return operadoresFiltro
    }

    protected OperadorOrdenador mapearOperadorOrden(Map<String, Object> rawOperadoresFiltro) {
        Class<? extends OperadorOrdenador> claseOperador = this.operadorOrdenadorStrategy().get(rawOperadoresFiltro.get(OPERADOR))
        OpeardorOrdenador
    }

    protected Map<String, Class<? extends OperadorOrdenador>> operadorOrdenadorStrategy() {
        Map<String, Class<? extends OperadorOrdenador>> strategyMap = new HashMap<>()

        strategyMap.put(OPERADOR_ORDENADOR_MENOR_TAG, OperadorOrdenadorMenor.class)
        strategyMap.put(OPERADOR_ORDENADOR_MENOR_TAG, OperadorOrdenadorMenor.class)

        return strategyMap
    }
}
