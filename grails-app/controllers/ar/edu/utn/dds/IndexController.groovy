package ar.edu.utn.dds

import ar.edu.utn.dds.model.Metodologia
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

@Secured('ROLE_USER')
class IndexController {

    def empresaService
    def springSecurityService

    def home() {
        render(
                view: "/index",
                model: [

                ]
        )

    }

    def compararEmpresas() {
        def empresas = empresaService.listar()
        ArrayList<Metodologia> metodologias = [new Metodologia(nombre: "Metodología 1"), new Metodologia(nombre: "Metodología 2")]

        render(
                view: "/consultas",
                model: [
                        empresas: empresas,
                        metodologias: metodologias
                ]
        )
    }

    @Secured('ROLE_ADMIN')
    def agregarCuentas() {
        def empresas = empresaService.listar()
        render(
                view: "/empresas",
                model: [
                        empresas: empresas
                ]
        )
    }

    def agregarIndicadores() {
        List<String> userRoles = ((GrailsUser) springSecurityService.getPrincipal())
                .getAuthorities()
                .collect({authority -> authority.getAuthority()})

        render(
                view: "/indicadores/cargaIndicadores",
                model: [
                    userRoles: userRoles
                ]
        )
    }
}
