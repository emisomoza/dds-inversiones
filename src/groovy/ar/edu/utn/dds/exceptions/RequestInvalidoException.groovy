package ar.edu.utn.dds.exceptions

class RequestInvalidoException extends RuntimeException {
    RequestInvalidoException(String message) {
        super(message)
    }

    RequestInvalidoException(String var1, Throwable var2) {
        super(var1, var2)
    }
}
