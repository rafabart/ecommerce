package com.ecommerce.service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    Category findById(final Long id);

    Long create(CategoryDTO categoryDTO);

    void update(final Long id, CategoryDTO categoryDTO);

    void deleteById(final Long id);

    List<Category> findAll();

    Page<Category> findAllPageable(final Integer page, final Integer linesPerPage,
                                   final String direction, final String orderBy);
}
