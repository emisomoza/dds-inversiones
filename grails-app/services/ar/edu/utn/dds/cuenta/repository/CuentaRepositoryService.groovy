package ar.edu.utn.dds.cuenta.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.exceptions.SQLInaccesibleException
import ar.edu.utn.dds.mappers.CuentaMapper
import ar.edu.utn.dds.model.Cuenta
import grails.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException

import static com.xlson.groovycsv.CsvParser.parseCsv

@Transactional
class CuentaRepositoryService {

    def jdbcTemplate

    @Cacheable(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def listar() {
        try {
            String query = "SELECT * FROM CUENTA"
            return jdbcTemplate.queryForObject(query, new CuentaMapper())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al listar todas las cuentas", e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def obtener(Long id) {
        try {
            String query = "SELECT * FROM CUENTA WHERE cuenta_id = ?"
            return (Cuenta) jdbcTemplate.queryForObject(query, [id] as Object[], new CuentaMapper())
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontro cuenta con id " + id, e.getCause())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al listar todas las cuentas", e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def obtenerLista(List<String> cuentas) {
        try {
            String query = "SELECT * FROM CUENTA WHERE cuenta_id = ?"
            return jdbcTemplate.queryForObject(query, [id] as Object[], new CuentaMapper())
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontro cuenta con id " + id, e.getCause())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al listar todas las cuentas", e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardar(Cuenta cuenta) {
        try {
            String query = "INSERT INTO CUENTA (cuenta_nombre, cuenta_valor) VALUES (?, ?)"
            return jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al guardar cuenta " + cuenta.getNombre(), e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def actualizar(Cuenta cuenta) {
        try {
            String query = "UPDATE CUENTA SET cuenta_nombre = ?, cuenta_valor = ? WHERE cuenta_id = ?"
            jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor(), cuenta.getId())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al actualizar cuenta " + cuenta.getNombre(), e.getCause())
        }
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
