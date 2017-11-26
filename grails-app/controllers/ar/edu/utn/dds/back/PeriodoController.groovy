package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.PeriodoInvalidoException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Periodo
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

import java.text.SimpleDateFormat
import java.time.LocalDate

@Secured('ROLE_ADMIN')
class PeriodoController extends RestfulController {
    static responseFormats = ['json']

    def periodoService

    PeriodoController() {
        super(Periodo)
    }

    def list() {
        try {
            def periodos = periodoService.listar()
            response.setStatus(200)
            render([periodos: periodos] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")
            Date fechaDesdeDate = format.parse(jsonObject.fechaDesde)
            Date fechaHastaDate = format.parse(jsonObject.fechaHasta)

            LocalDate fechaDesdeLocalDate = LocalDate.of(fechaDesdeDate.calendarDate.getYear(), fechaDesdeDate.calendarDate.getMonth(), fechaDesdeDate.calendarDate.getDayOfMonth())
            LocalDate fechaHastaLocalDate = LocalDate.of(fechaHastaDate.calendarDate.getYear(), fechaHastaDate.calendarDate.getMonth(), fechaHastaDate.calendarDate.getDayOfMonth())

            Long id = periodoService.guardar(new Periodo(fechaDesdeLocalDate, fechaHastaLocalDate))
            response.addHeader("Location", "/periodo/" + id.toString())
            response.setStatus(201)
            render ""
        } catch(PeriodoInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            def periodo = periodoService.obtener(params.id as Long)
            response.setStatus(200)
            render([periodo: periodo] as JSON)
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
