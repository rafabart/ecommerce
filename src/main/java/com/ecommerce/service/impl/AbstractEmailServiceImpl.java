package com.ecommerce.service.impl;

import com.ecommerce.entity.Customer;
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


    public void sendNewPasswordEmail(final Customer customer, final String newPassword) {
        final SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromNewPassword(customer, newPassword);
        sendEmail(simpleMailMessage);
    }


    protected SimpleMailMessage prepareSimpleMailMessageFromNewPassword(Customer customer, String newPassword) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Solicitação de nova senha!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Nova senha: " + newPassword);
        return simpleMailMessage;
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