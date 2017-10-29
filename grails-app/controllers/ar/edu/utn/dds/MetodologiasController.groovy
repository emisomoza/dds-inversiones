package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.exceptions.MetodologiaInvalidaException
import ar.edu.utn.dds.mappers.metodologia.MetodologiaMapper
import ar.edu.utn.dds.utils.map.MapNormalizer
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class MetodologiasController {

    def indicadorService
    def metodologiaService
    def mapNormalizer

    def index() { }

    def crear() {
        List<String> indicadores = indicadorService.listar().collect({ind -> ind.nombre}).sort()

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores
                ]
        )
    }

    def save() {

        def view
        def model

        try{
            Map<String, Object> normalizedMap = mapNormalizer.normalizeValues(params)
            normalizedMap = (new MapNormalizer()).normalizeKeys(normalizedMap, ".")
            MetodologiaMapper mapper = new MetodologiaMapper()
            metodologiaService.guardar(mapper.mapear(normalizedMap))

            List<String> indicadores = indicadorService.listar().collect({ind -> ind.nombre}).sort()

            view = "/metodologias"
            model = [
                    indicadores: indicadores,
                    text: "Metodología $params.nombre guardada con éxito."
            ]

        } catch(MetodologiaInvalidaException | MetodologiaExistenteException e) {
            log.error(e.getMessage(), e)

            view = "/errorGenericoBack"
            model = [
                    text: e.getMessage(),
                    buttonText: "Volver"
            ]
        }

        render(view: view, model: model)
    }
}
