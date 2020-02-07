package com.ecommerce.resource;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.response.CategoryWithoutProductsResponse;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    final private CategoryService categoryService;

    @Autowired
    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Category> findById(@PathVariable("id") final Long id) {

        final Category category = categoryService.findById(id);

        return ResponseEntity.ok().body(category);
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody Category categoryRequest) {

        final Category category = categoryService.create(categoryRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody Category categoryRequest) {

        final Category category = categoryService.update(id, categoryRequest);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CategoryWithoutProductsResponse>> findAll() {

        final List<Category> categories = categoryService.findAll();

        final List<CategoryWithoutProductsResponse> list = categories.stream().map(CategoryWithoutProductsResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }
}
