package ar.edu.utn.dds

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CuentaService)
class CuentaServiceSpec extends Specification {

    void "parsear archivo de importacion de cuentas"() {
        setup:
        List<Map<String, String>> mapasCuentas = service.parsearArchImportCuentas(new File(this.getClass().getResource('carga_cuentas.csv').getFile()))
//          Map<String, String> mapaCuentasEsperado = new HashMap<>()
//          mapaCuentasEsperado.put()

        expect:
        mapasCuentas.size() == 2
    }
}
