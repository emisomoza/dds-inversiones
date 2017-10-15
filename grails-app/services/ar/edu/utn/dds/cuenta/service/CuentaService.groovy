package ar.edu.utn.dds.cuenta.service

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.model.Cuenta

import static com.xlson.groovycsv.CsvParser.parseCsv

class CuentaService {

    public static final String EMPRESA_TAG = "empresa"
    public static final String FECHA_DESDE_TAG = "fecha_desde"
    public static final String FECHA_HASTA_TAG = "fecha_hasta"
    public static final String TIPO_TAG = "tipo"
    public static final String VALOR_TAG = "valor"

    def cuentaRepositoryService

    def listar() {
        return this.listar(new Cuenta())
    }

    def listar(Cuenta cuenta) {
        return this.cuentaRepositoryService.listar(cuenta)
    }

    def obtener(Long empId, Long perId, Long cueId) {
        return this.cuentaRepositoryService.obtener(empId, perId, cueId)
    }

    def guardar(Cuenta cuenta) {
       return this.cuentaRepositoryService.guardar(cuenta)
    }

    def actualizar(Cuenta cuenta) {
       return this.cuentaRepositoryService.actualizar(cuenta)
    }
    
    def parsearImportCuentas(File archivo) {
        try {
            return this.parsearImportCuentas(archivo.text.toString())
        } catch (Exception e) {
            String mensaje = "Error importando cuentas desde archivo"
            log.error(mensaje)
            throw new InversionesException(mensaje, e.getCause())
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
            throw new InversionesException("Error parceando cuentas para importar", e.getCause())
        }
    }
}
