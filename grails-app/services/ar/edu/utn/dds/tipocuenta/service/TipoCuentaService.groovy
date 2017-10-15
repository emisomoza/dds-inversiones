package ar.edu.utn.dds.tipocuenta.service

import ar.edu.utn.dds.model.TipoCuenta

class TipoCuentaService {

    def tipoCuentaRepository

    def listar() {
        return this.listar(new TipoCuenta())
    }

    def listar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.listar(tipoCuenta)
    }

    def obtener(Long id) {
        return this.tipoCuentaRepository.obtener(id)
    }

    def guardar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.guardar(tipoCuenta)
    }

    def actualizar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.actualizar(tipoCuenta)
    }

}
