package ar.edu.utn.dds.metodologia.service

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Metodologia
import ar.edu.utn.dds.resolver.ResolvedorIndicador
import grails.transaction.Transactional

@Transactional
class MetodologiaService {
    def metodologiaRepositoryService
    def indicadorService

    void guardar(Metodologia metodologia) {
        metodologiaRepositoryService.guardar(metodologia)
    }

    Metodologia obtener(String name) {
        metodologiaRepositoryService.obtener(name)
    }

    ArrayList<Metodologia> listar() {
        metodologiaRepositoryService.listar()
    }

    List<Empresa> comparar(Metodologia metodologia, List<Empresa> empresasAComparar) {
        List<Empresa> empresasFiltradas = metodologia.filtrar(empresasAComparar, indicadorService)
        return metodologia.ordenar(empresasFiltradas)
    }
}
