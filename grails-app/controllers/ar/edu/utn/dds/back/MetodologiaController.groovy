package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.MetodologiaInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Metodologia
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('ROLE_ADMIN')
class MetodologiaController extends RestfulController {
    static responseFormats = ['json']

    def metodologiaService

    MetodologiaController() {
        super(Metodologia)
    }

    def list() {
        try {
            def metodologias = metodologiaService.listar()
            response.setStatus(200)
            render([metodologias: metodologias] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            Metodologia nuevaMetodologia = new Metodologia(nombre, expresion, visibilidad)
            metodologiaService.guardar(nuevoIndicador)
            response.addHeader("Location", "/indicador/" + nuevoIndicador.getNombre())
            response.setStatus(201)
            render ""
        } catch(MetodologiaInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            Metodologia metodologia = metodologiaService.obtener(params.id)
            response.setStatus(200)
            render([metodologia: metodologia] as JSON)
        } catch(RecursoNoEncontradoException e) {
            response.setStatus(404)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
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