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
        for(Indicador unIndicador : indicadores) {
            this.aplicar(periodo, unIndicador)
        }
    }

    Double aplicar(Periodo periodo, Indicador indicador) {
        Closure<Cuenta> obtenedorDeCuentas = {String unNombreCuenta -> periodo.getCuentas().find {it.getNombre().equals(unNombreCuenta)}}
        Closure<Indicador> obtenedorDeIndicadores = {String unNombreIndicador -> indicadorRepositoryService.obtener(unNombreIndicador)}

        ResolvedorIndicador resolvedorIndicador = new ResolvedorIndicador(obtenedorDeCuentas, obtenedorDeIndicadores)

        return resolvedorIndicador.resolver(indicador)
    }

    void guardar(Indicador indicador) {
        indicadorRepositoryService.guardar(indicador)
    }

    Indicador obtener(String name) {
        indicadorRepositoryService.obtener(name)
    }

    ArrayList<Indicador> listar() {
        indicadorRepositoryService.listar()
    }
}
