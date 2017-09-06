package ar.edu.utn.dds.mappers

import ar.edu.utn.dds.model.Cuenta
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class CuentaMapper implements RowMapper {

    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Cuenta cuenta  = new Cuenta()

        cuenta.setId(resultSet.getLong("cuenta_id"))
        cuenta.setNombre(resultSet.getString("cuenta_nombre"))
        cuenta.setValor(resultSet.getDouble("cuenta_valor"))

        return cuenta
    }
}
