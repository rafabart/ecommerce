package com.ecommerce.entity.dto;

import com.ecommerce.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotEmpty(message = "O campo nome esta vazio!")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
