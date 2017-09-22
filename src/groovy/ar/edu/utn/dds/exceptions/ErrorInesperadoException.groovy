package ar.edu.utn.dds.exceptions

class ErrorInesperadoException extends RuntimeException {

    ErrorInesperadoException(String mensaje) {
        super(mensaje)
    }

    ErrorInesperadoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
