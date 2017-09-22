package ar.edu.utn.dds.exceptions

class SQLInaccesibleException extends DatosInaccesiblesException {
    SQLInaccesibleException(String mensaje) {
        super(mensaje)
    }

    SQLInaccesibleException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }

    @Override
    String getDatabaseName() {
        return "SQL"
    }
}
