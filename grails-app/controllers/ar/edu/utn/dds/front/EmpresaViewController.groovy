package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.utils.helper.RestHelper
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Secured('ROLE_ADMIN')
class EmpresaViewController {
    private String baseUrl = "http://localhost:8080/dds-inversiones"

    def index() { }

    def save() {
        String nombreEmpresa = params.nomEmpresa

        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        def postResponse = new RestBuilder().post(baseUrl + "/empresa") {
            header 'Cookie', cookie
            contentType "application/json"
            json {
                nombre = nombreEmpresa
            }
        }

        if(!postResponse.getStatus().equals(201)) {
            renderRespuestaErrorGenericoBack(postResponse)
            return
        }


        def getResponse = new RestBuilder().get(baseUrl + postResponse.getHeaders().getLocation().getPath()) {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!getResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(getResponse)
            return
        }


        def listarResponse = new RestBuilder().get(baseUrl + "/empresa") {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!listarResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(listarResponse)
            return
        }

        def view
        def model
        Empresa nuevaEmpresa = bindData(new Empresa(), getResponse.getJson().empresa)
        List<Empresa> todasLasEmpresas = listarResponse.getJson().empresas.collect {it -> bindData(new Empresa(), it)}

        view = "/empresas"
        model = [
            empresas: todasLasEmpresas,
            text: "Empresa \"$nuevaEmpresa.nombre\" guardada con éxito."
        ]

        render(view: view, model: model)
    }

    def upload() {
        def view
        def model
        String textoSalida

        String cookie = RestHelper.getJSessionCookieFromRequest(request)

        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");

        if(!file?.empty) {
            if(file.contentType.equals("text/plain") | file.contentType.equals("text/csv")) {
                def postResponse = new RestBuilder().post(baseUrl + "/empresa/from-csv") {
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

        def listarResponse = new RestBuilder().get(baseUrl + "/empresa") {
            header 'Cookie', cookie
            contentType "application/json"
        }

        if(!listarResponse.getStatus().equals(200)) {
            renderRespuestaErrorGenericoBack(listarResponse)
            return
        }

        List<Empresa> todasLasEmpresas = listarResponse.getJson().empresas.collect {it -> bindData(new Empresa(), it)}

        view = "/empresas"
        model = [
            empresas: todasLasEmpresas,
            text: textoSalida
        ]

        render(view: view, model: model)
    }

    void renderRespuestaErrorGenericoBack(RestResponse response) {
        def view
        def model

        view = "/errorGenericoBack"
        model = [
            text: response.getJson().descripcionError.toString(),
            buttonText: "Volver"
        ]
        render(view: view, model: model)
    }

    void renderRespuestaError(RestResponse response) {
        def view
        def model

        view = "/error"
        model = [
            exception: new Exception(response.getJson().descripcionError.toString()),
        ]
        render(view: view, model: model)
    }
}
