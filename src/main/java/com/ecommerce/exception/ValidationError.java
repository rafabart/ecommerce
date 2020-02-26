package com.ecommerce.exception;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldMessageError> errors = new ArrayList<>();

    public ValidationError(final Long timeStamp, final Integer status, final String error, final String message, final String path) {
        super(timeStamp, status, error, message, path);
    }


    public List<FieldMessageError> getErrors() {
        return errors;
    }


    public void addError(final String fieldName, final String message) {
        errors.add(new FieldMessageError(fieldName, message));
    }
}
