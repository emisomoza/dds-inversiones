package ar.edu.utn.dds

class ModificarEmpresaController {

    def empresaService
    def periodoService

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

        periodoService.guardar(idEmpresa, fechaDesde, fechaHasta)
        def empresa = empresaService.obtener(idEmpresa)
        //def periodos = empresaService.obtenerPeriodos(idEmpresa)

        render(
            view: "/modificarEmpresa",
            model: [
                    periodos: null,
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
