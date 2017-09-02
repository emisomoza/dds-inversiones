package ar.edu.utn.dds

import ar.edu.utn.dds.model.ContenedorEmpresas

class IndexController {

    def home() {
        render(
                view: "/index",
                model: [

                ]
        )

    }

    def compararEmpresas() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")
        def empresas = contenedor.getEmpresas()
        render(
                view: "/consultas",
                model: [
                        empresas: empresas
                ]
        )
    }

    def agregarCuentas() {
        render(
                view: "/cuentas",
                model: [

                ]
        )
    }

    def agregarIndicadores() {
        render(
                view: "/indicadores/cargaIndicadores",
                model: [

                ]
        )
    }

    def crearMetodologias() {
        render(
                view: "/metodologias",
                model: [

                ]
        )
    }
}
