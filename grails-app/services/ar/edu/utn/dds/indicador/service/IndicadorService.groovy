package ar.edu.utn.dds.indicador.service

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.resolver.ResolvedorIndicador
import grails.transaction.Transactional

@Transactional
class IndicadorService {
    def indicadorRepositoryService

    void aplicar(Periodo periodo, List<Indicador> indicadores) {
        Closure<Cuenta> obtenedorDeCuentas = {String unNombreCuenta -> periodo.getCuentas().find {it.getNombre().equals(unNombreCuenta)}}
        Closure<Indicador> obtenedorDeIndicadores = {String unNombreIndicador -> indicadorRepositoryService.getIndicador(unNombreIndicador)}

        ResolvedorIndicador resolvedorIndicador = new ResolvedorIndicador(obtenedorDeCuentas, obtenedorDeIndicadores)
        for(Indicador unIndicador : indicadores) {
            resolvedorIndicador.resolver(unIndicador)
        }
    }
}
