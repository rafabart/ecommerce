package com.ecommerce.service.impl;

import com.ecommerce.entity.Purchase;
import com.ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailServiceImpl implements EmailService {


    @Value("${default.sender}")
    private String sender;


    public void sendOrderConfirmationEmail(final Purchase purchase) {
        final SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPurchase(purchase);
        sendEmail(simpleMailMessage);
    }


    protected SimpleMailMessage prepareSimpleMailMessageFromPurchase(final Purchase purchase) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(purchase.getCustomer().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido confirmado! Codigo: " + purchase.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(purchase.toString());
        return simpleMailMessage;
    }
}