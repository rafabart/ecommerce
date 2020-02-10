package com.ecommerce.exception;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldMessageError> errors = new ArrayList<>();

    public ValidationError(final Integer status, final String message, final Long timeStamp) {
        super(status, message, timeStamp);
    }


    public List<FieldMessageError> getErrors() {
        return errors;
    }


    public void addError(final String fieldName, final String message) {
        errors.add(new FieldMessageError(fieldName, message));
    }
}
