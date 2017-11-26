package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.utils.helper.RestHelper
import com.fasterxml.jackson.databind.ObjectMapper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

import java.text.SimpleDateFormat
import java.util.stream.Collectors

@Secured('ROLE_ADMIN')
class ModificarEmpresaViewController {
    private String baseUrl = "http://localhost:8080/dds-inversiones"

    def agregarCuenta() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        def getResponse = new RestBuilder().get(baseUrl + "/empresa/" + params.empresa) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        Empresa empresa = bindData(new Empresa(), getResponse.getJson().empresa)

        render(
            view: "/agregarCuenta",
            model: [
                empresa: empresa
            ]
        )
    }

    def save_cuenta_empresa() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)
        String saveCuentaText

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")
        String fechaDesdeString = format.format(params.fechaDesde)
        String fechaHastaString = format.format(params.fechaHasta)

        def postResponse = new RestBuilder().post(baseUrl + "/periodo") {
            header 'Cookie', cookie
            contentType "application/json"
            json {
                fechaDesde = fechaDesdeString
                fechaHasta = fechaHastaString
            }
        }

        if(!postResponse.getStatus().equals(201)) {
            renderRespuestaErrorGenericoBack(postResponse)
            return
        }


        def getResponse = new RestBuilder().get(baseUrl + postResponse.getHeaders().getLocation().getPath()) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        Periodo nuevoPeriodo = bindData(new Periodo(), getResponse.getJson().periodo)


        String idDeEmpresa = params.empresa
        getResponse = new RestBuilder().get(baseUrl + "/empresa/" + idDeEmpresa) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        Empresa empresa = bindData(new Empresa(), getResponse.getJson().empresa)


        String nombreCuenta = params.nomCuenta.toString()
        Double valorCuenta = Double.parseDouble(params.valor)

        postResponse = new RestBuilder().post(baseUrl + "/cuenta") {
            header 'Cookie', cookie
            contentType "application/json"
            json {
                idEmpresa = empresa.getId()
                idPeriodo = nuevoPeriodo.getId()
                nombre = nombreCuenta
                valor = valorCuenta
            }
        }

        if(!postResponse.getStatus().equals(201)) {
            renderRespuestaErrorGenericoBack(postResponse)
            return
        }


        getResponse = new RestBuilder().get(baseUrl + postResponse.getHeaders().getLocation().getPath() + "?" + postResponse.getHeaders().getLocation().getQuery()) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        Cuenta cuenta = bindData(new Cuenta(), getResponse.getJson().cuenta)

        saveCuentaText = "Cuenta ${cuenta?.tipo?.descripcion} guardada exitosamente!"
        render(
            view: "/agregarCuenta",
            model: [
                cuenta: cuenta,
                saveCuentaText: saveCuentaText,
                empresa: empresa
            ]
        )
    }

    def listarCuentas() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)
        String idEmpresa = params.empresa
        def getResponse = new RestBuilder().get(baseUrl + "/empresa/" + idEmpresa) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        Empresa empresa = new ObjectMapper().readValue(getResponse.getJson().empresa.toString(), Empresa.class)

        def periodos = empresa.periodos.stream()
                .map{periodo -> [
                inicio:periodo.fechaInicio, fin:periodo.fechaFin,
                cuentas:periodo.cuentas.stream().map{cuenta -> [
                    nombre:cuenta.tipo.descripcion,
                    valor:cuenta.valor]}
                .collect(Collectors.toList())]}
        .collect(Collectors.toList())

        render(
            view: "/listarCuentas",
            model: [
                periodos: periodos,
                empresa: empresa.getNombre()
            ]
        )
    }

    void renderRespuestaErrorGenericoBack(RestResponse response) {
        def view
        def model

        view = "/errorGenericoBack"
        model = [
                text: response.getJson().descripcionError.toString(),
                buttonText: "Volver"
        ]
        render(view: view, model: model)
    }

    void renderRespuestaError(RestResponse response) {
        def view
        def model

        view = "/error"
        model = [
                exception: new Exception(response.getJson().descripcionError.toString()),
        ]
        render(view: view, model: model)
    }
}
