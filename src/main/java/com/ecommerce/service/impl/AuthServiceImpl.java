package com.ecommerce.service.impl;

import com.ecommerce.entity.Customer;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    final private CustomerRepository customerRepository;

    final private EmailService emailService;

    private Random random = new Random();


    @Autowired
    public AuthServiceImpl(final BCryptPasswordEncoder bCryptPasswordEncoder, final CustomerRepository customerRepository, final EmailService emailService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }


    public void sendNewPassword(final String email) {

        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            throw new ObjectNotFoundException("Email");
        }

        String newPass = newPassword();
        customer.setPassword(bCryptPasswordEncoder.encode(newPass));

        customerRepository.save(customer);
        emailService.sendNewPasswordEmail(customer, newPass);
    }


    private String newPassword() {

        char[] vet = new char[10];

        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }

        return new String(vet);

    }


    private char randomChar() {

        int opt = random.nextInt(3);

        //gera um digito
        if (opt == 0) {
            return (char) (random.nextInt(10) + 48);
            //gera letra maiuscula
        } else if (opt == 1) {
            return (char) (random.nextInt(26) + 65);
            // gera letra minuscula
        } else {
            return (char) (random.nextInt(10) + 97);
        }
    }
}
