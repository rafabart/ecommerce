package com.ecommerce.service;

import com.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(final Long id);

    Category create(Category category);

    Category update(final Long id, Category category);

    void deleteById(final Long id);

    List<Category> findAll();
}
