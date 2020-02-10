package com.ecommerce.service.impl;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.dto.CategoryDTO;
import com.ecommerce.exception.DataIntegrityException;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category findById(final Long id) {

        final Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Categoria")
        );

        return category;
    }


    @Transactional
    public Long create(CategoryDTO categoryDTO) {

        categoryDTO.setId(null);

        final Category category = fromDTO(categoryDTO);

        return categoryRepository.save(category).getId();
    }


    @Transactional
    public void update(final Long id, final CategoryDTO categoryDTO) {

        Category category = findById(id);

        updateCategoryFromDTO(category, categoryDTO);

        categoryRepository.save(category);
    }


    @Transactional
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


    public Page<Category> findAllPageable(final Integer page, final Integer linesPerPage,
                                          final String direction, final String orderBy) {

        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return categoryRepository.findAll(pageRequest);
    }


    public Category fromDTO(final CategoryDTO categoryDTO) {

        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }


    private void updateCategoryFromDTO(Category category, final CategoryDTO categoryDTO) {

        category.setName(categoryDTO.getName());
    }
}
