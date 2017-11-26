package ar.edu.utn.dds.tipocuenta.repository

import ar.edu.utn.dds.jdbc.DefaultJDBCRepositoryService
import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.mappers.jdbc.TipoCuentaMapper
import ar.edu.utn.dds.model.TipoCuenta
import ar.edu.utn.dds.utils.queries.QueryUtils
import ar.edu.utn.dds.utils.queries.builders.RootStatementBuilder
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.RowMapper

class TipoCuentaRepositoryService extends DefaultJDBCRepositoryService<TipoCuenta> {

    private static final Map<String, String> COLUMNS = {
        Map columns = new HashMap()
        columns.put("id", "TIPO_ID")
        columns.put("descripcion", "TIPO_DESCRIPCION")
        return columns
    }.call()

    private static final String TABLE = "TIPO_CUENTA"

    private RowMapper mapper = new TipoCuentaMapper()

    @Override
    String nombreEntidad() {
        return TipoCuenta.getName()
    }

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

    Boolean existe(String descripcion) {
        QueryUtils queryUtils = this.obtenerQueryExiste(descripcion)
        this.existe(queryUtils)
    }

    private QueryUtils obtenerQueryExiste(String descripcion) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement("1", TABLE))
        queryUtils.addWhereParam(COLUMNS.get("descripcion"), descripcion)

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    List<TipoCuenta> listar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = this.obtenerQueryListar(tipoCuenta)
        return this.listar(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryListar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), tipoCuenta.getId())
        queryUtils.addWhereParam(COLUMNS.get("descripcion"), tipoCuenta.getDescripcion())

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    TipoCuenta obtener(Long id) {
        QueryUtils queryUtils = this.obtenerQueryObtener(id)
        return this.obtener(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryObtener(Long id) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), id)

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    Long guardar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = this.obtenerQueryGuardar(tipoCuenta)
        return this.guardarYObtenerId(queryUtils)
    }

    private QueryUtils obtenerQueryGuardar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildInsertRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("descripcion"), tipoCuenta.getDescripcion())

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void actualizar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = this.obtenerQueryActualizar(tipoCuenta)
        this.actualizar(queryUtils)
    }

    private QueryUtils obtenerQueryActualizar(TipoCuenta tipoCuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildUpdateRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("descripcion"), tipoCuenta.getDescripcion())
        queryUtils.addWhereParam(COLUMNS.get("id"), tipoCuenta.getId())

        return queryUtils
    }
}
