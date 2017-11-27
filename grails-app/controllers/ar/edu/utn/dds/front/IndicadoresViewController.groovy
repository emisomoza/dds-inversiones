package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.RequestInvalidoException
import ar.edu.utn.dds.model.Indicador
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class IndicadoresViewController extends AbstractViewController {
    def indicadorService

    def save() {
        try {
            validarParametrosSave(params)

            String nombreString = params.nombre
            String expresionString = params.expresion
            String visibilidadString = params.visibilidad

            def postResponse = post("/indicador", {
                nombre = nombreString
                expresion = expresionString
                visibilidad = visibilidadString
            })

            def getResponse = get(postResponse.getHeaders().getLocation().getPath())

            Indicador nuevoIndicador = mapearAObjeto(getResponse.getJson().indicador, Indicador.class)

            def view
            def model

            view = "/indicadoresView/indicadorAgregado"
            model = [
                indicador: nuevoIndicador
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

        if(expresionString == null || expresionString.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna expresion")
        }

        if(visibilidadString == null || visibilidadString.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna visibilidad")
        }
    }

    def listarIndicadores() {
        try {
            def getResponse = get("/indicador")
            def indicadores = mapearALista(getResponse.getJson().indicadores, Indicador.class).sort()

            render(
                view: "/listarIndicadores",
                model: [
                    indicadores: indicadores
                ]
            )

        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }
}
