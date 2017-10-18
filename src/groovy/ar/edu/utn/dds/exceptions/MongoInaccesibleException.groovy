package ar.edu.utn.dds.exceptions

class MongoInaccesibleException extends DatosInaccesiblesException {

    MongoInaccesibleException(String mensaje) {
        super(mensaje)
    }

    MongoInaccesibleException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }

    @Override
    String getDatabaseName() {
        return "Mongo"
    }
}
