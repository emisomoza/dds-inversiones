package ar.edu.utn.dds

class IndexController {

    def empresaService;

    def home() {
        render(
                view: "/index",
                model: [

                ]
        )

    }

    def compararEmpresas() {
        def empresas = empresaService.listar()

        render(
                view: "/consultas",
                model: [
                        empresas: empresas
                ]
        )
    }

    def agregarCuentas() {
        def empresas = empresaService.listar()
        render(
                view: "/empresas",
                model: [
                        empresas: empresas
                ]
        )
    }

    def agregarIndicadores() {
        render(
                view: "/indicadores/cargaIndicadores",
                model: [

                ]
        )
    }
}
