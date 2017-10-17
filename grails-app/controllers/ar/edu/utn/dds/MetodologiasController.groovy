package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class MetodologiasController {

    def indicadorService

    def index() { }

    def crear() {
        ArrayList<Indicador> indicadores
        indicadores = indicadorService.listarTodo().sort{it?.nombre}

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
        indicadores = indicadorService.listarTodo()

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores,
                        text: "Metodología $params.nomMetodologia guardada con éxito."
                ]
        )
    }
}
