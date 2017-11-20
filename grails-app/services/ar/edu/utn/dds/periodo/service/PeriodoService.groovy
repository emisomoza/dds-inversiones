package ar.edu.utn.dds.periodo.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo

import java.util.function.Function
import java.util.stream.Collectors

class PeriodoService {

    def periodoRepositoryService
    def cuentaService

    def listar() {
        return this.listar(new Periodo())
    }

    def listar(Periodo periodo) {
        return this.periodoRepositoryService.listar(periodo)
    }

    def listarPopulado(Empresa empresa) {
        Map<Long, List<Cuenta>> cuentasMap = this.cuentaService.listarPopulado(empresa).stream()
                .collect(Collectors.groupingBy({cue -> cue.getPeriodo()} as Function<Cuenta, Long>))

        return cuentasMap.entrySet().stream()
                .map({entry ->
                    Periodo nuevoPeriodo = this.obtener(entry.getKey())
                    nuevoPeriodo.setCuentas(entry.getValue())
                    return nuevoPeriodo
                })
                .collect(Collectors.toList())
    }

    def obtener(Long id) {
        return this.periodoRepositoryService.obtener(id)
    }

    def guardar(Periodo periodo) {
        this.periodoRepositoryService.guardar(periodo)
    }

    def actualizar(Periodo periodo) {
        this.periodoRepositoryService.actualizar(periodo)
    }
}