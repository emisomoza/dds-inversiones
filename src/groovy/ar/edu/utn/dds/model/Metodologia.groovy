package ar.edu.utn.dds.model

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "metodologias")
@CompoundIndexes([
        @CompoundIndex(name = "id_idx", def = "{'nombre': 1, 'owner': -1}", unique = true)
])
class Metodologia implements Serializable {

    private String nombre
    private String owner
    private List<OperadorFiltro> operadoresFiltro
    private OperadorOrdenador operadorOrden

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    String getOwner() {
        return owner
    }

    void setOwner(String owner) {
        this.owner = owner
    }

    List<OperadorFiltro> getOperadoresFiltro() {
        return operadoresFiltro
    }

    void setOperadoresFiltro(List<OperadorFiltro> operadoresFiltro) {
        this.operadoresFiltro = operadoresFiltro
    }

    OperadorOrdenador getOperadorOrden() {
        return operadorOrden
    }

    void setOperadorOrden(OperadorOrdenador operadorOrden) {
        this.operadorOrden = operadorOrden
    }

    List<Empresa> filtrar(List<Empresa> empresas) {
        return empresas.findAll { this.filtrar(it) }
    }

    Boolean filtrar(Empresa empresa) {
        return this.operadoresFiltro.every { it.filtrar(empresa) }
    }

    List<Empresa> ordenar(List<Empresa> empresas) {
        return this.operadorOrden.ordenar(empresas);
    }
}