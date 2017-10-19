package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Empresa

abstract class OperadorOrdenador extends Operador {

    private ModificadorOrdenador modificador

    ModificadorOrdenador getModificador() {
        return modificador
    }

    void setModificador(ModificadorOrdenador modificador) {
        this.modificador = modificador
    }

    abstract List<Empresa> ordenar(List<Empresa> empresas, IndicadorService indicadorService)
}
