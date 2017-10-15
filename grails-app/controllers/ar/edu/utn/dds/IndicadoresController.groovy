package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador


class IndicadoresController {
    def indicadorRepositoryService

    def save() {
        Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)

        indicadorRepositoryService.guardarIndicador(nuevoIndicador)

        render(
                view: "/indicadores/indicadorAgregado",
                model: [
                    indicador: nuevoIndicador
                ]
        )
    }
}
