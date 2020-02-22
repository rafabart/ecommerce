package com.ecommerce.service;

import com.ecommerce.entity.Purchase;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PurchaseService {

    Purchase findById(final Long id);

    List<Purchase> findAll();

    Purchase create(Purchase purchase);

    Page<Purchase> findAllPageable(final Integer page, final Integer linesPerPage,
                                   final String direction, final String orderBy);
}
