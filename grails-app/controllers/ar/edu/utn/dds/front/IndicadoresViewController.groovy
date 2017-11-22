package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.IndicadorExistenteException
import ar.edu.utn.dds.exceptions.IndicadorInvalidoException
import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class IndicadoresViewController {

    def indicadorService

    def save() {

        def view
        def model

        try {
            Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion, params.visibilidad)
            indicadorService.guardar(nuevoIndicador)

            //TODO reemplazar por un texto como en metodologias
            view = "/indicadores/indicadorAgregado"
            model = [
                indicador: nuevoIndicador
            ]

        } catch(IndicadorInvalidoException | IndicadorExistenteException e) {
            log.error(e.getMessage(), e)
            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
