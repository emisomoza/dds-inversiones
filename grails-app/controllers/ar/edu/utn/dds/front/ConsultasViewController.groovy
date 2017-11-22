package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import grails.plugin.springsecurity.annotation.Secured

import java.util.stream.Collectors

@Secured('ROLE_USER')
class ConsultasViewController {

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

            view = "/resultadoComparacion"
            model = [
                    empresas: empresasOrdenadas
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
