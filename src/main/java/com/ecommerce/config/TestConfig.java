package com.ecommerce.config;

import com.ecommerce.service.DBService;
import com.ecommerce.service.EmailService;
import com.ecommerce.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    final private DBService dbService;

    @Autowired
    public TestConfig(final DBService dbService) {
        this.dbService = dbService;
    }


    @Bean
    public void instantiateDatabase() {
        dbService.instantiateTestDatabase();
    }


    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
