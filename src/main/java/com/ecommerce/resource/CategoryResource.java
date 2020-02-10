package com.ecommerce.resource;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.dto.CategoryDTO;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Void> create(@Valid @RequestBody CategoryDTO categoryDTO) {

        final Long id = categoryService.create(categoryDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") final Long id,@Valid @RequestBody CategoryDTO categoryDTO) {

        categoryService.update(id, categoryDTO);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {

        final List<Category> categories = categoryService.findAll();

        final List<CategoryDTO> list = categories.stream().map(CategoryDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDTO>> findAllPageable(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") final String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") final String direction) {

        final Page<Category> categories = categoryService.findAllPageable(page, linesPerPage, direction, orderBy);

        final Page<CategoryDTO> list = categories.map(CategoryDTO::new);

        return ResponseEntity.ok().body(list);
    }
}
