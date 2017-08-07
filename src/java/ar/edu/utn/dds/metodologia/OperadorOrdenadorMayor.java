package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Empresa;
import ar.edu.utn.dds.Periodo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andres on 06/08/17.
 */
public class OperadorOrdenadorMayor extends OperadorOrdenador {

    @Override
    public List<Empresa> ordenar(List<Empresa> empresas) {

        return empresas.stream()
            .sorted((e1, e2) -> {
                Float value1 = this.getModificador().reducir(e1.getPeriodos(), this.getIndicador());
                Float value2 = this.getModificador().reducir(e2.getPeriodos(), this.getIndicador());

                return (int) (value1 - value2);})
            .collect(Collectors.toList());
    }
}
