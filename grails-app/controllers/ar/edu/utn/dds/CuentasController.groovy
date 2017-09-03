package ar.edu.utn.dds

import ar.edu.utn.dds.model.ContenedorEmpresas

class CuentasController {

    def indicadorService

    def index() {

    }

    def verCuenta() {
        render(
                view: "/cuentas",
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

    def save() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")
        indicadorService.insertIndicador("indicadorPrueba")
        render(
                view: "/index",
                model: [

                ]
        )
    }
}
