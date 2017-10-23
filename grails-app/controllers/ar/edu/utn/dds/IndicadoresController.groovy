package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.IndicadorExistenteException
import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class IndicadoresController {
    def indicadorService

    def save() {
        try {
            Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)
            indicadorService.guardar(nuevoIndicador)

            render(
                    view: "/indicadores/indicadorAgregado",
                    model: [
                            indicador: nuevoIndicador
                    ]
            )
        } catch(IndicadorExistenteException e) {
            render(
                    view: "/errorGenericoBack",
                    model: [
                            text: "Indicador existente",
                            buttonText: "Volver"
                    ]
            )
        }
    }
}
