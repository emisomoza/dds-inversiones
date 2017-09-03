package ar.edu.utn.dds.mappers

import ar.edu.utn.dds.Empresa
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class EmpresaMapper implements RowMapper {


    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Empresa empresa = new Empresa()

        empresa.setId(resultSet.getLong("empresa_id"))
        empresa.setNombre(resultSet.getString("empresa_nombre"))

        return empresa
    }
}
