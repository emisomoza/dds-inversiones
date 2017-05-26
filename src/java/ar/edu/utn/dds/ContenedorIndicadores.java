package ar.edu.utn.dds;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 21/05/17.
 */
@Component
public class ContenedorIndicadores {

    private List<Indicador> indicadores;

    /*
     * Grails 2.5.4 (the version that we are using) does not suport de diamond operator (<>).
     * Even in java it's not necesary we need to specify the ArrayList type.
     */
    ContenedorIndicadores() {
        indicadores = new ArrayList<Indicador>();
    }

    public List<Indicador> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

}
