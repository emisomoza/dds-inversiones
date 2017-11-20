package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.EmpresaInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.model.Empresa
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Secured('ROLE_ADMIN')
class EmpresaController {

    def empresaService
    def importadorCuentas

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

    def upload() {

        def view
        def model
        String text

        try {
            if(request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request
                CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file")

                if(!file?.empty) {
                    importadorCuentas.importar(new String(file.getBytes()))
                    text = "Archivo procesado exitosamente!"
                } else
                    text = "El archivo no puede estar vacío."

            } else
                text = 'Request is not of type MultipartHttpServletRequest'

            def empresas = empresaService.listar()

            view = "/empresas"
            model = [
                    empresas: empresas,
                    text: text
            ]

        } catch(InversionesException e) {
            log.error(e.getMessage(), e)

            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        } catch(Exception e) {
            log.error(e.getMessage(), e)

            view = "/errorGenericoBack"
            model = [
                    text: "Ocurrio un error procesando la carga",
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
