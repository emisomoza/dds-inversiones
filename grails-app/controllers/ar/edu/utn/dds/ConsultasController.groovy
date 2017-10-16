package ar.edu.utn.dds

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class ConsultasController {

    def comparar() {
        render(
                view: "/index",
                model: [

                ]
        )
    }

}
