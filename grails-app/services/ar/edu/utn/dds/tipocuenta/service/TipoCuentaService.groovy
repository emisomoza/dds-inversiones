package ar.edu.utn.dds.tipocuenta.service

import ar.edu.utn.dds.model.TipoCuenta

class TipoCuentaService {

    def tipoCuentaRepositoryService

    def existe(String descripcion) {
        return this.tipoCuentaRepositoryService.existe(descripcion)
    }

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

    def importarTipoCuentas(List<TipoCuenta> tipoCuentas, Boolean validarExistencia) {
        tipoCuentas.forEach({tipoCuenta ->
            if(!(validarExistencia && this.existe(tipoCuenta.getDescripcion())))
                this.guardar(tipoCuenta)
        })
    }

}
