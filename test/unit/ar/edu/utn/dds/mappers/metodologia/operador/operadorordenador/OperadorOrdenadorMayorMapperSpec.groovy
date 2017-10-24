package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.ModificadorOrdenador
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorProm
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorSum
import ar.edu.utn.dds.metodologia.OperadorOrdenadorMayor
import spock.lang.Specification

class OperadorOrdenadorMayorMapperSpec extends Specification {

    OperadorOrdenadorMayorMapper mapper = new OperadorOrdenadorMayorMapper()

    void "mapear modificador"(String modificador, Class<? extends ModificadorOrdenador> result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorOrdenadorMayorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorOrdenadorMayorMapper.MODIFICADOR, modificador)

        expect:
        result.isInstance(mapper.mapear(rawOperator).getModificador())

        where:
        modificador                                   | result
        OperadorOrdenadorMayorMapper.MODIFICADOR_PROM | ModificadorOrdenadorProm.class
        OperadorOrdenadorMayorMapper.MODIFICADOR_SUM  | ModificadorOrdenadorSum.class
    }

    void "mapear indicador"(String indicador, String result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorOrdenadorMayorMapper.INDICADOR, indicador)
        rawOperator.put(OperadorOrdenadorMayorMapper.MODIFICADOR, OperadorOrdenadorMayorMapper.MODIFICADOR_PROM)

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
        rawOperator.put(OperadorOrdenadorMayorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorOrdenadorMayorMapper.MODIFICADOR, OperadorOrdenadorMayorMapper.MODIFICADOR_PROM)

        expect:
        mapper.mapear(rawOperator) instanceof OperadorOrdenadorMayor
    }
}
