package ar.edu.utn.dds.resolver

import ar.edu.utn.dds.expresion.ExpresionNegada
import ar.edu.utn.dds.expresion.FuncionBinaria
import ar.edu.utn.dds.expresion.FuncionEstatica
import ar.edu.utn.dds.expresion.FuncionUnaria
import ar.edu.utn.dds.expresion.OperacionDivision
import ar.edu.utn.dds.expresion.OperacionMultiplicacion
import ar.edu.utn.dds.expresion.OperacionPotencia
import ar.edu.utn.dds.expresion.OperacionResta
import ar.edu.utn.dds.expresion.OperacionSuma
import ar.edu.utn.dds.expresion.PrimariaCuenta
import ar.edu.utn.dds.expresion.PrimariaIndicador
import ar.edu.utn.dds.expresion.PrimariaNumero
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Indicador

import static java.lang.Math.sqrt

class ResolvedorIndicador {
    private Closure<Cuenta> obtenedorDeCuentas
    private Closure<Indicador> obtenedorDeIndicadores
    private Map<String, Double> resultadosIndicadores = new HashMap<>()

    ResolvedorIndicador() {
    }

    ResolvedorIndicador(Closure<Cuenta> obtenedorDeCuentas, Closure<Indicador> obtenedorDeIndicadores) {
        this.obtenedorDeCuentas = obtenedorDeCuentas
        this.obtenedorDeIndicadores = obtenedorDeIndicadores
    }

    Closure<Cuenta> getObtenedorDeCuenta() {
        return obtenedorDeCuentas
    }

    void setObtenedorDeCuenta(Closure<Cuenta> obtenedorDeCuenta) {
        this.obtenedorDeCuentas = obtenedorDeCuenta
    }

    Closure<Indicador> getObtenedorDeIndicador() {
        return obtenedorDeIndicadores
    }

    void setObtenedorDeIndicador(Closure<Indicador> obtenedorDeIndicador) {
        this.obtenedorDeIndicadores = obtenedorDeIndicador
    }

    void limpiarResultados() {
        resultadosIndicadores = new HashMap<>()
    }

    Double resolver(Indicador indicador) {
        Double result = resultadosIndicadores.get(indicador.getNombre())
        if(result == null) {
            result = resolver(indicador.getExpresion())
            resultadosIndicadores.put(indicador.getNombre(), result)
        }

        return result
    }

    Double resolver(ExpresionNegada expresion) {
        return -1 * resolver(expresion.getExpresion())
    }

    Double resolver(FuncionBinaria expresion) {
        switch (expresion.getNombre()) {
            case FuncionBinaria.ROOT:
                return Math.pow(resolver(expresion.getSegundoParametro()), 1.0 / resolver(expresion.getPrimerParametro()))
            default:
                return null
        }
    }

    Double resolver(FuncionEstatica expresion) {
        switch (expresion.getNombre()) {
            case FuncionEstatica.PI:
                return Math.PI
            default:
                return null
        }
    }

    Double resolver(FuncionUnaria expresion) {
        switch (expresion.getNombre()) {
            case FuncionUnaria.SQRT:
                return sqrt(resolver(expresion.getExpresion()))
            default:
                return null
        }
    }

    Double resolver(OperacionDivision expresion) {
        return resolver(expresion.getExpresionIzquierda()) / resolver(expresion.getExpresionDerecha())
    }

    Double resolver(OperacionMultiplicacion expresion) {
        return resolver(expresion.getExpresionIzquierda()) * resolver(expresion.getExpresionDerecha())
    }

    Double resolver(OperacionPotencia expresion) {
        return Math.pow(resolver(expresion.getExpresionIzquierda()), resolver(expresion.getExpresionDerecha()))
    }

    Double resolver(OperacionSuma expresion) {
        return resolver(expresion.getExpresionIzquierda()) + resolver(expresion.getExpresionDerecha())
    }

    Double resolver(OperacionResta expresion) {
        return resolver(expresion.getExpresionIzquierda()) - resolver(expresion.getExpresionDerecha())
    }

    Double resolver(PrimariaCuenta expresion) {
        return obtenedorDeCuentas.call(expresion.getNombre()).getValor()
    }

    Double resolver(PrimariaIndicador expresion) {
        return resolver(obtenedorDeIndicadores.call(expresion.getNombre()))
    }

    Double resolver(PrimariaNumero expresion) {
        return expresion.getValor()
    }
}
