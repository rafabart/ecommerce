package com.ecommerce.service;

import com.ecommerce.entity.Purchase;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(final Purchase purchase);

    void sendEmail(final SimpleMailMessage message);
}
