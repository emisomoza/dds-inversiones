package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class IndicadoresController {
    def indicadorRepositoryService

    def save() {
        Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)

        indicadorRepositoryService.guardar(nuevoIndicador)

        render(
                view: "/indicadores/indicadorAgregado",
                model: [
                    indicador: nuevoIndicador
                ]
        )
    }
}
