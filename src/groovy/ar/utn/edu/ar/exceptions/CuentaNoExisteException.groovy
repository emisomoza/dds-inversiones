package ar.utn.edu.ar.exceptions

/**
 * Created by esomoza on 5/13/17.
 */
public class CuentaNoExisteException extends Exception {
    private static final long serialVersionUID = 253859439964199894L;

    public CuentaNoExisteException() {
        super("La cuenta no existe");
    }
}
