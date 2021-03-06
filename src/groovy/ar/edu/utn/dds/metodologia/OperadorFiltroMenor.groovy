package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.ElementoInvalidoException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo

class OperadorFiltroMenor extends OperadorFiltro {

    private Double comparador

    OperadorFiltroMenor() {
    }

    OperadorFiltroMenor(Double comparador) {
        this.comparador = comparador
    }

    Double getComparador() {
        return comparador
    }

    void setComparador(Double comparador) {
        this.comparador = comparador
    }

    @Override
    Boolean filtrar(Empresa empresa, IndicadorService indicadorService) throws MetodologiaException {
        Indicador indicador = indicadorService.obtener(this.getIndicador())

        List<Periodo> periodos =
            this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().sort{p1, p2 -> p1.getFechaInicio() <=> p2.getFechaInicio()}
            )

        return periodos.every {
            try {
                return indicadorService.aplicar(it, indicador) < this.comparador
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException("No se puede aplicar la metodologia porque no se encontro una cuenta")
            }
        }
    }

    @Override
    void validarConsistencia() {
        super.validarConsistencia()
        if(this.getComparador() == null)
            throw new ElementoInvalidoException("El operador de filtro menor debe tener comparador")
    }
}