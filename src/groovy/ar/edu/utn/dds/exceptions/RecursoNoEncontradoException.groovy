package ar.edu.utn.dds.exceptions

class RecursoNoEncontradoException extends InversionesException {

    RecursoNoEncontradoException(String mensaje) {
        super(mensaje)
    }

    RecursoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
