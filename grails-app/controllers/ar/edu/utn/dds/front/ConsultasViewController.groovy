package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.RequestInvalidoException
import ar.edu.utn.dds.exceptions.RespuestaErrorHttpException
import ar.edu.utn.dds.model.Empresa
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class ConsultasViewController extends AbstractViewController {
    def mapNormalizer
    def empIdMapper

    def index() { }

    def comparar() {
        try {
            Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")

            validarParametrosComparar(normalizedMap)

            String parametrosComparar = "?metodologia=" + params.metodologia
            for(Long unIdEmpresa : empIdMapper.mapear(normalizedMap)) {
                parametrosComparar += "&empresa=" + unIdEmpresa.toString()
            }

            def getResponse = get("/metodologia/comparar" + parametrosComparar)

            List<Empresa> empresasOrdenadas = mapearALista(getResponse.getJson().empresasOrdenadas, Empresa.class)

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
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosComparar(Map params) {
        String metodologia = params.containsKey("metodologia") ? params.metodologia : null
        List<Long> empresas = params.containsKey("empresas") ? empIdMapper.mapear(params) : null

        if(metodologia == null || metodologia.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna metodologia")
        }

        if(empresas == null || empresas.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna empresa")
        }

        if(!(empresas.size() > 1)) {
            throw new RequestInvalidoException("Deben ingresarse 2 empresas por lo menos")
        }
    }
}
