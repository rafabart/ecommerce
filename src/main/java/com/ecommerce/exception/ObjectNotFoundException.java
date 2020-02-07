package com.ecommerce.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message + " não encontrado(a)!");
    }

    public ObjectNotFoundException(String message, Throwable cause) {

        super(message + " não encontrado(a)!", cause);
    }
}
