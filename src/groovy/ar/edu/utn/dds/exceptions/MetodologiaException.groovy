package ar.edu.utn.dds.exceptions

/**
 * Created by andres on 06/08/17.
 */
class MetodologiaException extends InversionesException {
    MetodologiaException(String mensaje) {
        super(mensaje)
    }

    MetodologiaException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
