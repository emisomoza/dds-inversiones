package ar.edu.utn.dds.tipocuenta.service

import ar.edu.utn.dds.model.TipoCuenta

class TipoCuentaService {

    def tipoCuentaRepositoryService

    def listarTodo() {
        return tipoCuentaRepositoryService.listarTodo()
    }

    def listar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepositoryService.listar(tipoCuenta)
    }

    def obtener(Long id) {
        return this.tipoCuentaRepositoryService.obtener(id)
    }

    def guardar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepositoryService.guardar(tipoCuenta)
    }

    def actualizar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepositoryService.actualizar(tipoCuenta)
    }

}
