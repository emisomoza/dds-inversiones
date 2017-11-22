package ar.edu.utn.dds.periodo.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo

import java.time.LocalDate
import java.util.function.Function
import java.util.stream.Collectors

class PeriodoService {

    def periodoRepositoryService
    def cuentaService

    def existe(LocalDate fechaDesde, LocalDate fechaHasta) {
        return this.periodoRepositoryService.existe(fechaDesde, fechaHasta)
    }

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
        periodo.validarConsistencia()
        return this.periodoRepositoryService.guardar(periodo)
    }

    def actualizar(Periodo periodo) {
        periodo.validarConsistencia()
        this.periodoRepositoryService.actualizar(periodo)
    }

    def importarPeriodos(List<Periodo> periodos, Boolean validarExistencia) {
       periodos.forEach({periodo ->
            if(!(validarExistencia && this.existe(periodo.getFechaInicio(), periodo.getFechaFin())))
                this.guardar(periodo)
        })
    }
}