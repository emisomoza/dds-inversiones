package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.CuentaInvalidoException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.TipoCuenta
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('ROLE_ADMIN')
class CuentaController extends RestfulController {
    static responseFormats = ['json']

    def cuentaService
    def tipoCuentaService

    CuentaController() {
        super(Cuenta)
    }

    def get() {
        if(params.empresa != null && params.periodo != null && params.tipo != null) {
            this.show()
        } else {
            this.list()
        }
    }

    def list() {
        try {
            def cuentas = cuentaService.listar()
            response.setStatus(200)
            render([cuentas: cuentas] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            TipoCuenta tipoCuenta = new TipoCuenta(jsonObject.nombre)
            Long idTipoCuenta = tipoCuentaService.guardar(tipoCuenta)
            tipoCuenta.setId(idTipoCuenta)

            Cuenta cuenta = new Cuenta(jsonObject.idEmpresa, jsonObject.idPeriodo, tipoCuenta, jsonObject.valor)
            cuentaService.guardar(cuenta)
            response.addHeader("Location", "/cuenta?empresa=" + cuenta.getEmpresa() + "&periodo=" + cuenta.getPeriodo() + "&tipo=" + cuenta.getTipo().getId())
            response.setStatus(201)
            render ""
        } catch(CuentaInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            def cuenta = cuentaService.obtener(params.empresa as Long, params.periodo as Long, params.tipo as Long)
            response.setStatus(200)
            render([cuenta: cuenta] as JSON)
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
