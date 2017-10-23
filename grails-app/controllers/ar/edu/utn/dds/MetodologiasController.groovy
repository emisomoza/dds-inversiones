package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Metodologia
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class MetodologiasController {

    def indicadorService
    def metodologiaService

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
        try{
            //Completar con los params del FE
            ArrayList<Indicador> indicadores
            indicadores = indicadorService.listar().sort{it?.nombre}
            metodologiaService.guardar(new Metodologia())

            render(
                    view: "/metodologias",
                    model: [
                            indicadores: indicadores,
                            text: "Metodología $params.nomMetodologia guardada con éxito."
                    ]
            )
        } catch(MetodologiaExistenteException e) {
            render(
                    view: "/errorGenericoBack",
                    model: [
                            text: "Metodología existente",
                            buttonText: "Volver"
                    ]
            )
        }
    }
}
