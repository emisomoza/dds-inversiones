package ar.edu.utn.dds.exceptions

class MetodologiaInvalidoException extends ElementoInvalidoException {

    MetodologiaInvalidoException(String mensaje) {
        super(mensaje)
    }

    MetodologiaInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
