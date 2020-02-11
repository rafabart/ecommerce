package com.ecommerce.service;

import com.ecommerce.entity.Purchase;

public interface PurchaseService {

    Purchase findById(final Long id);

    Purchase create(Purchase purchase);
}
