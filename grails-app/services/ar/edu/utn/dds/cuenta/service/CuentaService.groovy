package ar.edu.utn.dds.cuenta.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa

class CuentaService {

    def cuentaRepositoryService

    def listar() {
        return this.listar(new Cuenta())
    }

    def listar(Cuenta cuenta) {
        return this.cuentaRepositoryService.listar(cuenta)
    }

    def listarPopulado(Empresa empresa) {
        Cuenta queryCuenta = new Cuenta()
        queryCuenta.setEmpresa(empresa.getId())
        return this.listar(queryCuenta)
    }

    def obtener(Long empId, Long perId, Long cueId) {
        return this.cuentaRepositoryService.obtener(empId, perId, cueId)
    }

    def guardar(Cuenta cuenta) {
        cuenta.validarConsistencia()
        return this.cuentaRepositoryService.guardar(cuenta)
    }

    def actualizar(Cuenta cuenta) {
        cuenta.validarConsistencia()
        return this.cuentaRepositoryService.actualizar(cuenta)
    }
}
