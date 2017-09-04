package ar.edu.utn.dds

import ar.edu.utn.dds.Periodo
import ar.edu.utn.dds.mappers.PeriodoMapper
import grails.transaction.Transactional

@Transactional
class PeriodoService {

    def jdbcTemplate

    def listar() {
        String query = "SELECT * FROM PERIODO"
        return jdbcTemplate.queryForObject(query, new PeriodoMapper())
    }

    def obtener(Long id) {
        String query = "SELECT * FROM PERIODO WHERE periodo_id = ?"
        return jdbcTemplate.queryForObject(query, [id] as Object[], new PeriodoMapper())
    }

    def guardar(Periodo periodo) {
        String query = "INSERT INTO PERIODO (fecha_fin, fecha_inicio) VALUES (?, ?)"
        return jdbcTemplate.update(query, periodo.getFechaFin(), periodo.getFechaInicio())
    }

    def actualizar(Periodo periodo) {
        String query = "UPDATE PERIODO SET fecha_fin = ?, fecha_inicio = ? WHERE periodo_id = ?"
        jdbcTemplate.update(query, periodo.getFechaFin(), periodo.getFechaInicio(), periodo.getId())
    }
}
