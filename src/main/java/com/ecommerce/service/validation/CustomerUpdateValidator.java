package com.ecommerce.service.validation;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.dto.CustomerDTO;
import com.ecommerce.entity.dto.CustomerNewDTO;
import com.ecommerce.entity.enums.TypeCustomer;
import com.ecommerce.exception.FieldMessageError;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.validation.utils.CPFAndCNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerDTO customerDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessageError> fieldMessageErrors = new ArrayList<>();

        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        if (customer != null && !customer.getId().equals(uriId)) {
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
