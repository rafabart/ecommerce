package com.ecommerce.entity.response;

import com.ecommerce.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryWithoutProductsResponse {

    private Long id;

    private String name;

    public CategoryWithoutProductsResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
