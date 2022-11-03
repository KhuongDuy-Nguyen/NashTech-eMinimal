package com.eminimal.backend.exceptions;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -3808246479819405631L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
