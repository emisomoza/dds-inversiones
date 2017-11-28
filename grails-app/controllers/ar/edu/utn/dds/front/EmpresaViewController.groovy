package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.RequestInvalidoException
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.utils.helper.RestHelper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Secured('ROLE_ADMIN')
class EmpresaViewController extends AbstractViewController {

    def index() { }

    def save() {
        try {
            validarParametrosSave(params)

            String nombreEmpresa = params.nomEmpresa
            def postResponse = post("/empresa", {nombre = nombreEmpresa})

            def getResponse = get(postResponse.getHeaders().getLocation().getPath())

            def listarResponse = get("/empresa")

            def view
            def model
            Empresa nuevaEmpresa = mapearAObjeto(getResponse.getJson().empresa, Empresa.class)
            List<Empresa> todasLasEmpresas = mapearALista(listarResponse.getJson().empresas, Empresa.class)

            view = "/empresas"
            model = [
                empresas: todasLasEmpresas,
                text: "Empresa \"$nuevaEmpresa.nombre\" guardada con éxito."
            ]

            render(view: view, model: model)
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosSave(Map params) {
        String nombreEmpresa = params.containsKey("nomEmpresa") ? params.nomEmpresa : null

        if(nombreEmpresa == null || nombreEmpresa.isEmpty()) {
            throw new RequestInvalidoException("No se ingresó ninguna empresa")
        }
    }

    def upload() {
        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        validarParametrosUpload(params)

        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");

        String textoSalida

        if(!file?.empty) {
            if(file.contentType.equals("text/plain") | file.contentType.equals("text/csv")) {
                def postResponse = new RestBuilder().post(getBaseUrl() + "/empresa/from-csv") {
                    header 'Cookie', cookie
                    contentType "application/json"
                    json {
                        text = new String(file.getBytes())
                    }
                }

                if(!postResponse.getStatus().equals(201)) {
                    textoSalida = postResponse.getJson().descripcionError.toString()
                } else {
                    textoSalida = "Archivo procesado exitosamente!"
                }
            } else {
                textoSalida = "Formato de archivo incorrecto."
            }
        } else {
            textoSalida = "El archivo no puede estar vacío."
        }

        def listarResponse = new RestBuilder().get(getBaseUrl() + "/empresa") {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!listarResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(listarResponse)
            return
        }

        List<Empresa> todasLasEmpresas = mapearALista(listarResponse.getJson().empresas, Empresa.class)

        def view
        def model
        view = "/empresas"
        model = [
            empresas: todasLasEmpresas,
            text: textoSalida
        ]

        render(view: view, model: model)
    }

    void validarParametrosUpload(Map params) {
        //No valida nada
    }
}
