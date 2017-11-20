package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.exceptions.ElementoInvalidoException

abstract class Operador implements Serializable {

    private String indicador

    String getIndicador() {
        return indicador
    }

    void setIndicador(String indicador) {
        this.indicador = indicador
    }

    void validarConsistencia() {
        if(this.getIndicador() == null || this.getIndicador().size() == 0)
            throw new ElementoInvalidoException("El operador debe tener un indicador")
    }
}
