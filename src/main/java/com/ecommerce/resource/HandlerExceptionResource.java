package com.ecommerce.resource;

import com.ecommerce.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptionResource {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(final ObjectNotFoundException e, final HttpServletRequest request) {

        final StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }


    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(final DataIntegrityException e, final HttpServletRequest request) {

        final StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Integridade de dados", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(final MethodArgumentNotValidException e, final HttpServletRequest request) {

        final ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", e.getMessage(), request.getRequestURI());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors())
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(final AuthorizationException e, final HttpServletRequest request) {

        final StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
                "Acesso negado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }
}
