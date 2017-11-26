package ar.edu.utn.dds.exceptions

/**
 * Created by esomoza on 5/13/17.
 */
class CuentaNoExisteException extends InversionesException {

    CuentaNoExisteException() {
        super("La cuenta no existe")
    }
}
