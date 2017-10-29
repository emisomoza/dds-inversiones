package ar.edu.utn.dds.metodologia.service

import ar.edu.utn.dds.exceptions.MetodologiaInvalidaException
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Metodologia

class MetodologiaService {

    def metodologiaRepositoryService
    def indicadorService
    def springSecurityService

    void guardar(Metodologia metodologia) {
        this.validarGuardar(metodologia)

        Long userId = (Long) springSecurityService.getCurrentUserId()
        metodologia.setOwner(userId)

        metodologiaRepositoryService.guardar(metodologia)
    }

    void validarGuardar(Metodologia metodologia) {
        if(!this.estaCompleto(metodologia))
            throw new MetodologiaInvalidaException("La metodologia debe tener nombre, operadores de filtro y operador de ordenamiento")
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

    Boolean estaCompleto(Metodologia metodologia) {
        return (metodologia.getNombre() == null
            || metodologia.getOperadoresFiltro() == null
            || metodologia.getOperadoresFiltro().size() == 0
            || metodologia.getOperadorOrden() == null)
    }
}
