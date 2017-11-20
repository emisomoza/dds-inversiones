package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.exceptions.MetodologiaInvalidoException
import ar.edu.utn.dds.mappers.metodologia.MetodologiaMapper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

@Secured('ROLE_USER')
class MetodologiasController {

    def indicadorService
    def metodologiaService
    def mapNormalizer
    def springSecurityService

    def index() { }

    def crear() {
        List<String> indicadores = indicadorService.listar().collect({ind -> ind.nombre}).sort()
        List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal())
                .getAuthorities()
                .collect({authority -> authority.getAuthority()})

        render(
                view: "/metodologias",
                model: [
                        indicadores: indicadores,
                        userRoles: userRoles
                ]
        )
    }

    def save() {

        def view
        def model

        try{
            Map<String, Object> normalizedMap = mapNormalizer.normalize(params, ".")
            MetodologiaMapper mapper = new MetodologiaMapper()
            metodologiaService.guardar(mapper.mapear(normalizedMap))

            List<String> indicadores = indicadorService.listar().collect({ind -> ind.nombre}).sort()
            List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal())
                    .getAuthorities()
                    .collect({authority -> authority.getAuthority()})

            view = "/metodologias"
            model = [
                    indicadores: indicadores,
                    userRoles: userRoles,
                    text: "Metodología \"$params.nombre\" guardada con éxito."
            ]

        } catch(MetodologiaInvalidoException | MetodologiaExistenteException e) {
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
