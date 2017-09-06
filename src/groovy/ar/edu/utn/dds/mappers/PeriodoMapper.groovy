package ar.edu.utn.dds.mappers

import ar.edu.utn.dds.model.Periodo
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class PeriodoMapper implements RowMapper {
    
    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Periodo periodo = new Periodo()
        
        periodo.setId(resultSet.getLong("periodo_id"))
        periodo.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate())
        periodo.setFechaFin(resultSet.getDate("fecha_fin").toLocalDate())
        
        return periodo
    }
}
