package dds.inversiones

class SecurityFilters {

    def springSecurityService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                request.user = springSecurityService.getCurrentUser()
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
