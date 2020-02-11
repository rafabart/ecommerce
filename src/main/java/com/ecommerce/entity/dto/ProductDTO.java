package com.ecommerce.entity.dto;

import com.ecommerce.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private Double price;

    public ProductDTO(final Product product) {

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
