package ar.edu.utn.dds

class IndicadoresController {

    def index() { }

    def verIndicadores() {
        render(
                view: "/indicadores",
                model: [

                ]
        )
    }

    def volver() {
        render(
                view: "/index",
                model: [

                ]
        )
    }
    def guardar() {
    /*Agregar invocacion a dominio*/
    }
}
