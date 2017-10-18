package ar.edu.utn.dds

import ar.edu.utn.dds.model.Metodologia
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class IndexController {

    def empresaService

    def home() {
        render(
                view: "/index",
                model: [

                ]
        )

    }

    def compararEmpresas() {
        def empresas = empresaService.listar()
        ArrayList<Metodologia> metodologias = [new Metodologia(nombre: "Metodología 1"), new Metodologia(nombre: "Metodología 2")]

        render(
                view: "/consultas",
                model: [
                        empresas: empresas,
                        metodologias: metodologias
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
