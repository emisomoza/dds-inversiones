package ar.edu.utn.dds

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.DatosInaccesiblesException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.exceptions.SQLInaccesibleException
import ar.edu.utn.dds.mappers.EmpresaMapper
import ar.edu.utn.dds.model.Empresa
import grails.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.ResultSetExtractor

import java.sql.ResultSet
import java.sql.SQLException

@Transactional
class EmpresaService {
    def jdbcTemplate
    def cuentaService

    @Transactional(readOnly = true)
    def existe(Long id) {
        def result
        log.info(String.format("Verificando existencia de empresa %l", id))
        String query = "SELECT 1 FROM EMPRESA WHERE empresa_id = ? LIMIT 1"

        try {
            result = jdbcTemplate.query(query, [id] as Object[], new ResultSetExtractor<Boolean>() {
                @Override
                Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                    boolean r = rs.next()
                    return r
                }
            })
            log.info(String.format("Resultado de verificando existencia de empresa %l: %l", id, result))
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al verificar existencia de empresa " + id, e.getCause())
        }
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def listar() {
        def result
        log.info("Listando empresas")
        String query = "SELECT * FROM EMPRESA"
        try {
            result = jdbcTemplate.query(query, new EmpresaMapper())
            log.info("Empresas listadas: " + result.toString())
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al listar todas las empresas", e.getCause())
        }
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def obtener(Long id) {
        try {
            String query = "SELECT * FROM EMPRESA WHERE empresa_id = ?"
            return jdbcTemplate.queryForObject(query, [id] as Object[], new EmpresaMapper())
        } catch(EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontro la empresa " + id, e.getCause())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al obtener empresa " + id, e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardar(Empresa empresa) {
        def result
        log.info("Guardando empresa")
        String query = "INSERT INTO EMPRESA (empresa_nombre) VALUES (?)"
        try {
            result = jdbcTemplate.update(query, empresa.getNombre())
            log.info(String.format("Empresa guardada: " + result))
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al guardar empresa " + empresa.getNombre(), e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardarCompuesto(Empresa empresa) {
        def result
        log.info("Guardando empresa compuesta")
        String query = "INSERT INTO EMPRESA (empresa_nombre) VALUES (?)"
        try {
            result = jdbcTemplate.update(query, empresa.getNombre())
            log.info(String.format("Empresa guardada: %l", result))
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al guardar empresa " + empresa.getNombre(), e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def actualizar(Empresa empresa) {
        log.info(String.format("Actualizando empresa %l", empresa.id))
        String query = "UPDATE EMPRESA SET empresa_nombre = ? WHERE empresa_id = ?"
        try {
            jdbcTemplate.update(query, empresa.getNombre(), empresa.getId())
            log.info(String.format("Empresa actualizada: %l", empresa.id))
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al actualizar empresa " + empresa.getId(), e.getCause())
        }
    }

    def importarCuentas(File archivo) {
        List<Map<String, String>> mapasCuentas = this.cuentaService.parsearArchImportCuentas(archivo)
    }



}
