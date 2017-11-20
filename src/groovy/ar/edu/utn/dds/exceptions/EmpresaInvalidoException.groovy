package ar.edu.utn.dds.exceptions

class EmpresaInvalidoException extends ElementoInvalidoException {

    EmpresaInvalidoException(String mensaje) {
        super(mensaje)
    }

    EmpresaInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa)
    }
}
