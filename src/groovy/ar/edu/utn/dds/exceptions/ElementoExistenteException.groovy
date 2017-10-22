package ar.edu.utn.dds.exceptions

class ElementoExistenteException extends InversionesException {

    ElementoExistenteException(String mensaje) {
        super(mensaje)
    }

    ElementoExistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
