package ar.edu.utn.dds.mappers.metodologia.operadorordenador

import ar.edu.utn.dds.metodologia.OperadorOrdenador

abstract class OperadorOrdenadorMapper implements OperadorOrdenadorMapperStrategy {

    private static final String MODIFICADOR = "modificador"

    abstract OperadorOrdenador getInstance()

    @Override
    OperadorOrdenador mapear(Map<String, Object> rawOperador) {
        OperadorOrdenador operador = this.getInstance()



        return operador
    }

}
