package com.ecommerce.service;

import com.ecommerce.entity.Customer;

public interface CustomerService {

    Customer findById(final Long id);
}
