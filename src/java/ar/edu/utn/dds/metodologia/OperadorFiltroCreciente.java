package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Empresa;
import ar.edu.utn.dds.Periodo;
import ar.edu.utn.dds.exceptions.CuentaNoExisteException;
import ar.edu.utn.dds.exceptions.MetodologiaException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andres on 06/08/17.
 */
public class OperadorFiltroCreciente extends OperadorFiltro {


    @Override
    Boolean filtrar(Empresa empresa) throws MetodologiaException {
        List<Periodo> periodos = this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().stream()
                        .sorted(Comparator.comparing(Periodo::getFechaInicio))
                        .collect(Collectors.toList()));

        return periodos.stream()
                .allMatch(periodo -> {
                    int index = periodos.indexOf(periodo);
                    try {
                        return index <= 0
                                || periodos.get(index - 1).consultarCuenta(this.getIndicador()) < periodo.consultarCuenta(this.getIndicador());
                    } catch (CuentaNoExisteException e) {
                        throw new MetodologiaException();
                    }
                });
    }
}
