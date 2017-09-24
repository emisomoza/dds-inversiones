package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Empresa

class OperadorOrdenadorMenor extends OperadorOrdenador {

    @Override
    List<Empresa> ordenar(List<Empresa> empresas) {

        return empresas.sort { e1, e2 ->
            Float value1 = this.getModificador().reducir(e1.getPeriodos(), this.getIndicador());
            Float value2 = this.getModificador().reducir(e2.getPeriodos(), this.getIndicador());

            return (int) (value2 - value1)
        }
    }
}