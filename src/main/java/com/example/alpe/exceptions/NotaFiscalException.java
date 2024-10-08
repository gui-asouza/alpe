package com.example.alpe.exceptions;

public class NotaFiscalException extends Exception {

    public NotaFiscalException(String message) {
        super(message);
    }

    public NotaFiscalException(Throwable cause) {
        super(cause);
    }

    public NotaFiscalException(String message, Throwable cause) {
        super(message, cause);
    }
}
