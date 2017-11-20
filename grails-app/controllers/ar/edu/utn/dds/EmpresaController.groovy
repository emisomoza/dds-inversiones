package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.EmpresaInvalidoException
import ar.edu.utn.dds.model.Empresa
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class EmpresaController {
    def empresaService

    def index() { }

    def save() {

        def view
        def model

        try{
        Empresa nuevaEmpresa =  new Empresa()
        nuevaEmpresa.setNombre(params.nomEmpresa)
        empresaService.guardar(nuevaEmpresa)

        def empresas = empresaService.listar()

        view = "/empresas"
        model = [
                empresas: empresas,
                text: "Empresa \"$nuevaEmpresa.nombre\" guardada con éxito."
        ]

        } catch(EmpresaInvalidoException e) {
            log.error(e.getMessage(), e)

            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
