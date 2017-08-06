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
public class OperadorFiltroMenor extends OperadorFiltro {

    private Float comparador;

    @Override
    public Boolean filtrar(Empresa empresa) throws MetodologiaException {
        List<Periodo> periodos = this.getModificador().filtrarPeriodos(
                empresa.getPeriodos().stream()
                        .sorted(Comparator.comparing(Periodo::getFechaInicio))
                        .collect(Collectors.toList()));

        return periodos.stream()
                .allMatch(periodo -> {
                    try {
                        return periodo.consultarCuenta(this.getIndicador()) < this.comparador;
                    } catch (CuentaNoExisteException e) {
                        throw new MetodologiaException();
                    }
                });
    }

}
