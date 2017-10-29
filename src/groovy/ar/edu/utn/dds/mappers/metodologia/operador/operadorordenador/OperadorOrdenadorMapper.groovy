package ar.edu.utn.dds.mappers.metodologia.operador.operadorordenador

import ar.edu.utn.dds.metodologia.ModificadorOrdenador
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorProm
import ar.edu.utn.dds.metodologia.ModificadorOrdenadorSum
import ar.edu.utn.dds.metodologia.OperadorOrdenador

abstract class OperadorOrdenadorMapper implements OperadorOrdenadorMapperStrategy {

    protected static final String INDICADOR = "indicador"

    protected static final String MODIFICADOR = "modificador"
    protected static final String MODIFICADOR_PROM = "promedio"
    protected static final String MODIFICADOR_SUM = "suma"

    abstract OperadorOrdenador getInstance()

    @Override
    OperadorOrdenador mapear(Map<String, Object> rawOperador) {
        OperadorOrdenador operador = this.getInstance()

        operador.setModificador(this.modificadorOrdenador((String) rawOperador.get(MODIFICADOR)))
        operador.setIndicador((String) rawOperador.get(INDICADOR))

        return operador
    }

    protected ModificadorOrdenador modificadorOrdenador(String strModificador) {
        ModificadorOrdenador modif = null

        if(strModificador != null)
            modif = this.modificadorStrategy().get(strModificador).newInstance()

        return modif
    }

    private Map<String, Class<? extends ModificadorOrdenador>> modificadorStrategy() {
        Map<String, Class<? extends ModificadorOrdenador>> strategy = new HashMap<>()

        strategy.put(MODIFICADOR_PROM, ModificadorOrdenadorProm.class)
        strategy.put(MODIFICADOR_SUM, ModificadorOrdenadorSum.class)

        return strategy
    }

}
