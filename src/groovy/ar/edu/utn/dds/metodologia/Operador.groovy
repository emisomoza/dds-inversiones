package ar.edu.utn.dds.metodologia

abstract class Operador implements Serializable {

    private String indicador;

    String getIndicador() {
        return indicador;
    }

    void setIndicador(String indicador) {
        this.indicador = indicador;
    }
}
