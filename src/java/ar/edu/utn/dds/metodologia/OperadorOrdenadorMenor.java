package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.model.Empresa;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andres on 06/08/17.
 */
public class OperadorOrdenadorMenor extends OperadorOrdenador {

    @Override
    public List<Empresa> ordenar(List<Empresa> empresas) {

        return empresas.stream()
                .sorted((e1, e2) -> {
                    Float value1 = this.getModificador().reducir(e1.getPeriodos(), this.getIndicador());
                    Float value2 = this.getModificador().reducir(e2.getPeriodos(), this.getIndicador());

                    return (int) (value2 - value1);})
                .collect(Collectors.toList());
    }
}
