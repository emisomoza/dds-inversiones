package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.OperadorOrdenador
import ar.edu.utn.dds.metodologia.OperadorOrdenadorMenor

class OperadorOrdenadorMenorMapper extends OperadorOrdenadorMapper {

    @Override
    OperadorOrdenador getInstance() {
        return new OperadorOrdenadorMenor()
    }
}
