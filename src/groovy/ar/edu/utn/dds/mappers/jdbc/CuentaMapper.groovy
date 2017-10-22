package ar.edu.utn.dds.mappers.jdbc

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.TipoCuenta
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class CuentaMapper implements RowMapper {

    @Override
    Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TipoCuenta tipoCuenta = (TipoCuenta) (new TipoCuentaMapper()).mapRow(resultSet, i)

        Cuenta cuenta = new Cuenta()
        cuenta.setEmpresa(resultSet.getLong("empresa_id"))
        cuenta.setPeriodo(resultSet.getLong("periodo_id"))
        cuenta.setTipo(tipoCuenta)
        cuenta.setValor(resultSet.getDouble("cuenta_valor"))

        return cuenta
    }
}
