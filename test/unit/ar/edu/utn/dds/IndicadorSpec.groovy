package ar.edu.utn.dds

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class IndicadorSpec extends Specification {

    void "dependencias indicador"() {
        expect:
        Indicador indicador = new Indicador("IndicadorTest", ecuacion)
        [indicador.getDependenciasIndicador(), indicador.getDependenciasCuenta()] == resultado

        where:
        ecuacion                                              | resultado
        "ind(ind1)+cue(cue1)*2^7/cue(cue1)"                   | [["ind1"], ["cue1"]]
        "ind(ind1)*cue(cue2)+ind(ind4)+ind(ind1)"             | [["ind1", "ind4"], ["cue2"]]
    }

    void "deserializar indiador"() {
        setup:
        ObjectMapper objectMapper = new ObjectMapper()
        Indicador indicador

        objectMapper.enableDefaultTyping()
        indicador = objectMapper.readValue(this.getClass().getResource("/indicadorTest1.json").text, Indicador.class)

        expect:
        indicador.aplicar() == 41

    }

    void "serializar indicador"() {
        setup:
        ObjectMapper objectMapper = new ObjectMapper()
        String serializacion
        HashMap<String, Object> deserializacion
        HashMap<String, Object> deserializacionEsperada

        Indicador indicador = new Indicador("Ind1", "(3+4)*(20-10)")

        serializacion = objectMapper.writeValueAsString(indicador)
        deserializacion = objectMapper.readValue(serializacion, HashMap.class)
        deserializacionEsperada = objectMapper.readValue(this.getClass().getResource("/indicadorTest2.json").text, HashMap.class)

        expect:
        deserializacion == deserializacionEsperada
    }
}
