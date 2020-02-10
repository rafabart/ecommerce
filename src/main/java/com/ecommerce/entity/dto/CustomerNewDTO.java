package com.ecommerce.entity.dto;

import com.ecommerce.service.validation.CpfAndCnpj;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@CpfAndCnpj
@NoArgsConstructor
public class CustomerNewDTO implements Serializable {

    @NotEmpty(message = "O campo nome esta vazio!")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String name;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "O campo email esta vazio!")
    private String email;

    private String cpfOrCnpj;

    @NotNull(message = "O campo tipo de cliente esta vazio!")
    private Integer typeCustomer;

    @NotEmpty(message = "O campo endereço esta vazio!")
    private String street;

    @NotEmpty(message = "O campo numero esta vazio!")
    private String number;

    @NotEmpty(message = "O campo bairro esta vazio!")
    private String district;

    private String complement;

    @NotEmpty(message = "O campo cep esta vazio!")
    private String cep;

    @NotEmpty(message = "O campo telefone 1 esta vazio!")
    private String phoneNumberOne;

    private String phoneNumberTwo;

    private String phoneNumberThree;

    @NotNull(message = "O campo cidade esta vazio!")
    private Long cityId;
}
