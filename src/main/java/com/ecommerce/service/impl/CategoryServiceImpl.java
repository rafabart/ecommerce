package com.ecommerce.service.impl;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.DataIntegrityException;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Category create(Category category) {

        category.setId(null);

        return categoryRepository.save(category);
    }


    public Category update(final Long id, Category category) {

        findById(id);
        category.setId(id);

        return categoryRepository.save(category);
    }


    public void deleteById(final Long id) {

        findById(id);

        try {

            categoryRepository.deleteById(id);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException();
        }
    }


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
