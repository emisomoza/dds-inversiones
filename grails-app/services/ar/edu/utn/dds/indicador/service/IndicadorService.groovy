package ar.edu.utn.dds.indicador.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.resolver.ResolvedorIndicador
import grails.transaction.Transactional

@Transactional
class IndicadorService {
    def indicadorRepositoryService
    def springSecurityService

    void aplicar(Periodo periodo, List<Indicador> indicadores) {
        Closure<Cuenta> obtenedorDeCuentas = {String unNombreCuenta -> periodo.getCuentas().find {it.getNombre().equals(unNombreCuenta)}}
        Closure<Indicador> obtenedorDeIndicadores = {String unNombreIndicador -> indicadorRepositoryService.obtenerIndicador(unNombreIndicador)}

        ResolvedorIndicador resolvedorIndicador = new ResolvedorIndicador(obtenedorDeCuentas, obtenedorDeIndicadores)
        for(Indicador unIndicador : indicadores) {
            resolvedorIndicador.resolver(unIndicador)
        }
    }

    void guardarIndicador(Indicador indicador) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        indicador.setOwner(userId)
        indicadorRepositoryService.guardarIndicador(indicador)
    }

    Indicador obtenerIndicador(String name) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        indicadorRepositoryService.obtenerIndicador(name, userId)
    }

    ArrayList<Indicador> listarTodo() {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        indicadorRepositoryService.listarTodo(userId)
    }
}
