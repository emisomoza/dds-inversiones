package ar.edu.utn.dds.exceptions

class RedisInaccesibleException extends DatosInaccesiblesException {
    def RedisInaccesibleException(String mensaje) {
        super(mensaje)
    }

    def RedisInaccesibleException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }

    @Override
    String getDatabaseName() {
        return "Redis"
    }
}
