package ar.edu.utn.dds

class ConsultasController {

    def index() {

    }

    def verConsultar() {
        render(
                view: "/consultas",
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

    def mostrarEmpresas() {

        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        return contenedor.getEmpresas()
    }
}
