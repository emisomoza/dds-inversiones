package ar.edu.utn.dds.metodologia;

import ar.edu.utn.dds.Empresa;

import java.util.List;

/**
 * Created by andres on 06/08/17.
 */
public abstract class OperadorOrdenador extends Operador {

    private ModificadorOrdenador modificador;

    public ModificadorOrdenador getModificador() {
        return modificador;
    }

    public void setModificador(ModificadorOrdenador modificador) {
        this.modificador = modificador;
    }

    public abstract List<Empresa> ordenar(List<Empresa> empresas);
}
