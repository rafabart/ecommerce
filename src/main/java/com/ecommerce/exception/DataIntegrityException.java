package com.ecommerce.exception;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException() {
        super("Não é possível excluir uma categoria com produtos associados!");
    }

    public DataIntegrityException(String message, Throwable cause) {

        super(message, cause);
    }
}
