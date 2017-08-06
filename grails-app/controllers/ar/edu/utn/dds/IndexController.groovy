package ar.edu.utn.dds

class IndexController {

    def home() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")
        render(
                view: "/index",
                model: [

                ]
        )

    }

    def compararEmpresas() {
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
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
