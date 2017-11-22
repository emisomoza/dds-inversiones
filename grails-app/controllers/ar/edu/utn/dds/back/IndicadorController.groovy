package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.IndicadorInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Indicador
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('ROLE_ADMIN')
class IndicadorController extends RestfulController {
    static responseFormats = ['json']

    def indicadorService

    IndicadorController() {
        super(Indicador)
    }

    def list() {
        try {
            def indicadores = indicadorService.listar()
            response.setStatus(200)
            render([indicadores: indicadores] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            Indicador nuevoIndicador = new Indicador(jsonObject.nombre, jsonObject.expresion, jsonObject.visibilidad)
            indicadorService.guardar(nuevoIndicador)
            response.addHeader("Location", "/indicador/" + nuevoIndicador.getNombre())
            response.setStatus(201)
            render ""
        } catch(IndicadorInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            def indicador = indicadorService.obtener(params.id)
            response.setStatus(200)
            render([indicador: indicador] as JSON)
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