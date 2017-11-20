package ar.edu.utn.dds.model

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "metodologias")
@CompoundIndexes([
        @CompoundIndex(name = "id_idx", def = "{'nombre': 1, 'owner': -1}", unique = true)
])
@JsonIgnoreProperties(ignoreUnknown=true)
class Metodologia implements Serializable {

    @JsonProperty("nombre")
    private String nombre
    @JsonProperty("owner")
    private Long owner
    @JsonProperty("visibilidad")
    private String visibilidad
    @JsonProperty("operadoresFiltro")
    private List<OperadorFiltro> operadoresFiltro
    @JsonProperty("operadorOrden")
    private OperadorOrdenador operadorOrden

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    Long getOwner() {
        return owner
    }

    void setOwner(Long owner) {
        this.owner = owner
    }

    String getVisibilidad() {
        return visibilidad
    }

    void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad
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

    List<Empresa> filtrar(List<Empresa> empresas, IndicadorService indicadorService) {
        return empresas.findAll { this.filtrar(it, indicadorService) }
    }

    Boolean filtrar(Empresa empresa, IndicadorService indicadorService) {
        return this.operadoresFiltro.every { it.filtrar(empresa, indicadorService) }
    }

    List<Empresa> ordenar(List<Empresa> empresas, IndicadorService indicadorService) {
        return this.operadorOrden.ordenar(empresas, indicadorService)
    }
}