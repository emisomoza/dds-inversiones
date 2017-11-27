package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.RespuestaErrorHttpException
import ar.edu.utn.dds.utils.helper.RestHelper
import com.fasterxml.jackson.databind.ObjectMapper
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.beans.factory.annotation.Value

abstract class AbstractViewController {
    @Value('${grails.serverURL}')
    private String baseUrl

    String getBaseUrl() {
        return baseUrl
    }

    public <T> T mapearAObjeto(JSONObject jsonObject, Class<T> clazz) {
        return new ObjectMapper().readValue(jsonObject.toString(), clazz)
    }

    public <T> List<T> mapearALista(List<JSONObject> jsonObjects, Class<T> clazz) {
        return jsonObjects.collect {it -> new ObjectMapper().readValue(it.toString(), clazz)}
    }

    RestResponse get(String url) {
        def response = new RestBuilder().get(baseUrl + url) {
            header 'Cookie', RestHelper.getJSessionCookieFromRequest(request)
            contentType "application/json;charset=utf-8"
        }

        if(!response.getStatus().equals(200)) {
            throw new RespuestaErrorHttpException(response.getJson().descripcionError.toString(), response.getStatus())
        }

        return response
    }

    RestResponse post(String url, Closure bodyClosure) {
        def response = new RestBuilder().post(baseUrl + url) {
            header 'Cookie', RestHelper.getJSessionCookieFromRequest(request)
            contentType "application/json;charset=utf-8"
            json bodyClosure
        }

        if(!response.getStatus().equals(201)) {
            throw new RespuestaErrorHttpException(response.getJson().descripcionError.toString(), response.getStatus())
        }

        return response
    }

    void renderRespuestaErrorGenericoBack(String message) {
        def view
        def model

        view = "/errorGenericoBack"
        model = [
            text: message,
            buttonText: "Volver"
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
