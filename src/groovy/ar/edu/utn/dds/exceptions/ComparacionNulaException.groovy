package ar.edu.utn.dds.exceptions

/**
 * Created by andres on 06/08/17.
 */
class ComparacionNulaException extends InversionesException {
    ComparacionNulaException(String mensaje) {
        super(mensaje)
    }

    ComparacionNulaException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
