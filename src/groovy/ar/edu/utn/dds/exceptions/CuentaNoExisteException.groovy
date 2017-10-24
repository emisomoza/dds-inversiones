package ar.edu.utn.dds.exceptions

/**
 * Created by esomoza on 5/13/17.
 */
class CuentaNoExisteException extends Exception {

    CuentaNoExisteException() {
        super("La cuenta no existe")
    }
}
