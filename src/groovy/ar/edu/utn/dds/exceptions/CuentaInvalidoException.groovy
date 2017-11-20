package ar.edu.utn.dds.exceptions

class CuentaInvalidoException extends ElementoInvalidoException {

    CuentaInvalidoException(String mensaje) {
        super(mensaje)
    }

    CuentaInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
