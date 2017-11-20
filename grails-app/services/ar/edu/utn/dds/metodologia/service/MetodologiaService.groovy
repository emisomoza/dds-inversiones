package ar.edu.utn.dds.metodologia.service

import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PostFilter

class MetodologiaService {

    def metodologiaRepositoryService
    def indicadorService
    def springSecurityService

    void guardar(Metodologia metodologia) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologia.setOwner(userId)

        metodologia.validarConsistencia()
        metodologiaRepositoryService.guardar(metodologia)
    }

    @PostAuthorize("returnObject.visibilidad == 'ROLE_NULL' || hasRole(returnObject.visibilidad)")
    Metodologia obtener(String name) {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologiaRepositoryService.obtener(name, userId)
    }

    @PostFilter("filterObject.getVisibilidad() == 'ROLE_NULL' || hasRole(filterObject.visibilidad)")
    ArrayList<Metodologia> listar() {
        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologiaRepositoryService.listar(userId)
    }

    List<Empresa> comparar(Metodologia metodologia, List<Empresa> empresasAComparar) {
        List<Empresa> empresasFiltradas = metodologia.filtrar(empresasAComparar, indicadorService)
        return metodologia.ordenar(empresasFiltradas, indicadorService)
    }

}
