package ar.edu.utn.dds

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.exceptions.SQLInaccesibleException
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.mappers.PeriodoMapper
import grails.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException

@Transactional
class PeriodoService {

    def jdbcTemplate

    @Cacheable(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def listar() {
        try {
            String query = "SELECT * FROM PERIODO"
            return jdbcTemplate.queryForObject(query, new PeriodoMapper())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al listar todos los periodos", e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def obtener(Long id) {
        try {
            String query = "SELECT * FROM PERIODO WHERE periodo_id = ?"
            return jdbcTemplate.queryForObject(query, [id] as Object[], new PeriodoMapper())
        } catch(EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontro periodo con id " + id, e.getCause())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al obtener periodo con id " + id, e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardar(Periodo periodo) {
        try {
            String query = "INSERT INTO PERIODO (fecha_fin, fecha_inicio) VALUES (?, ?)"
            return jdbcTemplate.update(query, periodo.getFechaFin(), periodo.getFechaInicio())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al guardar periodo", e.getCause())
        }
    }

    @CacheEvict(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def actualizar(Periodo periodo) {
        try {
            String query = "UPDATE PERIODO SET fecha_fin = ?, fecha_inicio = ? WHERE periodo_id = ?"
            jdbcTemplate.update(query, periodo.getFechaFin(), periodo.getFechaInicio(), periodo.getId())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al actualizar periodo", e.getCause())
        }
    }
}
