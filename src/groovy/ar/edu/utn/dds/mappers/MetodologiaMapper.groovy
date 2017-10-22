package ar.edu.utn.dds.mappers

import ar.edu.utn.dds.metodologia.OperadorFiltro
import ar.edu.utn.dds.metodologia.OperadorOrdenador
import ar.edu.utn.dds.model.Metodologia

class MetodologiaMapper {

    Metodologia mapear(Map<String, Object> rawMetodologia) {
        Metodologia nuevaMetodologia = new Metodologia()

        nuevaMetodologia.setNombre((String) rawMetodologia.get("nombre"))
        nuevaMetodologia.setOperadoresFiltro(this.mapearOperadoresFiltro((Map<String, Object>) rawMetodologia.get("operadoresfiltro")))
        nuevaMetodologia.setOperadorOrden(this.mapearOperadorOrden((Map<String, Object>) rawMetodologia).get("operadororden"))

        return nuevaMetodologia
    }

    protected List<OperadorFiltro> mapearOperadoresFiltro(Map<String, Object> rawOperadoresFiltro) {
        List<OperadorFiltro> operadoresFiltro = new ArrayList<>()

        return operadoresFiltro
    }

    protected OperadorOrdenador mapearOperadorOrden(Map<String, Object> rawOperadoresFiltro) {

    }
}
