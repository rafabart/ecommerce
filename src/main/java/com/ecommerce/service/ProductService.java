package com.ecommerce.service;

import com.ecommerce.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Product findById(final Long id);

    Page<Product> search(final String name, final String ids, final Integer page,
                         final Integer linesPerPage, final String direction, final String orderBy);
}
