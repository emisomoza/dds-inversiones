package ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro

import ar.edu.utn.dds.metodologia.ModificadorFiltroSiempre
import ar.edu.utn.dds.metodologia.OperadorFiltroMayor
import spock.lang.Specification

class OperadorFiltroMayorMapperSpec extends Specification {

    OperadorFiltroMayorMapper mapper = new OperadorFiltroMayorMapper()

    void "mapear modificador"() {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroMayorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorFiltroMayorMapper.COMPARADOR, (Double) 2.45)

        expect:
        mapper.mapear(rawOperator).getModificador() instanceof ModificadorFiltroSiempre
    }

    void "mapear indicador"(String indicador, String result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroMayorMapper.INDICADOR, indicador)
        rawOperator.put(OperadorFiltroMayorMapper.COMPARADOR, (Double) 2.45)

        expect:
        mapper.mapear(rawOperator).getIndicador() == result

        where:
        indicador       | result
        "indicador - 1" | "indicador - 1"
        "indicador - 2" | "indicador - 2"
    }

    void "mapear comparador"(Double comparador, Double result) {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroMayorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorFiltroMayorMapper.COMPARADOR, comparador)

        expect:
        ((OperadorFiltroMayor) mapper.mapear(rawOperator)).getComparador() == result

        where:
        comparador      | result
        (Double) 2.45   | (Double) 2.45
        (Double) 1.34   | (Double) 1.34
    }

    void "mapear ordenador"() {
        setup:
        Map<String, Object> rawOperator = new HashMap<>()
        rawOperator.put(OperadorFiltroMayorMapper.INDICADOR, "indicador - test")
        rawOperator.put(OperadorFiltroMayorMapper.COMPARADOR, (Double) 2.45)

        expect:
        mapper.mapear(rawOperator) instanceof OperadorFiltroMayor
    }
}
