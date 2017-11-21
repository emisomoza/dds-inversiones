package ar.edu.utn.dds.cuenta.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa

class CuentaService {

    def cuentaRepositoryService

    def existe(Long empresa, Long periodo, Long tipo) {
        return this.cuentaRepositoryService.existe(empresa, periodo, tipo)
    }

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

    def importarCuentas(List<Cuenta> cuentas) {
        cuentas.forEach({cuenta ->
            if(this.existe(cuenta.getEmpresa(), cuenta.getPeriodo(), cuenta.getTipo().getId()))
                this.actualizar(cuenta)
            else
                this.guardar(cuenta)

        })
    }
}
