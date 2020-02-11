package com.ecommerce.service.impl;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    final private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, final CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public Product findById(final Long id) {

        final Product product = productRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Categoria")
        );

        return product;
    }


    public Page<Product> search(final String name, final String ids, final Integer page,
                                final Integer linesPerPage, final String direction, final String orderBy) {

        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        final List<Long> longListofIds = decodeLongList(ids);

        final String nameDecoded = decodeParam(name);

        final List<Category> categories = categoryRepository.findAllById(longListofIds);

        return productRepository.search(nameDecoded, categories, pageRequest);
    }


    private List<Long> decodeLongList(final String stringList) {

        final String[] vet = stringList.split(",");

        List<Long> longList = new ArrayList<>();

        for (String item : vet) {
            longList.add(Long.parseLong(item));
        }

        return longList;
    }


    private String decodeParam(final String name) {
        try {
            return URLDecoder.decode(name, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
