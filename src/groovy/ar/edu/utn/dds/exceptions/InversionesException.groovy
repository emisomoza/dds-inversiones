package ar.edu.utn.dds.exceptions

class InversionesException extends RuntimeException {

    InversionesException(String mensaje) {
        super(mensaje)
    }

    InversionesException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
