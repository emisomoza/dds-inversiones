package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador

class OperadorOrdenadorMayor extends OperadorOrdenador {

    @Override
    List<Empresa> ordenar(List<Empresa> empresas, IndicadorService indicadorService) {
        Indicador indicador = indicadorService.obtener(this.getIndicador())

        return empresas.sort { e1, e2 ->
            Double value1 = this.getModificador().reducir(e1.getPeriodos(), indicadorService, indicador)
            Double value2 = this.getModificador().reducir(e2.getPeriodos(), indicadorService, indicador)

            return (value1 - value2).intValue()
        }
    }
}
