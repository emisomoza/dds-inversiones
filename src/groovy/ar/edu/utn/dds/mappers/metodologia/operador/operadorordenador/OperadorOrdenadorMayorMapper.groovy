package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.OperadorOrdenador
import ar.edu.utn.dds.metodologia.OperadorOrdenadorMayor

class OperadorOrdenadorMayorMapper extends OperadorOrdenadorMapper {

    @Override
    OperadorOrdenador getInstance() {
        return new OperadorOrdenadorMayor()
    }
}