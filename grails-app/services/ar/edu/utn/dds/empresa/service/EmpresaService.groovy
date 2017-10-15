package ar.edu.utn.dds.empresa.service

import ar.edu.utn.dds.model.Empresa

class EmpresaService {

    def empresaRepositoryService
    def cuentaService

    def existe(Long id) {
        return this.empresaRepositoryService.existe(id)
    }

    def listar() {
        return this.listar(new Empresa())
    }

    def listar(Empresa empresa) {
        return this.empresaRepositoryService.listar(empresa)
    }

    def obtener(Long id) {
        return this.empresaRepositoryService.obtener(id)
    }

    def guardar(Empresa empresa) {
        return this.empresaRepositoryService.guardar(empresa)
    }

    def actualizar(Empresa empresa) {
        return this.empresaRepositoryService.actualizar(empresa)
    }

    def importarCuentas(File archivo) {
        List<Map<String, String>> mapasCuentas = this.cuentaService.parsearImportCuentas(archivo)
    }
}