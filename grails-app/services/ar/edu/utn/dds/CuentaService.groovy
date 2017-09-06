package ar.edu.utn.dds

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.mappers.CuentaMapper
import grails.transaction.Transactional

@Transactional
class CuentaService {

    def jdbcTemplate

    def listar() {
        String query = "SELECT * FROM CUENTA"
        return jdbcTemplate.queryForObject(query, new CuentaMapper())
    }

    def obtener(Long id) {
        String query = "SELECT * FROM CUENTA WHERE cuenta_id = ?"
        return jdbcTemplate.queryForObject(query, [id] as Object[], new CuentaMapper())
    }

    def guardar(Cuenta cuenta) {
        String query = "INSERT INTO CUENTA (cuenta_nombre, cuenta_valor) VALUES (?, ?)"
        return jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor())
    }

    def actualizar(Cuenta cuenta) {
        String query = "UPDATE CUENTA SET cuenta_nombre = ?, cuenta_valor = ? WHERE cuenta_id = ?"
        jdbcTemplate.update(query, cuenta.getNombre(), cuenta.getValor(), cuenta.getId())
    }
}
