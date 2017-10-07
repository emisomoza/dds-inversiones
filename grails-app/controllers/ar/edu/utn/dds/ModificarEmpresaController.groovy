package ar.edu.utn.dds

class ModificarEmpresaController {

    def empresaService

    def index() {
        def empresas = empresaService.obtener(Long.parseLong(params.empresa))

        render(
                view: "/modificarEmpresa",
                model: [
                        empresa: empresas
                ]
        )
    }

    def save_periodo() {

        Long idEmpresa = Long.parseLong(params.empresa)
        Date fechaDesde = params.fechaDesde
        Date fechaHasta = params.fechaHasta

        empresaService.agregarPeriodo(idEmpresa, fechaDesde, fechaHasta)
        def empresas = empresaService.obtener(idEmpresa)
        //def periodos = empresaService.obtenerPeriodos(idEmpresa)

        render(
            view: "/modificarEmpresa",
            model: [
                    periodos: empresas
            ]
        )
    }
}
