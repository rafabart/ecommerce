package com.ecommerce.config;

import com.ecommerce.entity.BankSlip;
import com.ecommerce.entity.CreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

            @Override
            public void configure(ObjectMapper objectMapper) {

                objectMapper.registerSubtypes(BankSlip.class);
                objectMapper.registerSubtypes(CreditCard.class);

                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
