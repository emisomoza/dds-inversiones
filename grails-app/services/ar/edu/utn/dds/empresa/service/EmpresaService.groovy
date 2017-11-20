package ar.edu.utn.dds.empresa.service

import ar.edu.utn.dds.model.Empresa

import java.util.stream.Collectors

class EmpresaService {

    def empresaRepositoryService
    def periodoService

    def existe(Long id) {
        return this.empresaRepositoryService.existe(id)
    }

    def existe(String nombre) {
        return this.empresaRepositoryService.existe(nombre)
    }

    def listar() {
        return this.listar(new Empresa())
    }

    def listar(Empresa empresa) {
        return this.empresaRepositoryService.listar(empresa)
    }

    def listarPopulado(Empresa empresa) {
        List<Empresa> empresas = this.listar(empresa)
        this.popular(empresas)
        return empresas
    }

    def obtener(Long id) {
        return this.empresaRepositoryService.obtener(id)
    }

    def obtenerPopulado(Long id) {
        return this.popular(this.obtener(id))
    }

    def guardar(Empresa empresa) {
        empresa.validarConsistencia()
        return this.empresaRepositoryService.guardar(empresa)
    }

    def actualizar(Empresa empresa) {
        empresa.validarConsistencia()
        return this.empresaRepositoryService.actualizar(empresa)
    }

    def popular(List<Empresa> empresas) {
        return empresas.stream()
            .map({empresa -> this.popular(empresa)})
            .collect(Collectors.toList())
    }

    def popular(Empresa empresa) {
        empresa.setPeriodos(this.periodoService.listarPopulado(empresa))
        return empresa
    }

    def importarEmpresas(List<Empresa> empresas, Boolean validarExistencia) {
        empresas.forEach({empresa ->
            if(!(validarExistencia && this.existe(empresa.getNombre())))
                this.guardar(empresa)
        })
    }

}