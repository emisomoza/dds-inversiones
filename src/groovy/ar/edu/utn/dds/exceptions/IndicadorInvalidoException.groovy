package ar.edu.utn.dds.exceptions

class IndicadorInvalidoException extends ElementoInvalidoException {

    IndicadorInvalidoException(String mensaje) {
        super(mensaje)
    }

    IndicadorInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
