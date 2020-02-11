package com.ecommerce.service.impl;

import com.ecommerce.entity.BankSlip;
import com.ecommerce.entity.ItemPurchase;
import com.ecommerce.entity.Purchase;
import com.ecommerce.entity.enums.StatusPayment;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.ItemPurchaseRepository;
import com.ecommerce.repository.PaymentRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.service.BankSlipService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    final private PurchaseRepository purchaseRepository;

    final private BankSlipService bankSlipService;

    final private PaymentRepository paymentRepository;

    final private ProductService productService;

    final private ItemPurchaseRepository itemPurchaseRepository;

    @Autowired
    public PurchaseServiceImpl(final PurchaseRepository purchaseRepository, final BankSlipService bankSlipService,
                               final PaymentRepository paymentRepository, final ProductService productService,
                               final ItemPurchaseRepository itemPurchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.bankSlipService = bankSlipService;
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.itemPurchaseRepository = itemPurchaseRepository;
    }

    public Purchase findById(final Long id) {

        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Pedido")
        );

        return purchase;
    }


    @Transactional
    public Purchase create(Purchase purchase) {

        purchase.setId(null);
        purchase.setDate(new Date());
        purchase.getPayment().setStatusPayment(StatusPayment.PENDING);
        purchase.getPayment().setPurchase(purchase);

        if (purchase.getPayment() instanceof BankSlip) {
            final BankSlip payment = (BankSlip) purchase.getPayment();

            bankSlipService.fillPaymentWithBankSlip(payment, purchase.getDate());
        }

        purchase = purchaseRepository.save(purchase);

        paymentRepository.save(purchase.getPayment());

        for (ItemPurchase itemPurchase : purchase.getItemPurchases()) {
            itemPurchase.setDiscount(0.0);
            itemPurchase.setPrice(productService.findById(itemPurchase.getId().getProduct().getId()).getPrice());
            itemPurchase.getId().setPurchase(purchase);
        }

        itemPurchaseRepository.saveAll(purchase.getItemPurchases());
        return purchase;
    }
}
