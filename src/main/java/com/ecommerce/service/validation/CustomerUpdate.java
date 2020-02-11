package com.ecommerce.service.validation;

import org.jvnet.staxex.StAxSOAPBody;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomerUpdateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerUpdate {

    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends StAxSOAPBody.Payload>[] payload() default {};
}
