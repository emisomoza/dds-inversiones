package ar.edu.utn.dds

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import ar.edu.utn.dds.utils.map.MapNormalizer
import grails.plugin.springsecurity.annotation.Secured

import java.util.stream.Collectors

@Secured('ROLE_USER')
class ConsultasController {

    def mapNormalizer
    def metodologiaService
    def empresaService

    protected static final String EMPRESAS = "empresas"

    def comparar() {

        def view
        def model

        try{
            Map<String, Object> normalizedMap = mapNormalizer.normalizeValues(params)
            normalizedMap = (new MapNormalizer()).normalizeKeys(normalizedMap, ".")
            Metodologia metodologia = metodologiaService.obtener(params.metodologia)
            ArrayList<Empresa> empresasOrdenadas = metodologiaService.comparar( metodologia, this.mapear(normalizedMap) )

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

    List<Empresa> mapear(Map<String, Object> rawComparar) {
        List<Empresa> empresas = this.mapearEmpresas((Map<String, Object>) rawComparar.get(EMPRESAS))

        return empresas
    }

    protected List<Empresa> mapearEmpresas(Map<String, Object> rawEmpresas) {
        return Optional.ofNullable(rawEmpresas).orElse(new HashMap<String, Object>()).values().parallelStream()
                .map({rawEmpresa -> this.mapearEmpresa(rawEmpresa)})
                .collect(Collectors.toList())
    }

    protected Empresa mapearEmpresa(Map<String, Object> rawEmpresa) {
        Empresa empresa = empresaService.obtener(Long.parseLong(rawEmpresa.get("empresa")))
        empresa.periodos = empresaService.obtenerPeriodos(empresa)

        return empresa
    }

}
