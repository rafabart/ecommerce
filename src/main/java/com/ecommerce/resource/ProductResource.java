package com.ecommerce.resource;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.dto.ProductDTO;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductResource {

    final private ProductService productService;

    @Autowired
    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Product> findById(@PathVariable("id") final Long id) {

        final Product product = productService.findById(id);

        return ResponseEntity.ok().body(product);
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<Page<ProductDTO>> search(
            @RequestParam(value = "name", defaultValue = "0") final String name,
            @RequestParam(value = "categories", defaultValue = "24") final String categories,
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") final String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") final String direction) {

        final Page<Product> products = productService.search(name, categories, page, linesPerPage, direction, orderBy);

        final Page<ProductDTO> productDTOS = products.map(ProductDTO::new);

        return ResponseEntity.ok().body(productDTOS);
    }
}
