package ar.edu.utn.dds.exceptions

class RootStatementBuilderException extends InversionesException {

    RootStatementBuilderException(String mensaje) {
        super(mensaje)
    }

    RootStatementBuilderException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
