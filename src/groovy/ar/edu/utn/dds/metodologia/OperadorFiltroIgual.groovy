package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo

class OperadorFiltroIgual extends OperadorFiltro {

    @Override
    public Boolean filtrar(Empresa empresa) throws MetodologiaException {
        List<Periodo> periodos =
            this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().sort{p1, p2 -> p1.getFechaInicio() <=> p2.getFechaInicio()}
            )

        return periodos.every {
            int index = periodos.indexOf(it)
            try {
                return index <= 0 || periodos.get(index - 1).consultarCuenta(this.getIndicador()) == it.consultarCuenta(this.getIndicador());
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException();
            }
        }
    }
}
