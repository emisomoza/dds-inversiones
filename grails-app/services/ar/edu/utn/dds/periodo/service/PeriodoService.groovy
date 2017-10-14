package ar.edu.utn.dds.periodo.service

import ar.edu.utn.dds.model.Periodo

class PeriodoService {

    def periodoRepositoryService

    def listar() {
        this.listar(new Periodo())
    }

    def listar(Periodo periodo) {
        return this.periodoRepositoryService.listar(periodo)
    }

    def obtener(Long id) {
        return this.obtener(id)
    }

    def guardar(Periodo periodo) {
        this.periodoRepositoryService.guardar(periodo)
    }

    def actualizar(Periodo periodo) {
        this.periodoRepositoryService.actualizar(periodo)
    }
}
