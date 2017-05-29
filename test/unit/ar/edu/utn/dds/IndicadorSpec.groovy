package ar.edu.utn.dds

import ar.edu.utn.dds.expresion.Expresion
import ar.edu.utn.dds.expresion.OperacionMultiplicacion
import ar.edu.utn.dds.expresion.OperacionResta
import ar.edu.utn.dds.expresion.OperacionSuma
import ar.edu.utn.dds.expresion.PrimariaNumero
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class IndicadorSpec extends Specification {

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

        Expresion num1 = new PrimariaNumero(3)
        Expresion num2 = new PrimariaNumero(4)
        Expresion num3 = new PrimariaNumero(20)
        Expresion num4 = new PrimariaNumero(10)

        Expresion op1 = new OperacionSuma(num1, num2)
        Expresion op2 = new OperacionResta(num3, num4)

        Expresion op3 = new OperacionMultiplicacion(op1, op2)

        Indicador indicador = new Indicador(new ArrayList<String>(), new ArrayList<String>())
        indicador.setNombre("Ind1")
        indicador.setExpresion(op3)

        serializacion = objectMapper.writeValueAsString(indicador)
        deserializacion = objectMapper.readValue(serializacion, HashMap.class)
        deserializacionEsperada = objectMapper.readValue(this.getClass().getResource("/indicadorTest2.json").text, HashMap.class)

        expect:
        deserializacion == deserializacionEsperada
    }
}
