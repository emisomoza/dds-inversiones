package ar.edu.utn.dds.exceptions

class ElementoInvalidoException extends InversionesException {

    ElementoInvalidoException(String mensaje) {
        super(mensaje)
    }

    ElementoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
