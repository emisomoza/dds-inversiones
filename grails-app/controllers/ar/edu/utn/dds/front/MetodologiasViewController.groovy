package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.exceptions.MetodologiaInvalidoException
import ar.edu.utn.dds.mappers.metodologia.MetodologiaMapper
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Metodologia
import ar.edu.utn.dds.utils.helper.RestHelper
import com.fasterxml.jackson.databind.ObjectMapper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Secured('ROLE_USER')
class MetodologiasViewController {
    private String baseUrl = "http://localhost:8080/dds-inversiones"

    def indicadorService
    def springSecurityService

    def index() { }

    def crear() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        def listarResponse = new RestBuilder().get(baseUrl + "indicador") {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!listarResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        List<Indicador> todosLosIndicadores = listarResponse.getJson().indicadores.collect {it -> new ObjectMapper().readValue(it.toString(), Indicador.class)}
        List<String> nombresIndicadores = todosLosIndicadores.collect({ind -> ind.nombre}).sort()

        List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal()).getAuthorities().collect({authority -> authority.getAuthority()})

        def view
        def model

        view = "/metodologias"
        model = [
            indicadores: nombresIndicadores,
            userRoles: userRoles,
        ]
        render(view: view, model: model)
    }

    def save() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)


        //Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")
        //MetodologiaMapper mapper = new MetodologiaMapper()
        //metodologiaService.guardar(mapper.mapear(normalizedMap))

        def postResponse = new RestBuilder().post(baseUrl + "/metodologia") {
            header 'Cookie', cookie
            contentType "application/json"
            json {
                params = params
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



        def listarResponse = new RestBuilder().get(baseUrl + "indicador") {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!listarResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }

        List<Indicador> todosLosIndicadores = listarResponse.getJson().indicadores.collect {it -> new ObjectMapper().readValue(it.toString(), Indicador.class)}
        List<String> nombresIndicadores = todosLosIndicadores.collect({ind -> ind.nombre}).sort()

        List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal()).getAuthorities().collect({authority -> authority.getAuthority()})

        def view
        def model

        view = "/metodologias"
        model = [
            indicadores: nombresIndicadores,
            userRoles: userRoles,
            text: "Metodología \"$params.nombre\" guardada con éxito."
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
