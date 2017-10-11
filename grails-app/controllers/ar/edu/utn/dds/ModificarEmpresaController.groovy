package ar.edu.utn.dds

import java.time.LocalDate

class ModificarEmpresaController {

    def empresaService
    def periodoService

    def index() {
        def empresa = empresaService.obtener(Long.parseLong(params.empresa))

        def periodos = periodoService.obtenerPeriodos(Long.parseLong(params.empresa))

        render(
                view: "/modificarEmpresa",
                model: [
                        periodos: periodos,
                        empresa: empresa
                ]
        )
    }

    def save_periodo() {

        Long idEmpresa = Long.parseLong(params.empresa)
        Date fechaDesdeDate = params.fechaDesde
        Date fechaHastaDate = params.fechaHasta

        LocalDate fechaDesdeLocalDate = LocalDate.of(fechaDesdeDate.calendarDate.getYear(), fechaDesdeDate.calendarDate.getMonth(), fechaDesdeDate.calendarDate.getDayOfMonth())
        LocalDate fechaHastaLocalDate = LocalDate.of(fechaHastaDate.calendarDate.getYear(), fechaHastaDate.calendarDate.getMonth(), fechaHastaDate.calendarDate.getDayOfMonth())

        periodoService.guardar(idEmpresa, fechaDesdeLocalDate, fechaHastaLocalDate)
        def empresa = empresaService.obtener(idEmpresa)

        def periodos = periodoService.obtenerPeriodos(idEmpresa)

        render(
            view: "/modificarEmpresa",
            model: [
                    periodos: periodos,
                    empresa: empresa
            ]
        )
    }

    def modificarPeriodo(){
        render(
                view: "/modificarPeriodo",
                model: [

                ]
        )
    }
}
