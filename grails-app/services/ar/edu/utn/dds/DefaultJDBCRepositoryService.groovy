package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.exceptions.SQLInaccesibleException
import ar.edu.utn.dds.utils.queries.QueryUtils
import grails.transaction.Transactional
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper

import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Timestamp
import java.util.stream.Collectors

@Transactional
class DefaultJDBCRepositoryService<T> {

    def jdbcTemplate

    void setPSArgs(PreparedStatement ps, List<Object> args) throws Exception {
        int i = 1

        for (Object arg : args) {
            if (!Optional.ofNullable(arg).isPresent()) {
                throw new Exception("Unsupported argument " + arg + " of type NULL")
            }

            if (arg instanceof String) {
                ps.setString(i++, (String) arg)
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg)
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg)
            } else if (arg instanceof Date) {
                ps.setDate(i++, (Date) arg)
            } else if (arg instanceof Timestamp) {
                ps.setTimestamp(i++, (Timestamp) arg)
            }  else {
                throw new Exception("Unsupported argument " + arg + " of type " + arg.getClass());
            }
        }
    }

    @Transactional(readOnly = true)
    Boolean existe(QueryUtils queryUtils) {
        def result
        log.info(String.format("Verificando existencia de %s", T.getClass().getName()))
        log.debug(String.format("Query: %s", queryUtils.getSqlQuery()))
        try {
            result = jdbcTemplate.query(queryUtils.getSqlQuery(), queryUtils.getSqlArguments().toArray(), new ResultSetExtractor<Boolean>() {
                @Override
                Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                    boolean r = ++rs
                    return r
                }
            })
            log.info(String.format("Resultado de verificacion de existencia de %s: %l", T.getClass().getName(), result))
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException(String.format("Error al verificar existencia de %s",T.getClass().getName()), e.getCause())
        }
    }

    @Transactional(readOnly = true)
    List<T> listar(QueryUtils queryUtils, RowMapper<T> mapper) {
        def result
        log.info(String.format("Listando %s", T.getClass().getName()))
        log.debug(String.format("Query: %s", queryUtils.getSqlQuery()))
        try {
            result = jdbcTemplate.query(queryUtils.getSqlQuery(), queryUtils.getSqlArguments().toArray(), mapper)
            log.info(String.format("Listado de %s existoso", T.getClass().getName()))
            return result
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException(String.format("Error al listar %s", T.getClass().getName()), e.getCause())
        }
    }

    @Transactional(readOnly = true)
    T obtener(QueryUtils queryUtils, RowMapper<T> mapper) {
        T result
        log.info(String.format("Obteniendo %s", T.getClass().getName()))
        log.debug(String.format("Query: %s", queryUtils.getSqlQuery()))
        try {
            result = jdbcTemplate.queryForObject(queryUtils.getSqlQuery(), queryUtils.getSqlArguments().toArray(), mapper)
            log.info(String.format("Obtencion de %s exitoso", T.getClass().getName()))
            return result
        } catch(EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException(String.format("No se encontro %s", T.getClass().getName()), e.getCause())
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException(String.format("Error al obtener %s", T.getClass().getName()), e.getCause())
        }
    }

    void guardar(QueryUtils queryUtils) {
        log.info(String.format("Guardando %s", T.getClass().getName()))
        log.debug(String.format("Query: %s", queryUtils.getSqlQuery()))
        String query =
                queryUtils.getSqlQuery() + "( " + queryUtils.getParams().stream().collect(Collectors.joining(", ")) + " ) VALUES (" +
                queryUtils.getParams().collect {p -> " ? "}.stream().collect(Collectors.joining(", "))
        try {
            jdbcTemplate.update(query, { ps ->
                try {
                    setPSArgs(ps, queryUtils.getSqlArguments())
                } catch (Exception e) {
                    throw new SQLException(e)
                }
            })
            log.info(String.format("%s guardado", T.getClass().getName()))
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException(String.format("Error al guardar %s", T.getClass().getName()), e.getCause())
        }
    }

    void actualizar(QueryUtils queryUtils) {
        log.info(String.format("Actualizando %s",T.getClass().getName()))
        log.debug(String.format("Query %s", queryUtils.getSqlQuery()))
        try {
            jdbcTemplate.update(queryUtils.getSqlQuery(), {ps ->
                try {
                    setPSArgs(ps, queryUtils.getSqlArguments())
                } catch (Exception e) {
                    throw new SQLException(e)
                }
            })
            log.info(String.format("Empresa actualizada: %l", empresa.id))
        } catch(DataAccessException e) {
            throw new SQLInaccesibleException("Error al actualizar empresa " + empresa.getId(), e.getCause())
        }
    }
}
