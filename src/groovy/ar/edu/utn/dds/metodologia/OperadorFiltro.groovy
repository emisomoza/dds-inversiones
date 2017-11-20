package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.ElementoInvalidoException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Empresa

abstract class OperadorFiltro extends Operador {

    private ModificadorFiltro modificador

    ModificadorFiltro getModificador() {
        return modificador
    }

    void setModificador(ModificadorFiltro modificador) {
        this.modificador = modificador
    }

    abstract Boolean filtrar(Empresa empresa, IndicadorService indicadorService) throws MetodologiaException

    @Override
    void validarConsistencia() {
        super.validarConsistencia()
        if(this.getModificador() == null)
            throw new ElementoInvalidoException("El operador de filtro debe tener modificador")
    }
}
