package ar.edu.utn.dds.metodologia.service

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Metodologia
import ar.edu.utn.dds.resolver.ResolvedorIndicador
import grails.transaction.Transactional

class MetodologiaService {

    def metodologiaRepositoryService
    def indicadorService
    def springSecurityService

    void guardar(Metodologia metodologia) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologia.setOwner(userId)
        metodologiaRepositoryService.guardar(metodologia)
    }

    Metodologia obtener(String name) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologiaRepositoryService.obtener(name, userId)
    }

    ArrayList<Metodologia> listar() {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologiaRepositoryService.listar(userId)
    }

    List<Empresa> comparar(Metodologia metodologia, List<Empresa> empresasAComparar) {
        List<Empresa> empresasFiltradas = metodologia.filtrar(empresasAComparar, indicadorService)
        return metodologia.ordenar(empresasFiltradas, indicadorService)
    }
}