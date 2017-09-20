package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.mappers.CuentaMapper
import ar.edu.utn.dds.model.Cuenta
import grails.transaction.Transactional

import static com.xlson.groovycsv.CsvParser.parseCsv

@Transactional
class CuentaService {

    def jdbcTemplate

    def listar() {
        String query = "SELECT * FROM CUENTA"
        return jdbcTemplate.queryForObject(query, new CuentaMapper())
    }

    def obtener(Long id) {
        String query = "SELECT * FROM CUENTA WHERE cuenta_id = ?"
        return jdbcTemplate.queryForObject(query, [id] as Object[], new CuentaMapper())
    }

    def guardar(Cuenta cuenta) {
        String query = "INSERT INTO CUENTA (cuenta_nombre, cuenta_valor) VALUES (?, ?)"
        return jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor())
    }

    def actualizar(Cuenta cuenta) {
        String query = "UPDATE CUENTA SET cuenta_nombre = ?, cuenta_valor = ? WHERE cuenta_id = ?"
        jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor(), cuenta.getId())
    }

    def parsearArchImportCuentas(File archivo) {

        try {
            return this.parsearArchImportCuentas(archivo.text.toString())
        } catch (Exception e) {
            String mensaje = "Error importando cuentas desde archivo"
            log.error(mensaje)
            throw new InversionesException(mensaje, e.getCause())
        }
    }

    def parsearArchImportCuentas(String cuentasCSV) {
        List<Map<String, String>> mapasCuentas = new ArrayList<>()

        try {
            Iterator iteradorCuentas = parseCsv(cuentasCSV)

            for(linea in iteradorCuentas) {
                Map<String, String> mapaCuentas = new HashMap<>()
                mapaCuentas.put("empresa", linea.Empresa)
                mapaCuentas.put("fecha_desde", linea.Fecha_Desde)
                mapaCuentas.put("fecha_hasta", linea.Fecha_Hasta)
                mapaCuentas.put("tipo", linea.Cuenta)
                mapaCuentas.put("valor", linea.Valor)
                mapasCuentas.add(mapaCuentas)
            }

            return mapasCuentas
        } catch(Exception e) {
            throw new InversionesException("Error parceando cuentas para importar", e.getCause())
        }
    }
}
