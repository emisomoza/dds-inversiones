package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.MetodologiaException
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo

class OperadorFiltroMenor extends OperadorFiltro {

    private Double comparador;

    OperadorFiltroMenor() {
    }

    OperadorFiltroMenor(Double comparador) {
        this.comparador = comparador;
    }

    Double getComparador() {
        return comparador;
    }

    void setComparador(Double comparador) {
        this.comparador = comparador;
    }

    @Override
    Boolean filtrar(Empresa empresa) throws MetodologiaException {
        List<Periodo> periodos =
            this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().sort{p1, p2 -> p1.getFechaInicio() <=> p2.getFechaInicio()}
            )

        return periodos.every {
            try {
                return it.consultarCuenta(this.getIndicador()) < this.comparador;
            } catch (CuentaNoExisteException e) {
                throw new MetodologiaException();
            }
        }
    }
}