package com.ecommerce.config;

import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    final private DBService dbService;

    final private CategoryRepository categoryRepository;

    @Autowired
    public ProdConfig(final DBService dbService, final CategoryRepository categoryRepository) {
        this.dbService = dbService;
        this.categoryRepository = categoryRepository;
    }


    @Bean
    public void instantiateDatabase() {

        if (categoryRepository.findAll().isEmpty()) {
            dbService.instantiateTestDatabase();
        }
    }
}
