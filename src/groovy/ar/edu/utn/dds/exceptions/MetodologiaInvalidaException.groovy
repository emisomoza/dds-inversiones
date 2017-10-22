package ar.edu.utn.dds.exceptions

class MetodologiaInvalidaException extends ElementoInvalidoException {

    MetodologiaInvalidaException(String mensaje) {
        super(mensaje)
    }

    MetodologiaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
