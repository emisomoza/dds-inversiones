package ar.edu.utn.dds.exceptions

abstract class DatosInaccesiblesException extends ErrorInesperadoException {
    DatosInaccesiblesException(String mensaje) {
        super(mensaje)
    }

    DatosInaccesiblesException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }

    abstract String getDatabaseName()

    @Override
    String getMessage() {
        return "Error de acceso a " + getDatabaseName() + ": " + super.getMessage()
    }
}
