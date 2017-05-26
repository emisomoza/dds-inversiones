package ar.edu.utn.dds.expresion;

public class FuncionBinaria extends Funcion {
    private Expresion primerParametro;
    private Expresion segundoParametro;

    public FuncionBinaria(String nombre, Expresion primerParametro, Expresion segundoParametro) {
        super(nombre);
        this.primerParametro = primerParametro;
        this.segundoParametro = segundoParametro;
    }

    @Override
    protected Double getValor() {
        switch (nombre) {
            case "root":
                return Math.pow(segundoParametro.getResultado(), 1.0/primerParametro.getResultado());
            default:
                return null;
        }
    }
}
