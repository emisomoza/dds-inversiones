package ar.edu.utn.dds

class ModificarEmpresaController {

    def empresaService
    def periodoService

    def index() {
        def empresas = empresaService.obtener(Long.parseLong(params.empresa))

        def periodos = periodoService.obtenerPeriodos(Long.parseLong(params.empresa))

        render(
                view: "/modificarEmpresa",
                model: [
                        periodos: periodos,
                        empresa: empresas
                ]
        )
    }

    def save_periodo() {

        Long idEmpresa = Long.parseLong(params.empresa)
        Date fechaDesde = params.fechaDesde
        Date fechaHasta = params.fechaHasta

        periodoService.guardar(idEmpresa, fechaDesde, fechaHasta)
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
