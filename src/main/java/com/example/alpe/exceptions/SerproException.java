package com.example.alpe.exceptions;

public class SerproException extends Exception {

    public SerproException(String message) {
        super(message);
    }

    public SerproException(Throwable cause) {
        super(cause);
    }

    public SerproException(String message, Throwable cause) {
        super(message, cause);
    }
}
