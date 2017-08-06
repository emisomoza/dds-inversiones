package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Empresa;
import ar.edu.utn.dds.Periodo;
import ar.edu.utn.dds.exceptions.MetodologiaException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andres on 06/08/17.
 */
public abstract class OperadorFiltro extends Operador {

    private ModificadorFiltro modificador;

    public ModificadorFiltro getModificador() {
        return modificador;
    }

    public void setModificador(ModificadorFiltro modificador) {
        this.modificador = modificador;
    }


    public abstract Boolean filtrar(Empresa empresa) throws MetodologiaException;

}
