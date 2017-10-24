package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.ModificadorOrdenador
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorProm
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorSum
import ar.edu.utn.dds.metodologia.OperadorOrdenadorMenor
import spock.lang.Specification

class OperadorOrdenadorMenorMapperSpec extends Specification {

    OperadorOrdenadorMenorMapper mapper = new OperadorOrdenadorMenorMapper()

    void "mapear modificador"(String modificador, Class<? extends ModificadorOrdenador> result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorOrdenadorMenorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorOrdenadorMenorMapper.MODIFICADOR, modificador)

        expect:
        result.isInstance(mapper.mapear(rawOperator).getModificador())

        where:
        modificador                                   | result
        OperadorOrdenadorMenorMapper.MODIFICADOR_PROM | ModificadorOrdenadorProm.class
        OperadorOrdenadorMenorMapper.MODIFICADOR_SUM  | ModificadorOrdenadorSum.class
    }

    void "mapear indicador"(String indicador, String result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorOrdenadorMenorMapper.INDICADOR, indicador)
        rawOperator.put(OperadorOrdenadorMenorMapper.MODIFICADOR, OperadorOrdenadorMenorMapper.MODIFICADOR_PROM)

        expect:
        mapper.mapear(rawOperator).getIndicador() == result

        where:
        indicador       | result
        "indicador - 1" | "indicador - 1"
        "indicador - 2" | "indicador - 2"
    }

    void "mapear ordenador"() {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorOrdenadorMenorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorOrdenadorMenorMapper.MODIFICADOR, OperadorOrdenadorMenorMapper.MODIFICADOR_PROM)

        expect:
        mapper.mapear(rawOperator) instanceof OperadorOrdenadorMenor
    }
}
