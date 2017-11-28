package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.RequestInvalidoException
import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

@Secured('ROLE_USER')
class MetodologiasViewController extends AbstractViewController {
    def mapNormalizer
    def springSecurityService

    def index() { }

    def crear() {
        try {
            def listarResponse = get("/indicador")

            List<Indicador> todosLosIndicadores = mapearALista(listarResponse.getJson().indicadores, Indicador.class)
            List<String> nombresIndicadores = todosLosIndicadores.collect({ ind -> ind.nombre }).sort()

            List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal()).getAuthorities().collect({ authority -> authority.getAuthority() })

            def view
            def model

            view = "/metodologias"
            model = [
                    indicadores: nombresIndicadores,
                    userRoles  : userRoles,
            ]
            render(view: view, model: model)
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    def save() {
        try {
            Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")

            validarParametrosSave(normalizedMap)

            post("/metodologia", {
                parametrosMetodologia = normalizedMap
            })

            def listarResponse = get("/indicador")

            List<Indicador> todosLosIndicadores = mapearALista(listarResponse.getJson().indicadores, Indicador.class)
            List<String> nombresIndicadores = todosLosIndicadores.collect({ ind -> ind.nombre }).sort()

            List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal()).getAuthorities().collect({ authority -> authority.getAuthority() })

            def view
            def model

            view = "/metodologias"
            model = [
                    indicadores: nombresIndicadores,
                    userRoles  : userRoles,
                    text       : "Metodología \"$params.nombre\" guardada con éxito."
            ]
            render(view: view, model: model)
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosSave(Map params) {
        String nombreString = params.containsKey("nombre") ? params.nombre : null
        String expresionString = params.containsKey("expresion") ? params.expresion : null
        String visibilidadString = params.containsKey("visibilidad") ? params.visibilidad : null

        if(nombreString == null || nombreString.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ningun nombre de indicador")
        }

        if(visibilidadString == null || visibilidadString.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna visibilidad")
        }
    }
}
