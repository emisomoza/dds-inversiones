package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador

class IndicadoresController {
    def save() {
        Indicador nuevoIndicador = new Indicador(params.nombre, params.expresion)

        //Guardarlo en la BD

        render(
                view: "/indicadorAgregado",
                model: [
                    indicador: nuevoIndicador
                ]
        )
    }
}
