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

        "/empresa"(controller: "empresa", method: "POST", action: "save")
        "/empresa/from-csv"(controller: "empresa", method: "POST", action: "upload")
        "/empresa"(controller: "empresa", method: "GET", action: "list")
        "/empresa/$id"(controller: "empresa", method: "GET", action: "show")

        "/empresa"(controller: "empresa", method: "POST", action: "save")
        "/empresa/from-csv"(controller: "empresa", method: "POST", action: "upload")
        "/empresa"(controller: "empresa", method: "GET", action: "list")
        "/empresa/$id"(controller: "empresa", method: "GET", action: "show")

        "500"(view:'/error')
    }
}
