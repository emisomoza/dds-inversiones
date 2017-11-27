package ar.edu.utn.dds.front

import ar.edu.utn.dds.exceptions.RequestInvalidoException
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat
import java.util.stream.Collectors

@Secured('ROLE_ADMIN')
class ModificarEmpresaViewController extends AbstractViewController {

    def agregarCuenta() {
        try {
            validarParametrosAgregarCuenta(params)

            def getResponse = get("/empresa/" + params.empresa)

            Empresa empresa = mapearAObjeto(getResponse.getJson().empresa, Empresa.class)

            render(
                view: "/agregarCuenta",
                model: [
                    empresa: empresa
                ]
            )
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosAgregarCuenta(Map params) {
        String empresaId = params.containsKey("empresa") ? params.empresa : null

        if(empresaId == null || empresaId.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso ninguna empresa")
        }
    }

    def save_cuenta_empresa() {
        try {
            validarParametrosSaveCuentaEmpresa(params)

            String saveCuentaText

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")
            String fechaDesdeString = format.format(params.fechaDesde)
            String fechaHastaString = format.format(params.fechaHasta)

            def postResponse = post("/periodo", {
                fechaDesde = fechaDesdeString
                fechaHasta = fechaHastaString
            })

            def getResponse = get(postResponse.getHeaders().getLocation().getPath())

            Periodo nuevoPeriodo = mapearAObjeto(getResponse.getJson().periodo, Periodo.class)

            String idDeEmpresa = params.empresa
            getResponse = get("/empresa/" + idDeEmpresa)

            Empresa empresa = mapearAObjeto(getResponse.getJson().empresa, Empresa.class)

            String nombreCuenta = params.nomCuenta.toString()
            Double valorCuenta = Double.parseDouble(params.valor)

            postResponse = post("/cuenta", {
                idEmpresa = empresa.getId()
                idPeriodo = nuevoPeriodo.getId()
                nombre = nombreCuenta
                valor = valorCuenta
            })

            getResponse = get(postResponse.getHeaders().getLocation().getPath() + "?" + postResponse.getHeaders().getLocation().getQuery())

            Cuenta cuenta = mapearAObjeto(getResponse.getJson().cuenta, Cuenta.class)

            saveCuentaText = "Cuenta ${cuenta?.tipo?.descripcion} guardada exitosamente!"
            render(
                view: "/agregarCuenta",
                model: [
                    cuenta: cuenta,
                    saveCuentaText: saveCuentaText,
                    empresa: empresa
                ]
            )
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosSaveCuentaEmpresa(Map params) {
        String fechaDesde = params.containsKey("fechaDesde") ? params.fechaDesde : null
        String fechaHasta = params.containsKey("fechaHasta") ? params.fechaHasta : null
        String empresaId = params.containsKey("empresa") ? params.empresa : null
        String valor = params.containsKey("valor") ? params.valor : null

        if(fechaDesde == null || fechaDesde.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso fecha inicio")
        }

        if(fechaHasta == null || fechaHasta.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso fecha fin")
        }

        if(empresaId == null || empresaId.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso ninguna empresa")
        }

        if(valor == null || valor.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso ningun valor")
        }

        try {
            Double.parseDouble(valor)
        } catch(Exception e) {
            throw new RequestInvalidoException("El valor ingresado no es un numero valido")
        }
    }

    def listarCuentas() {
        try {
            String idEmpresa = params.empresa

            def getResponse = get("/empresa/" + idEmpresa)

            Empresa empresa = mapearAObjeto(getResponse.getJson().empresa, Empresa.class)

            def periodos = empresa.periodos.stream().map { periodo -> [
                inicio : periodo.fechaInicio, fin: periodo.fechaFin,
                cuentas: periodo.cuentas.stream().map { cuenta -> [
                    nombre: cuenta.tipo.descripcion,
                    valor : cuenta.valor
                ]}.collect(Collectors.toList())
            ]}.collect(Collectors.toList())

            render(
                view: "/listarCuentas",
                model: [
                    periodos: periodos,
                    empresa : empresa.getNombre()
                ]
            )
        } catch(Exception e) {
            renderRespuestaErrorGenericoBack(e.getMessage())
        }
    }

    void validarParametrosListarCuentas(Map params) {
        String empresaId = params.containsKey("empresa") ? params.empresa : null

        if(empresaId == null || empresaId.isEmpty()) {
            throw new RequestInvalidoException("No se ingreso ninguna empresa")
        }
    }
}
