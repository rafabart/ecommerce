package com.ecommerce.service;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Purchase;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(final Purchase purchase);

    void sendEmail(final SimpleMailMessage message);

    void sendNewPasswordEmail(final Customer customer, final String newPassword);
}
