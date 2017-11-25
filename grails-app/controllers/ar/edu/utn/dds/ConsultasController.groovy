package ar.edu.utn.dds

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import grails.plugin.springsecurity.annotation.Secured

import java.util.stream.Collectors

@Secured('ROLE_USER')
class ConsultasController {

    def mapNormalizer
    def empIdMapper
    def metodologiaService
    def empresaService

    def comparar() {

        def view
        def model

        try{
            Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")
            Metodologia metodologia = metodologiaService.obtener(params.metodologia)
            List<Empresa> empresas = empIdMapper.mapear(normalizedMap).stream()
                    .map({empId -> this.empresaService.obtenerPopulado(empId)})
                    .collect(Collectors.toList())
            ArrayList<Empresa> empresasOrdenadas = metodologiaService.comparar(metodologia, empresas)

            def empresasIndexadas = empresasOrdenadas.indexed().collect{
                index, item -> [indice:index + 1, nombre:item.nombre]
            }

            view = "/resultadoComparacion"
            model = [
                    empresas: empresasIndexadas
            ]

        } catch(Exception e) {
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
