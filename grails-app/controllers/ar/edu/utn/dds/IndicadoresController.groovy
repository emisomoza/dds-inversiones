package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador

class IndicadoresController {
    def indicadorService

    def save() {
        Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)

        indicadorService.guardarIndicador(nuevoIndicador)

        render(
                view: "/indicadorAgregado",
                model: [
                    indicador: nuevoIndicador
                ]
        )
    }
}
