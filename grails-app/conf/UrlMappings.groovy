class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")

        "/empresa"(controller: "empresa", method: "POST", action: "save")
        "/empresa/from-csv"(controller: "empresa", method: "POST", action: "upload")
        "/empresa"(controller: "empresa", method: "GET", action: "list")
        "/empresa/$id"(controller: "empresa", method: "GET", action: "show")
        "/empresa/$id/cuentas"(controller: "empresa", method: "GET", action: "showCuentas")

        "/periodo"(controller: "periodo", method: "POST", action: "save")
        "/periodo"(controller: "periodo", method: "GET", action: "list")
        "/periodo/$id"(controller: "periodo", method: "GET", action: "show")

        "/cuenta"(controller: "cuenta", method: "POST", action: "save")
        "/cuenta"(controller: "cuenta", method: "GET", action: "get")

        "/indicador"(controller: "indicador", method: "POST", action: "save")
        "/indicador"(controller: "indicador", method: "GET", action: "list")
        "/indicador/$id"(controller: "indicador", method: "GET", action: "show")

        "/metodologia"(controller: "indicador", method: "POST", action: "save")
        "/metodologia"(controller: "indicador", method: "GET", action: "list")
        "/metodologia/$id"(controller: "indicador", method: "GET", action: "show")

        "500"(view:'/error')
    }
}
