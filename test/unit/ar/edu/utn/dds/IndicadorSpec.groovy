package ar.edu.utn.dds

import ar.edu.utn.dds.Indicador
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

/**
 * Created by andres on 21/05/17.
 */
class IndicadorSpec extends Specification {

    void "crear nuevo indiador"(){
        setup:
        ObjectMapper objectMapper = new ObjectMapper()
        Indicador indicador;

        objectMapper.enableDefaultTyping()
        indicador = objectMapper.readValue(new File("./indicadorTest1.json"), Indicador.class)

        expect:
        indicador.aplicar() == 3

    }
}
