package ar.edu.utn.dds.cuenta.repository

import ar.edu.utn.dds.jdbc.DefaultJDBCRepositoryService
import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.mappers.CuentaMapper
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.utils.queries.QueryUtils
import ar.edu.utn.dds.utils.queries.builders.RootStatementBuilder
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.RowMapper

import java.util.stream.Collectors

class CuentaRepositoryService extends DefaultJDBCRepositoryService<Cuenta> {

    private static final Map<String, String> COLUMNS = {
        Map columns = new HashMap()
        columns.put("empid", "EMPRESA_ID")
        columns.put("perid", "PERIODO_ID")
        columns.put("cueid", "CUENTA_TIPO")
        columns.put("valor", "CUENTA_VALOR")
        return columns
    }.call()

    private static final String TABLE = "CUENTA"

    private RowMapper mapper = new CuentaMapper()

    Boolean existe(Long empId, Long perId, Long cueId) {
        QueryUtils queryUtils = this.obtenerQueryExiste(empId, perId, cueId)
        this.existe(queryUtils)
    }

    private QueryUtils obtenerQueryExiste(Long empId, Long perId, Long cueId) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement("1", TABLE))
        queryUtils.addWhereParam(COLUMNS.get("empid"), empId)
        queryUtils.addWhereParam(COLUMNS.get("perid"), perId)
        queryUtils.addWhereParam(COLUMNS.get("cueid"), cueId)

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    List<Cuenta> listar(Cuenta cuenta) {
        QueryUtils queryUtils = this.obtenerQueryListar(cuenta)
        return this.listar(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryListar(Cuenta cuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(
                COLUMNS.values().stream().collect(Collectors.joining(", ")) + ", TIPO_ID, TIPO_DESCRIPCION ",
                TABLE + " JOIN TIPO_CUENTA ON CUENTA_TIPO = TIPO_ID "))
        queryUtils.addWhereParam(COLUMNS.get("empid"), cuenta.getEmpresa())
        queryUtils.addWhereParam(COLUMNS.get("perid"), cuenta.getPeriodo())
        if(cuenta.getTipo() != null)
            queryUtils.addWhereParam(COLUMNS.get("cueid"), cuenta.getTipo().getId())
        queryUtils.addWhereParam(COLUMNS.get("valor"), cuenta.getValor())

        return queryUtils
    }

    @Cacheable(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Cuenta obtener(Long empId, Long perId, Long cueId) {
        QueryUtils queryUtils = this.obtenerQueryObtener(empId, perId, cueId)
        return this.obtener(queryUtils, mapper)
    }

    private QueryUtils obtenerQueryObtener(Long empId, Long perId, Long cueId) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildSelectRootStatement(
                COLUMNS.values().stream().collect(Collectors.joining(", ")) + ", TIPO_ID, TIPO_DESCRIPCION ",
                TABLE + " JOIN TIPO_CUENTA ON CUENTA_TIPO = TIPO_ID "))
        queryUtils.addWhereParam(COLUMNS.get("empid"), empId)
        queryUtils.addWhereParam(COLUMNS.get("perid"), perId)
        queryUtils.addWhereParam(COLUMNS.get("cueid"), cueId)

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Cuenta cuenta) {
        QueryUtils queryUtils = this.obtenerQueryGuardar(cuenta)
        this.guardar(queryUtils)
    }

    private QueryUtils obtenerQueryGuardar(Cuenta cuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildInsertRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("empid"), cuenta.getEmpresa())
        queryUtils.addParam(COLUMNS.get("perid"), cuenta.getPeriodo())
        queryUtils.addParam(COLUMNS.get("cueid"), cuenta.getTipo().getId())
        queryUtils.addParam(COLUMNS.get("valor"), cuenta.getValor())

        return queryUtils
    }

    @CacheEvict(cacheNames = CacheData.CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void actualizar(Cuenta cuenta) {
        QueryUtils queryUtils = this.obtenerQueryActualizar(cuenta)
        this.actualizar(queryUtils)
    }

    private QueryUtils obtenerQueryActualizar(Cuenta cuenta) {
        QueryUtils queryUtils = new QueryUtils()

        queryUtils.setRootStatement(RootStatementBuilder.buildUpdateRootStatement(TABLE))
        queryUtils.addParam(COLUMNS.get("valor"), cuenta.getValor())
        queryUtils.addWhereParam(COLUMNS.get("empid"), cuenta.getEmpresa())
        queryUtils.addWhereParam(COLUMNS.get("perid"), cuenta.getPeriodo())
        queryUtils.addWhereParam(COLUMNS.get("cueid"), cuenta.getTipo().getId())

        return queryUtils
    }
}