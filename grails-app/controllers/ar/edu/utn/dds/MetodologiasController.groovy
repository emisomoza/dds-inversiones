package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class MetodologiasController {

    def indicadorService

    def index() { }

    def crear() {
        ArrayList<Indicador> indicadores
        indicadores = indicadorService.listar().sort{it?.nombre}

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores
                ]
        )
    }

    def save() {
        //Guardar metodología

        params

        ArrayList<Indicador> indicadores
        indicadores = indicadorService.listar()

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores,
                        text: "Metodología $params.nomMetodologia guardada con éxito."
                ]
        )
    }
}
