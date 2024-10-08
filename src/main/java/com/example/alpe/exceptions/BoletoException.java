package com.example.alpe.exceptions;

public class BoletoException extends Exception {

    public BoletoException(String message) { super(message); }

    public BoletoException(Throwable cause) {
        super(cause);
    }

    public BoletoException(String message, Throwable cause) {
        super(message, cause);
    }
}
