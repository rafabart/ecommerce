package com.ecommerce.service.impl;

import com.ecommerce.entity.Purchase;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    final private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase findById(final Long id) {

        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Pedido")
        );

        return purchase;
    }
}
