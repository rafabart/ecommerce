package com.ecommerce.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmailDTO implements Serializable {

    @Email(message = "Email inválido!")
    @NotEmpty(message = "O campo email esta vazio!")
    private String email;
}
