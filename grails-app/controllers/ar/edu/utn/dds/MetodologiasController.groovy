package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.exceptions.MetodologiaInvalidaException
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

        def view
        def model

        try{

            //TODO Completar con los params del FE
            ArrayList<Indicador> indicadores = indicadorService.listar().sort{it?.nombre}
            metodologiaService.guardar(new Metodologia())

            view = "/metodologias"
            model = [
                    indicadores: indicadores,
                    text: "Metodología $params.nomMetodologia guardada con éxito."
            ]

        } catch(MetodologiaInvalidaException | MetodologiaExistenteException e) {

            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
