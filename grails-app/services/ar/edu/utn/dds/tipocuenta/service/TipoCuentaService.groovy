package ar.edu.utn.dds.tipocuenta.service

import ar.edu.utn.dds.model.TipoCuenta

class TipoCuentaService {

    def tipoCuentaRepositoryService

    def listar() {
        return this.listar(new TipoCuenta())
    }

    def listar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepositoryService.listar(tipoCuenta)
    }

    def obtener(Long id) {
        return this.tipoCuentaRepositoryService.obtener(id)
    }

    def guardar(TipoCuenta tipoCuenta) {
        tipoCuenta.validarConsistencia()
        return this.tipoCuentaRepositoryService.guardar(tipoCuenta)
    }

    def actualizar(TipoCuenta tipoCuenta) {
        tipoCuenta.validarConsistencia()
        return this.tipoCuentaRepositoryService.actualizar(tipoCuenta)
    }

}
