package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador

class IndexController {

    def empresaService;
    def indicadorService;

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

    def crearMetodologias() {
        ArrayList<Indicador> indicadores
        indicadores = indicadorService.getIndicadores()

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores
                ]
        )
    }
}
