package ar.edu.utn.dds

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

/**
 * Created by andres on 21/05/17.
 */
class IndicadorSpec extends Specification {

    void "deserializar indiador"(){
        setup:
        ObjectMapper objectMapper = new ObjectMapper()
        Indicador indicador;

        objectMapper.enableDefaultTyping()
        indicador = objectMapper.readValue(this.getClass().getResource("/indicadorTest1.json").text, Indicador.class)

        expect:
        indicador.aplicar() == 41

    }
}
