package ar.edu.utn.dds.empresa.repository

import ar.edu.utn.dds.jdbc.DefaultJDBCRepositoryService
import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.mappers.jdbc.EmpresaMapper
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.utils.queries.QueryUtils
import ar.edu.utn.dds.utils.queries.builders.RootStatementBuilder
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.RowMapper

class EmpresaRepositoryService extends DefaultJDBCRepositoryService<Empresa> {

    private static final Map<String, String> COLUMNS = {
        Map columns = new HashMap()
        columns.put("id", "EMPRESA_ID")
        columns.put("nombre", "EMPRESA_NOMBRE")
        return columns
    }.call()

    private static final String TABLE = "EMPRESA"

    private RowMapper mapper = new EmpresaMapper()

    Boolean existe(Long id) {
        QueryUtils queryUtils = this.obtenerQueryExiste(id)
        this.existe(queryUtils)
    }

    private QueryUtils obtenerQueryExiste(Long id) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement("1", TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), id)

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    List<Empresa> listar(Empresa empresa) {
        QueryUtils queryUtils = this.obtenerQueryListar(empresa)
        return this.listar(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryListar(Empresa empresa) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), empresa.getId())
        queryUtils.addWhereParam(COLUMNS.get("nombre"), empresa.getNombre())

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.EMPRESA_CACHE_NAME, key="#id", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Empresa obtener(Long id) {
        QueryUtils queryUtils = this.obtenerQueryObtener(id)
        return this.obtener(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryObtener(Long id) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), id)

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Empresa empresa) {
        QueryUtils queryUtils = this.obtenerQueryGuardar(empresa)
        this.guardar(queryUtils)
    }

    private QueryUtils obtenerQueryGuardar(Empresa empresa) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildInsertRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("nombre"), empresa.getNombre())

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.EMPRESA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void actualizar(Empresa empresa) {
        QueryUtils queryUtils = this.obtenerQueryActualizar(empresa)
        this.actualizar(queryUtils)
    }

    private QueryUtils obtenerQueryActualizar(Empresa empresa) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildUpdateRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("nombre"), empresa.getNombre())
        queryUtils.addWhereParam(COLUMNS.get("id"), empresa.getId())

        return queryUtils
    }
}
