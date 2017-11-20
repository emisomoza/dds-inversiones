package ar.edu.utn.dds.importador

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.utils.DiffHelper
import spock.lang.Specification

class ImportadorCuentasSpec extends Specification {

    ImportadorCuentas imp

    def setup() {
        imp = new ImportadorCuentas()
    }

    void "parsear archivo"() {
        setup:
        List<Map<String, String>> mapasCuentas = imp.parsearImport(new File(this.getClass().getResource('carga_cuentas.csv').getFile()))
        List<Map<String, String>> mapasCuentasEsperado = new ArrayList<>()

        Map<String, String> tmpMap = new HashMap<>()
        tmpMap.put(ImportadorCuentas.EMPRESA_TAG,"Apple")
        tmpMap.put(ImportadorCuentas.FECHA_DESDE_TAG,"01/01/2015")
        tmpMap.put(ImportadorCuentas.FECHA_HASTA_TAG, "01/01/2016")
        tmpMap.put(ImportadorCuentas.TIPO_TAG, "mac")
        tmpMap.put(ImportadorCuentas.VALOR_TAG, "1000")
        mapasCuentasEsperado.add(tmpMap)

        tmpMap = new HashMap<>()
        tmpMap.put(ImportadorCuentas.EMPRESA_TAG,"windows")
        tmpMap.put(ImportadorCuentas.FECHA_DESDE_TAG,"01/01/2016")
        tmpMap.put(ImportadorCuentas.FECHA_HASTA_TAG, "01/01/2016")
        tmpMap.put(ImportadorCuentas.TIPO_TAG, "win")
        tmpMap.put(ImportadorCuentas.VALOR_TAG, "7979")
        mapasCuentasEsperado.add(tmpMap)

        expect:
        new DiffHelper().diff(mapasCuentas, mapasCuentasEsperado) == []

    }

    void "parsear string csv erroneo"() {
        setup:
        String cabecera = "Empresa,Fecha_Desde,Fecha_Hasta,Cuenta,Valor\n"

        when:
        imp.parsearImport(cabecera + ",,,,")

        then:
        thrown InversionesException

        when:
        imp.parsearImport(cabecera + "windows,01/01/2016,01/01/2016,win,asd")

        then:
        thrown InversionesException

        when:
        imp.parsearImport(cabecera + "windows,01/01/2016,01/012016,win,7979")

        then:
        thrown InversionesException

        when:
        imp.parsearImport(cabecera + "windows,60/01/2016,01/01/2016,win,7979")

        then:
        thrown InversionesException
    }
}