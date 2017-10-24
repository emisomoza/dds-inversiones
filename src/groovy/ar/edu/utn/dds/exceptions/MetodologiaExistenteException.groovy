package ar.edu.utn.dds.exceptions

class MetodologiaExistenteException extends ElementoExistenteException {

    MetodologiaExistenteException(String mensaje) {
        super(mensaje)
    }

    MetodologiaExistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
