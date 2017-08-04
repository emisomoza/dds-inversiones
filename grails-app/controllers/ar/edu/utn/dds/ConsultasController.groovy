package ar.edu.utn.dds

class ConsultasController {

    def index() {

    }

    def verConsultar() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        def empresas = contenedor.getEmpresas()
        render(
                view: "/consultas",
                model: [
                        empresas: empresas
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
