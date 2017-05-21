package ar.utn.edu.dds;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 21/05/17.
 */
@Component
public class ContenedorIndicadores {

    private List<Indicador> indicadores;

    ContenedorIndicadores() {
        indicadores = new ArrayList<>();
    }

    public List<Indicador> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

}
