package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import ar.edu.utn.dds.utils.helper.RestHelper
import com.fasterxml.jackson.databind.ObjectMapper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

import java.util.stream.Collectors

@Secured('ROLE_USER')
class ConsultasViewController {
    private String baseUrl = "http://localhost:8080/dds-inversiones"

    def mapNormalizer
    def empIdMapper

    def comparar() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        String parametrosComparar = "?metodologia=" + params.metodologia

        Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")
        for(Long unIdEmpresa : empIdMapper.mapear(normalizedMap)) {
            parametrosComparar += "&empresa=" + unIdEmpresa.toString()
        }

        def getResponse = new RestBuilder().get(baseUrl + "/metodologia/comparar" + parametrosComparar) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        ArrayList<Empresa> empresasOrdenadas = getResponse.getJson().empresasOrdenadas.collect {it -> new ObjectMapper().readValue(it.toString(), Empresa.class)}

        def empresasIndexadas = empresasOrdenadas.indexed().collect{
            index, item -> [indice:index + 1, nombre:item.nombre]
        }

        def view
        def model

        view = "/resultadoComparacion"
        model = [
            empresas: empresasIndexadas
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
