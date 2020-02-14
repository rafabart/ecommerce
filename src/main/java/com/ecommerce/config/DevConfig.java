package com.ecommerce.config;

import com.ecommerce.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    final private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Autowired
    public DevConfig(final DBService dbService) {
        this.dbService = dbService;
    }


    @Bean
    public void instantiateDatabase() {

        if (strategy.equals("create")) {
            dbService.instantiateTestDatabase();
        }
    }
}
