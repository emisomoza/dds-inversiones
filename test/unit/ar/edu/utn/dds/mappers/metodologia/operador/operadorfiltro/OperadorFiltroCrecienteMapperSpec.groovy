package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.ModificadorFiltroSiempre
import ar.edu.utn.dds.metodologia.OperadorFiltroCreciente
import spock.lang.Specification

class OperadorFiltroCrecienteMapperSpec extends Specification {

    OperadorFiltroCrecienteMapper mapper = new OperadorFiltroCrecienteMapper()

    void "mapear modificador"() {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroCrecienteMapper.INDICADOR, "indicador - test")

        expect:
        mapper.mapear(rawOperator).getModificador() instanceof ModificadorFiltroSiempre
    }

    void "mapear indicador"(String indicador, String result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroCrecienteMapper.INDICADOR, indicador)

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
        rawOperator.put(OperadorFiltroCrecienteMapper.INDICADOR, "indicador - test")

        expect:
        mapper.mapear(rawOperator) instanceof OperadorFiltroCreciente
    }
}
