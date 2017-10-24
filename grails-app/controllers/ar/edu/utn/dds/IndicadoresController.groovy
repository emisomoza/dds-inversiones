package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.IndicadorExistenteException
import ar.edu.utn.dds.exceptions.IndicadorInvalidoException
import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class IndicadoresController {

    def indicadorService

    def save() {

        def view
        def model

        try {

            Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)
            indicadorService.guardar(nuevoIndicador)

            view = "/indicadores/indicadorAgregado"
            model = [
                indicador: nuevoIndicador
            ]

        } catch(IndicadorInvalidoException | IndicadorExistenteException e) {
            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
