package ar.edu.utn.dds.mappers

import ar.edu.utn.dds.model.TipoCuenta
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class TipoCuentaMapper implements RowMapper {

    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TipoCuenta tipoCuenta = new TipoCuenta()

        tipoCuenta.setId(resultSet.getLong("tipo_id"))
        tipoCuenta.setDescripcion(resultSet.getString("tipo_descripcion"))

        return tipoCuenta
    }
}