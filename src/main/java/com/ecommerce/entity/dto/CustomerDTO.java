package com.ecommerce.entity.dto;

import com.ecommerce.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "O campo nome esta vazio!")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String name;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "O campo email esta vazio!")
    private String email;


    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }
}
