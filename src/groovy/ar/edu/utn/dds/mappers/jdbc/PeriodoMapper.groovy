package ar.edu.utn.dds.mappers.jdbc

import ar.edu.utn.dds.model.Periodo
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDate

class PeriodoMapper implements RowMapper {

    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Periodo periodo = new Periodo()

        periodo.setId(resultSet.getLong("periodo_id"))
        periodo.setFechaInicio(LocalDate.parse(resultSet.getString("fecha_inicio")))
        periodo.setFechaFin(LocalDate.parse(resultSet.getString("fecha_fin")))

        return periodo
    }
}
