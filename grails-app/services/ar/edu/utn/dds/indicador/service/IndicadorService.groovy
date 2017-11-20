package ar.edu.utn.dds.indicador.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.resolver.ResolvedorIndicador
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PostFilter

class IndicadorService {

    def indicadorRepositoryService
    def springSecurityService

    void aplicar(Periodo periodo, List<Indicador> indicadores) {
        for(Indicador unIndicador : indicadores) {
            this.aplicar(periodo, unIndicador)
        }
    }

    Double aplicar(Periodo periodo, Indicador indicador) {
        Closure<Cuenta> obtenedorDeCuentas = {String unNombreCuenta -> periodo.getCuentas().find {it.getNombre().equals(unNombreCuenta)}}
        Closure<Indicador> obtenedorDeIndicadores = {String unNombreIndicador -> this.obtener(unNombreIndicador)}

        ResolvedorIndicador resolvedorIndicador = new ResolvedorIndicador(obtenedorDeCuentas, obtenedorDeIndicadores)

        return resolvedorIndicador.resolver(indicador)
    }

    void guardar(Indicador indicador) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        indicador.setOwner(userId)

        indicador.validarConsistencia()
        indicadorRepositoryService.guardar(indicador)
    }

    @PostAuthorize("returnObject.visibilidad == 'ROLE_NULL' || hasRole(returnObject.visibilidad)")
    Indicador obtener(String name) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        indicadorRepositoryService.obtener(name, userId)
    }

    @PostFilter("filterObject.getVisibilidad() == 'ROLE_NULL' || hasRole(filterObject.visibilidad)")
    ArrayList<Indicador> listar() {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        return indicadorRepositoryService.listar(userId)
    }
}