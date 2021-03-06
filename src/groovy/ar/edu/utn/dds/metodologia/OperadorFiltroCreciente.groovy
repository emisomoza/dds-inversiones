package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo

class OperadorFiltroCreciente extends OperadorFiltro {

    @Override
    Boolean filtrar(Empresa empresa, IndicadorService indicadorService) throws MetodologiaException {
        Indicador indicador = indicadorService.obtener(this.getIndicador())

        List<Periodo> periodos =
            this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().sort{p1, p2 -> p1.getFechaInicio() <=> p2.getFechaInicio()}
            )

        return periodos.every {
            int index = periodos.indexOf(it)
            try {
                return index <= 0 || indicadorService.aplicar(periodos.get(index - 1), indicador) < indicadorService.aplicar(it, indicador)
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException("No se puede aplicar la metodologia porque no se encontro una cuenta")
            }
        }
    }
}