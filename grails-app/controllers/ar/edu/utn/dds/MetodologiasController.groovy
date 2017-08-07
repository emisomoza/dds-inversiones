package ar.edu.utn.dds

class MetodologiasController {

    def index() { }

    def verMetodologias() {
        render(
                view: "/metodologias",
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
}
