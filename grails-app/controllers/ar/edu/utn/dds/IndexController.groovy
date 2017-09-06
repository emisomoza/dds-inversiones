package ar.edu.utn.dds

import ar.edu.utn.dds.model.ContenedorEmpresas

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
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        def empresas = contenedor.getEmpresas()

        render(
                view: "/consultas",
                model: [
                        empresas: empresas
                ]
        )
    }

    def agregarCuentas() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        def empresas = contenedor.getEmpresas()
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
        render(
                view: "/metodologias",
                model: [

                ]
        )
    }
}
