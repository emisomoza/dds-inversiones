package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.mappers.EmpresaMapper
import ar.edu.utn.dds.model.Empresa
import grails.transaction.Transactional
import org.springframework.dao.DataAccessException
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
        } catch(Exception e) {
            throw new InversionesException(String.format("Error verificando existencia de empresa: %l", id), e.getCause())
        }

    }

    @Transactional(readOnly = true)
    def listar() {
        def result
        log.info("Listando empresas")
        String query = "SELECT * FROM EMPRESA"
        try {
            result = jdbcTemplate.query(query, new EmpresaMapper())
            log.info(String.format("Empresas listadas: %l", result.size()))
            return result
        } catch(Exception e) {
            throw new InversionesException("Error listando empresas", e.getCause())
        }
    }

    @Transactional(readOnly = true)
    def obtener(Long id) {
        if(this.existe(id)) {
            String query = "SELECT * FROM EMPRESA WHERE empresa_id = ?"
            return jdbcTemplate.queryForObject(query, [id] as Object[], new EmpresaMapper())
        } else {
            throw new RecursoNoEncontradoException(String.format("No se encontro la empresa %l", id))
        }
    }

    def guardar(Empresa empresa) {
        def result
        log.info("Guardando empresa")
        String query = "INSERT INTO EMPRESA (empresa_nombre) VALUES (?)"
        try {
            result = jdbcTemplate.update(query, empresa.getNombre())
            log.info(String.format("Empresa guardada: %l", result))
            return result
        } catch(Exception e) {
            throw new InversionesException("Error guardando empresa", e.getCause())
        }
    }

    def guardarCompuesto(Empresa empresa) {
        def result
        log.info("Guardando empresa compuesta")
        String query = "INSERT INTO EMPRESA (empresa_nombre) VALUES (?)"
        try {
            result = jdbcTemplate.update(query, empresa.getNombre())
            log.info(String.format("Empresa guardada: %l", result))
            return result
        } catch(Exception e) {
            throw new InversionesException("Error guardando empresa", e.getCause())
        }
    }

    def actualizar(Empresa empresa) {
        log.info(String.format("Actualizando empresa %l", empresa.id))
        String query = "UPDATE EMPRESA SET empresa_nombre = ? WHERE empresa_id = ?"
        try {
            jdbcTemplate.update(query, empresa.getNombre(), empresa.getId())
            log.info(String.format("Empresa actualizada: %l", empresa.id))
        } catch(Exception e) {
            throw new InversionesException(String.format("Error actualizando empresa: %l", empresa.id), e.getCause())
        }
    }

    def importarCuentas(File archivo) {
        List<Map<String, String>> mapasCuentas = this.cuentaService.parsearArchImportCuentas(archivo)

    }



}
