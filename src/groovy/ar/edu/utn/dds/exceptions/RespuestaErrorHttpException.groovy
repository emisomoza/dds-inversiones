package ar.edu.utn.dds.exceptions

class RespuestaErrorHttpException extends RuntimeException {
    private Integer httpStatus

    RespuestaErrorHttpException(String var1) {
        super(var1)
    }

    RespuestaErrorHttpException(String var1, Integer status) {
        super(var1)
        this.httpStatus = status
    }

    RespuestaErrorHttpException(String var1, Throwable var2) {
        super(var1, var2)
    }

    RespuestaErrorHttpException(String var1, Integer status, Throwable var2) {
        super(var1, var2)
        this.httpStatus = status
    }

    Integer getHttpStatus() {
        return httpStatus
    }

    void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus
    }
}
