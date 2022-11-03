package com.eminimal.backend.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException{

    @Serial
    private static final long  serialVersionUID = 1;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
