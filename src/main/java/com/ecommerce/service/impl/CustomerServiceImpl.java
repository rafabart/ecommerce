package com.ecommerce.service.impl;

import com.ecommerce.entity.Customer;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer findById(final Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Cliente")
        );

        return customer;
    }
}
