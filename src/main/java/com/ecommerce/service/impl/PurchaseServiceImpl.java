package com.ecommerce.service.impl;

import com.ecommerce.entity.BankSlip;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.ItemPurchase;
import com.ecommerce.entity.Purchase;
import com.ecommerce.entity.enums.StatusPayment;
import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.ItemPurchaseRepository;
import com.ecommerce.repository.PaymentRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.security.UserSpringSecurity;
import com.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    final private PurchaseRepository purchaseRepository;

    final private BankSlipService bankSlipService;

    final private PaymentRepository paymentRepository;

    final private ProductService productService;

    final private ItemPurchaseRepository itemPurchaseRepository;

    final private EmailService emailService;

    final private CustomerService customerService;

    @Autowired
    public PurchaseServiceImpl(final PurchaseRepository purchaseRepository, final BankSlipService bankSlipService,
                               final PaymentRepository paymentRepository, final ProductService productService,
                               final ItemPurchaseRepository itemPurchaseRepository, final EmailService emailService,
                               final CustomerService customerService) {
        this.purchaseRepository = purchaseRepository;
        this.bankSlipService = bankSlipService;
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.itemPurchaseRepository = itemPurchaseRepository;
        this.emailService = emailService;
        this.customerService = customerService;
    }

    public Purchase findById(final Long id) {

        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Pedido")
        );

        return purchase;
    }


    public List<Purchase> findAll() {

        List<Purchase> purchases = purchaseRepository.findAll();

        return purchases;
    }


    public Page<Purchase> findAllPageable(final Integer page, final Integer linesPerPage,
                                          final String direction, final String orderBy) {

        UserSpringSecurity user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationException();
        }

        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        final Customer customer = customerService.findById(user.getId());

        return purchaseRepository.findByCustomer(customer, pageRequest);
    }


    @Transactional
    public Purchase create(Purchase purchase) {

        purchase.setId(null);
        purchase.setDate(new Date());
        purchase.getPayment().setStatusPayment(StatusPayment.PENDING);
        purchase.getPayment().setPurchase(purchase);
        purchase.setCustomer(customerService.findById(purchase.getCustomer().getId()));

        if (purchase.getPayment() instanceof BankSlip) {
            final BankSlip payment = (BankSlip) purchase.getPayment();

            bankSlipService.fillPaymentWithBankSlip(payment, purchase.getDate());
        }

        purchase = purchaseRepository.save(purchase);

        paymentRepository.save(purchase.getPayment());

        for (ItemPurchase itemPurchase : purchase.getItemPurchases()) {
            itemPurchase.setPurchase(purchase);
            itemPurchase.setProduct(productService.findById(itemPurchase.getProduct().getId()));
            itemPurchase.setDiscount(00.00);
            itemPurchase.setPrice(itemPurchase.getProduct().getPrice());
        }

        itemPurchaseRepository.saveAll(purchase.getItemPurchases());

        emailService.sendOrderConfirmationEmail(purchase);

        return purchase;
    }
}
