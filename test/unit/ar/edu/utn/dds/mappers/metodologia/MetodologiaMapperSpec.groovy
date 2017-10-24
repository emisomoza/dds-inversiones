package ar.edu.utn.dds.mappers.metodologia

import ar.edu.utn.dds.mappers.metodologia.operador.operadorfiltro.*
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMapper
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMayorMapper
import ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador.OperadorOrdenadorMenorMapper
import spock.lang.Specification

class MetodologiaMapperSpec extends Specification {

    MetodologiaMapper mapper = new MetodologiaMapper()

    void "operador filtro mapper strategy"(String operador, Class<? extends OperadorFiltroMapper> result) {
        expect:
        result.isInstance(mapper.operadorFiltroMapper(operador))

        where:
        operador                                        | result
        MetodologiaMapper.OPERADOR_FILTRO_CRECIENTE     | OperadorFiltroCrecienteMapper.class
        MetodologiaMapper.OPERADOR_FILTRO_DECRECIENTE   | OperadorFiltroDecrecienteMapper.class
        MetodologiaMapper.OPERADOR_FILTRO_IGUAL         | OperadorFiltroIgualMapper.class
        MetodologiaMapper.OPERADOR_FILTRO_MAYOR         | OperadorFiltroMayorMapper.class
        MetodologiaMapper.OPERADOR_FILTRO_MENOR         | OperadorFiltroMenorMapper.class
    }

    void "operador ordenador mapper strategy"(String operador, Class<? extends OperadorOrdenadorMapper> result) {
        expect:
        result.isInstance(mapper.operadorOrdenadorMapper(operador))

        where:
        operador                                   | result
        MetodologiaMapper.OPERADOR_ORDENADOR_MAYOR | OperadorOrdenadorMayorMapper.class
        MetodologiaMapper.OPERADOR_ORDENADOR_MENOR | OperadorOrdenadorMenorMapper.class
    }
}
