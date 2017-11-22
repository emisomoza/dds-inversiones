package ar.edu.utn.dds.front

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.model.TipoCuenta
import grails.plugin.springsecurity.annotation.Secured

import java.time.LocalDate

@Secured('ROLE_ADMIN')
class ModificarEmpresaViewController {

    def empresaService
    def periodoService
    def cuentaService
    def tipoCuentaService

    def agregarCuenta() {

        def empresa = empresaService.obtener(Long.parseLong(params.empresa))

        render(
                view: "/agregarCuenta",
                model: [
                        empresa: empresa
                ]
        )
    }

    def save_cuenta_empresa() {
        String saveCuentaText
        Cuenta cuenta
        Empresa empresa

        try {
            Long idEmpresa = Long.parseLong(params.empresa)
            empresa = empresaService.obtener(idEmpresa)
            Date fechaDesdeDate = params.fechaDesde
            Date fechaHastaDate = params.fechaHasta

            Periodo periodo = this.guardarPeriodo(fechaDesdeDate, fechaHastaDate)

            cuenta = this.guardarCuenta(idEmpresa, params.nomCuenta.toString(), periodo.id, Double.parseDouble(params.valor))

            saveCuentaText = "Cuenta ${cuenta?.tipo?.descripcion} guardada exitosamente!"

            render(
                    view: "/agregarCuenta",
                    model: [
                            cuenta: cuenta,
                            saveCuentaText: saveCuentaText,
                            empresa: empresa
                    ]
            )
        }
        catch(Exception e){
            saveCuentaText = "Error guardando cuenta"
            log.error(saveCuentaText)

            render(
                    view: "/error",
                    model: [
                            exception: e
                    ]
            )
        }
    }

    private Cuenta guardarCuenta(Long idEmpresa, String descripcion, Long idPeriodo, Double valor) {
        TipoCuenta tipoCuenta = new TipoCuenta()
        tipoCuenta.setDescripcion(descripcion)
        tipoCuentaService.guardar(tipoCuenta)
        tipoCuenta = tipoCuentaService.listar(tipoCuenta).first() //Esto es para recuperar el id

        Cuenta cuenta = new Cuenta()
        cuenta.setEmpresa(idEmpresa)
        cuenta.setTipo(tipoCuenta)
        cuenta.setPeriodo(idPeriodo)
        cuenta.setValor(valor)
        cuentaService.guardar(cuenta)

        List<Cuenta> cuentas = cuentaService.listar(cuenta)

        return cuentas.first()
    }

    private Periodo guardarPeriodo(Date fechaDesdeDate, Date fechaHastaDate) {
        LocalDate fechaDesdeLocalDate = LocalDate.of(fechaDesdeDate.calendarDate.getYear(), fechaDesdeDate.calendarDate.getMonth(), fechaDesdeDate.calendarDate.getDayOfMonth())
        LocalDate fechaHastaLocalDate = LocalDate.of(fechaHastaDate.calendarDate.getYear(), fechaHastaDate.calendarDate.getMonth(), fechaHastaDate.calendarDate.getDayOfMonth())

        Periodo periodo = new Periodo(fechaDesdeLocalDate, fechaHastaLocalDate)

        periodoService.guardar(periodo)

        return periodoService.listar(periodo).first()
    }

    def listarCuentas() {
        Long idEmpresa = Long.parseLong(params.empresa)
        Empresa empresa = empresaService.obtener(idEmpresa)
        Cuenta cuenta = new Cuenta()
        cuenta.setEmpresa(empresa.getId())

        List<Cuenta> cuentas = cuentaService.listar(cuenta)

        render(
            view: "/listarCuentas",
            model: [
                    cuentas: cuentas,
                    empresa: empresa
            ]
        )
    }
}
