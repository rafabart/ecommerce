package com.ecommerce.service;

import com.ecommerce.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase findById(final Long id);

    List<Purchase> findAll();

    Purchase create(Purchase purchase);
}
