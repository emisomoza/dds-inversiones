package ar.edu.utn.dds.importador

import ar.edu.utn.dds.exceptions.InversionesException

import static com.xlson.groovycsv.CsvParser.parseCsv

class ImportadorCuentas {
    
    protected static final String EMPRESA_TAG = "empresa"
    protected static final String FECHA_DESDE_TAG = "fecha_desde"
    protected static final String FECHA_HASTA_TAG = "fecha_hasta"
    protected static final String TIPO_TAG = "tipo"
    protected static final String VALOR_TAG = "valor"

    def parsearImportCuentas(File archivo) {
        try {
            return this.parsearImportCuentas(archivo.text.toString())
        } catch (Exception e) {
            throw new InversionesException("Error importando cuentas desde archivo", e)
        }
    }

    def parsearImportCuentas(String cuentasCSV) {
        List<Map<String, String>> mapasCuentas = new ArrayList<>()

        try {
            Iterator iteradorCuentas = parseCsv(cuentasCSV)

            for(linea in iteradorCuentas) {
                Map<String, String> mapaCuentas = new HashMap<>()
                mapaCuentas.put(EMPRESA_TAG, linea.Empresa)
                mapaCuentas.put(FECHA_DESDE_TAG, linea.Fecha_Desde)
                mapaCuentas.put(FECHA_HASTA_TAG, linea.Fecha_Hasta)
                mapaCuentas.put(TIPO_TAG, linea.Cuenta)
                mapaCuentas.put(VALOR_TAG, linea.Valor)
                mapasCuentas.add(mapaCuentas)
            }

            return mapasCuentas
        } catch(Exception e) {
            throw new InversionesException("Error parceando cuentas para importar", e)
        }
    }
}
