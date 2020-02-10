package com.ecommerce.service.validation;

import com.ecommerce.entity.dto.CustomerNewDTO;
import com.ecommerce.entity.enums.TypeCustomer;
import com.ecommerce.exception.FieldMessageError;
import com.ecommerce.service.validation.utils.CPFAndCNPJ;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CpfAndCnpjValidator implements ConstraintValidator<CpfAndCnpj, CustomerNewDTO> {

    @Override
    public void initialize(CpfAndCnpj constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerNewDTO customerNewDTO, ConstraintValidatorContext context) {

        List<FieldMessageError> fieldMessageErrors = new ArrayList<>();

        if (customerNewDTO.getTypeCustomer().equals(TypeCustomer.NATURALPERSON.getId()) && !CPFAndCNPJ.isValidCPF(customerNewDTO.getCpfOrCnpj())) {
            fieldMessageErrors.add(new FieldMessageError("cpfOrCnpj", "CPF inválido!"));
        }

        if (customerNewDTO.getTypeCustomer().equals(TypeCustomer.LEGALPERSON.getId()) && !CPFAndCNPJ.isValidCNPJ(customerNewDTO.getCpfOrCnpj())) {
            fieldMessageErrors.add(new FieldMessageError("cpfOrCnpj", "CNPJ inválido!"));
        }

        for (FieldMessageError fieldMessageError : fieldMessageErrors) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(fieldMessageError.getMessage())
                    .addPropertyNode(fieldMessageError.getFieldName()).addConstraintViolation();
        }

        return fieldMessageErrors.isEmpty();
    }
}
