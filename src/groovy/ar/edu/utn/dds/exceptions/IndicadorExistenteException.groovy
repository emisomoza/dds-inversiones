package ar.edu.utn.dds.exceptions

class IndicadorExistenteException extends ElementoExistenteException {

    IndicadorExistenteException(String mensaje) {
        super(mensaje)
    }

    IndicadorExistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
