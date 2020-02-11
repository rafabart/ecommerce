package com.ecommerce.service.validation;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.dto.CustomerNewDTO;
import com.ecommerce.entity.enums.TypeCustomer;
import com.ecommerce.exception.FieldMessageError;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.validation.utils.CPFAndCNPJ;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerCreateValidator implements ConstraintValidator<CustomerCreate, CustomerNewDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerCreate constraintAnnotation) {

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

        Customer customer = customerRepository.findByEmail(customerNewDTO.getEmail());
        if (customer != null) {
            fieldMessageErrors.add(new FieldMessageError("email", "Email já existente"));
        }

        for (FieldMessageError fieldMessageError : fieldMessageErrors) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(fieldMessageError.getMessage())
                    .addPropertyNode(fieldMessageError.getFieldName()).addConstraintViolation();
        }

        return fieldMessageErrors.isEmpty();
    }
}
