package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.IndicadorExistenteException
import ar.edu.utn.dds.exceptions.IndicadorInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Indicador
import com.fasterxml.jackson.databind.ObjectMapper
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.json.JSONObject

@Secured('ROLE_USER')
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
            render([indicadores: indicadores.collect {it -> new JSONObject(new ObjectMapper().writeValueAsString(it))}] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()
        String nombre = jsonObject.nombre
        String expresion = jsonObject.expresion
        String visibilidad = jsonObject.visibilidad

        try {
            Indicador nuevoIndicador = Indicador.from(nombre, expresion, visibilidad)
            indicadorService.guardar(nuevoIndicador)
            response.addHeader("Location", URLEncoder.encode("/indicador/" + nuevoIndicador.getNombre(), "UTF-8"))
            response.setStatus(201)
            render ""
        } catch(IndicadorExistenteException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
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
            Indicador indicador = indicadorService.obtener(params.id)
            response.setStatus(200)
            render([indicador: new JSONObject(new ObjectMapper().writeValueAsString(indicador))] as JSON)
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