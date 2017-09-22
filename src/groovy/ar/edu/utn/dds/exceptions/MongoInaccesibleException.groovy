package ar.edu.utn.dds.exceptions

class MongoInaccesibleException extends DatosInaccesiblesException {
    def MongoInaccesibleException(String mensaje) {
        super(mensaje)
    }

    def MongoInaccesibleException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }

    @Override
    String getDatabaseName() {
        return "Mongo"
    }
}
