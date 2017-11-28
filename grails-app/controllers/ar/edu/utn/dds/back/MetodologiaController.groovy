package ar.edu.utn.dds.back

import ar.edu.utn.dds.exceptions.EmpresaInvalidoException
import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.exceptions.MetodologiaInvalidoException
import ar.edu.utn.dds.exceptions.RecursoNoEncontradoException
import ar.edu.utn.dds.mappers.metodologia.MetodologiaMapper
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import com.fasterxml.jackson.databind.ObjectMapper
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.json.JSONObject

@Secured('ROLE_USER')
class MetodologiaController extends RestfulController {
    static responseFormats = ['json']

    def metodologiaService
    def empresaService

    MetodologiaController() {
        super(Metodologia)
    }

    def list() {
        try {
            def metodologias = metodologiaService.listar()
            response.setStatus(200)
            render([metodologias: metodologias] as JSON)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def save() {
        def jsonObject = request.getJSON()

        try {
            MetodologiaMapper mapper = new MetodologiaMapper()
            Metodologia nuevaMetodologia = mapper.mapear(jsonObject.parametrosMetodologia)
            metodologiaService.guardar(nuevaMetodologia)

            response.addHeader("Location", URLEncoder.encode("/metodologia/" + nuevaMetodologia.getNombre(), "UTF-8"))
            response.setStatus(201)
            render ""
        } catch(MetodologiaInvalidoException e) {
            response.setStatus(400)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def show() {
        try {
            Metodologia metodologia = metodologiaService.obtener(params.id)
            response.setStatus(200)
            render([metodologia: metodologia] as JSON)
        } catch(RecursoNoEncontradoException e) {
            response.setStatus(404)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    def compare() {
        try {
            Metodologia metodologia = metodologiaService.obtener(params.metodologia)
            List<String> idsEmpresas = params.empresa
            List<Empresa> empresas = idsEmpresas.collect {it -> empresaService.obtenerPopulado(Long.valueOf(it))}
            validarEmpresas(empresas)
            List<Empresa> empresasComparadas = metodologiaService.comparar(metodologia, empresas)
            response.setStatus(200)
            render([empresasOrdenadas: empresasComparadas.collect {it -> new JSONObject(new ObjectMapper().writeValueAsString(it))}] as JSON)
        } catch(RecursoNoEncontradoException e) {
            response.setStatus(404)
            renderErrorInversiones(e)
        } catch(EmpresaInvalidoException e) {
            response.setStatus(404)
            renderErrorInversiones(e)
        } catch(Exception e) {
            response.setStatus(500)
            renderErrorGenerico(e)
        }
    }

    void renderErrorInversiones(InversionesException e) {
        log.error(e.getMessage(), e)
        render([descripcionError: e.getMessage()] as JSON)
    }

    void renderErrorGenerico(Exception e) {
        log.error(e.getMessage(), e)
        render([descripcionError: "Error inesperado"] as JSON)
    }

    void renderErrorGenerico(Exception e, String mensaje) {
        log.error(e.getMessage(), e)
        render([descripcionError: mensaje] as JSON)
    }

    void validarEmpresas( List<Empresa> empresas ) {
        for( empresa in empresas ) {
            if( empresa.periodos.size() == 0 ) {
                throw new EmpresaInvalidoException("La empresa ${empresa.nombre} no posee períodos.")
            } else if ( empresa.periodos?.cuentas.size() == 0 ) {
                throw new EmpresaInvalidoException("La empresa ${empresa.nombre} posee períodos sin cuentas.")
            }
        }
    }

}