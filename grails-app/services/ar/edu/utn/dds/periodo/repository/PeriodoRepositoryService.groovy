package ar.edu.utn.dds.periodo.repository

import ar.edu.utn.dds.jdbc.DefaultJDBCRepositoryService
import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.mappers.PeriodoMapper
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.utils.queries.QueryUtils
import ar.edu.utn.dds.utils.queries.builders.RootStatementBuilder
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.RowMapper

import java.sql.Date

class PeriodoRepositoryService extends DefaultJDBCRepositoryService<Periodo> {

    private static final Map<String, String> COLUMNS = {
        Map columns = new HashMap()
        columns.put("id", "PERIODO_ID")
        columns.put("finicio", "FECHA_INICIO")
        columns.put("ffin", "FECHA_FIN")
        return columns
    }.call()

    private static final String TABLE = "PERIODO"

    private RowMapper mapper = new PeriodoMapper()

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

    @Cacheable(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    List<Periodo> listar(Periodo periodo) {
        QueryUtils queryUtils = this.obtenerQueryListar(periodo)
        return this.listar(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryListar(Periodo periodo) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), periodo.getId())
        queryUtils.addWhereParam(COLUMNS.get("finicio"), Date.valueOf(periodo.getFechaInicio()))
        queryUtils.addWhereParam(COLUMNS.get("ffin"), Date.valueOf(periodo.getFechaFin()))

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Periodo obtener(Long id) {
        QueryUtils queryUtils = this.obtenerQueryObtener(id)
        return this.obtener(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryObtener(Long id) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(COLUMNS.values().toList(), TABLE))
        queryUtils.addWhereParam(COLUMNS.get("id"), id)

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Periodo periodo) {
        QueryUtils queryUtils = this.obtenerQueryGuardar(periodo)
        this.guardar(queryUtils)
    }

    private QueryUtils obtenerQueryGuardar(Periodo periodo) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildInsertRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("finicio"), Date.valueOf(periodo.getFechaInicio()))
        queryUtils.addParam(COLUMNS.get("ffin"), Date.valueOf(periodo.getFechaFin()))

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.PERIODO_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void actualizar(Periodo periodo) {
        QueryUtils queryUtils = this.obtenerQueryActualizar(periodo)
        this.actualizar(queryUtils)
    }

    private QueryUtils obtenerQueryActualizar(Periodo periodo) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildUpdateRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("finicio"), Date.valueOf(periodo.getFechaInicio()))
        queryUtils.addParam(COLUMNS.get("ffin"), Date.valueOf(periodo.getFechaFin()))
        queryUtils.addWhereParam(COLUMNS.get("id"), periodo.getId())

        return queryUtils
    }
}
