package ar.edu.utn.dds

class CuentasController {

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

    def guardar() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")
        render(
                view: "/index",
                model: [

                ]
        )
    }
}
