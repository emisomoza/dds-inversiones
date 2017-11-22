package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.utils.helper.RestHelper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Secured('ROLE_USER')
class IndicadoresViewController {
    private String baseUrl = "http://localhost:8080/dds-inversiones"

    def indicadorService

    def save() {
        String nombreString = params.nombre
        String expresionString = params.expresion
        String visibilidadString = params.visibilidad
        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        def postResponse = new RestBuilder().post(baseUrl + "/indicador") {
            header 'Cookie', cookie
            contentType "application/json"
            json {
                nombre = nombreString
                expresion = expresionString
                visibilidad = visibilidadString
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

        def view
        def model
        Indicador nuevoIndicador = bindData(new Indicador(), getResponse.getJson().indicador)

        view = "/indicadores/indicadorAgregado"
        model = [
            indicador: nuevoIndicador
        ]
        render(view: view, model: model)
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
