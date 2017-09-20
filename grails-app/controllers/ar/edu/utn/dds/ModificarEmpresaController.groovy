package ar.edu.utn.dds

class ModificarEmpresaController {

    def index() {
        def empresaService
        def empresas = empresaService.obtener(params.empresa)

        render(
                view: "/modificarEmpresa",
                model: [
                        empresa: empresa
                ]
        )
    }

    def save_periodo() {

        def empresa = params.empresa
        def periodos = params.empresa
        render(
                view: "/modificarEmpresa",
                model: [
                        empresa: empresa
                ]
        )
    }
}
