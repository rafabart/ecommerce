package com.ecommerce.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super("Acesso negado!");
    }

    public AuthorizationException(String message, Throwable cause) {

        super(message, cause);
    }
}
