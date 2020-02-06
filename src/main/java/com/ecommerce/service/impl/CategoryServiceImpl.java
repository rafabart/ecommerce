package com.ecommerce.service.impl;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(final Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Categoria")
        );

        return category;
    }
}
