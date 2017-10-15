package ar.edu.utn.dds

import ar.edu.utn.dds.model.Empresa

class EmpresaController {
    def empresaService

    def index() { }

    def save() {
        empresaService.guardar(new Empresa(params.nomEmpresa))

        def empresas = empresaService.listarTodo()
        render(
                view: "/empresas",
                model: [
                    empresas: empresas
                ]
        )
    }
}
