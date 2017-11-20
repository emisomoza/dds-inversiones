package ar.edu.utn.dds.exceptions

class TipoCuenaInvalidoException extends ElementoInvalidoException {

    TipoCuenaInvalidoException(String mensaje) {
        super(mensaje)
    }

    TipoCuenaInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
