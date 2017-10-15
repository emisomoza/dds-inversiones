package ar.edu.utn.dds

import ar.edu.utn.dds.cuenta.service.CuentaService
import ar.edu.utn.dds.utils.DiffHelper
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CuentaService)
class CuentaServiceSpec extends Specification {

    void "parsear archivo de importacion de cuentas"() {
        setup:
            List<Map<String, String>> mapasCuentas = service.parsearImportCuentas(new File(this.getClass().getResource('carga_cuentas.csv').getFile()))
            List<Map<String, String>> mapasCuentasEsperado = new ArrayList<>()

            Map<String, String> tmpMap = new HashMap<>()
            tmpMap.put(CuentaService.EMPRESA_TAG,"Apple")
            tmpMap.put(CuentaService.FECHA_DESDE_TAG,"01/01/2015")
            tmpMap.put(CuentaService.FECHA_HASTA_TAG, "01/01/2016")
            tmpMap.put(CuentaService.TIPO_TAG, "mac")
            tmpMap.put(CuentaService.VALOR_TAG, "1000")
            mapasCuentasEsperado.add(tmpMap)

            tmpMap = new HashMap<>()
            tmpMap.put(CuentaService.EMPRESA_TAG,"windows")
            tmpMap.put(CuentaService.FECHA_DESDE_TAG,"01/01/2016")
            tmpMap.put(CuentaService.FECHA_HASTA_TAG, "01/01/2016")
            tmpMap.put(CuentaService.TIPO_TAG, "win")
            tmpMap.put(CuentaService.VALOR_TAG, "7979")
            mapasCuentasEsperado.add(tmpMap)

        expect:
            new DiffHelper().diff(mapasCuentas, mapasCuentasEsperado) == []

    }
}
