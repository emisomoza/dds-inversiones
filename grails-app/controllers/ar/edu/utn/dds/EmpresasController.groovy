package ar.edu.utn.dds

import ar.edu.utn.dds.model.Empresa

class EmpresasController {
    def empresaService

    def index() { }

    def save() {
        empresaService.guardar(new Empresa(params.nomEmpresa))

        def empresas = empresaService.listar()
        render(
                view: "/empresas",
                model: [
                    empresas: empresas
                ]
        )
    }
}
