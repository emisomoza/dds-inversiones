package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.model.Empresa;
import ar.edu.utn.dds.exceptions.MetodologiaException;

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
