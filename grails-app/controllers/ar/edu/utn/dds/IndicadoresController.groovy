package ar.edu.utn.dds

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
