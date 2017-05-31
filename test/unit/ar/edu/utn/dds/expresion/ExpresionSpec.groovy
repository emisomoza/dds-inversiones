package ar.edu.utn.dds.expresion

import ar.edu.utn.dds.utilidades.mapper.ObjectMapper
import spock.lang.Specification

/**
 * Created by andres on 29/05/17.
 */
class ExpresionSpec extends Specification {

    def "Test mapeo primaria"() {
        setup:
        Map<String, List<String>> map
        List<Primaria> primarias = new ArrayList<>()
        primarias.add(new PrimariaCuenta("cuentaUno"))
        primarias.add(new PrimariaIndicador("indicadorUno"))
        primarias.add(new PrimariaNumero(4))

        map = new ObjectMapper<String, String>().map(primarias)

        expect:
        map.get("cue").contains("cuentaUno") &&
            map.get("ind").contains("indicadorUno")
    }
}
