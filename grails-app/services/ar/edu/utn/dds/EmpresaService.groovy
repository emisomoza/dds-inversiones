package ar.edu.utn.dds

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.mappers.EmpresaMapper
import grails.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable

@Transactional
class EmpresaService {

    def jdbcTemplate

    @Cacheable(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def listar() {
        String query = "SELECT * FROM EMPRESA"
        return jdbcTemplate.query(query, new EmpresaMapper())
    }

    def obtener(Long id) {
        String query = "SELECT * FROM EMPRESA WHERE empresa_id = ?"
        return jdbcTemplate.queryForObject(query, [id] as Object[], new EmpresaMapper())
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardar(Empresa empresa) {
        String query = "INSERT INTO EMPRESA (empresa_nombre) VALUES (?)"
        return jdbcTemplate.update(query, empresa.getNombre())
    }

    def actualizar(Empresa empresa) {
        String query = "UPDATE EMPRESA SET empresa_nombre = ? WHERE empresa_id = ?"
        jdbcTemplate.update(query, empresa.getNombre(), empresa.getId())
    }

}
