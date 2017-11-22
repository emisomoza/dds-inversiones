package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.EmpresaInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Empresa
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('ROLE_ADMIN')
class EmpresaController extends RestfulController {
    static responseFormats = ['json']

    def empresaService
    def importadorCuentas

    EmpresaController() {
        super(Empresa)
    }

    def list() {
        try {
            def empresas = empresaService.listar()
            response.setStatus(200)
            render([empresas: empresas] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            Long id = empresaService.guardar(new Empresa(jsonObject.nombre))
            response.addHeader("Location", "/empresa/" + id.toString())
            response.setStatus(201)
            render ""
        } catch(EmpresaInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            def empresa = empresaService.obtener(params.id as Long)
            response.setStatus(200)
            render([empresa: empresa] as JSON)
        } catch(RecursoNoEncontradoException e) {
            response.setStatus(404)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def upload() {
        def jsonObject = request.getJSON()

        if(jsonObject.text == null || jsonObject.text.isEmpty()) {
            response.setStatus(400)
            render("El archivo debe tener contenido")
            return
        }

        try {
            importadorCuentas.importar(jsonObject.text)
            response.setStatus(201)
            render("")
        } catch(InversionesException e) {
            response.setStatus(422)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e, "Ocurrio un error procesando la carga")
        }
    }

    void renderErrorInversiones(InversionesException e) {
        log.error(e.getMessage(), e)
        render([descripcionError: e.getMessage()] as JSON)
    }

    void renderErrorGenerico(Exception e) {
        log.error(e.getMessage(), e)
        render([descripcionError: "Error inesperado"] as JSON)
    }

    void renderErrorGenerico(Exception e, String mensaje) {
        log.error(e.getMessage(), e)
        render([descripcionError: mensaje] as JSON)
    }
}