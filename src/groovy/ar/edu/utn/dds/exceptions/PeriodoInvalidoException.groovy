package ar.edu.utn.dds.exceptions

class PeriodoInvalidoException extends ElementoInvalidoException {

    PeriodoInvalidoException(String mensaje) {
        super(mensaje)
    }

    PeriodoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
