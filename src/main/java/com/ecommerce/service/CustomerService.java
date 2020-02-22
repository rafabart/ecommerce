package com.ecommerce.service;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.dto.CustomerDTO;
import com.ecommerce.entity.dto.CustomerNewDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    Customer findById(final Long id);

    Long create(final CustomerNewDTO customerDTO);

    void update(final Long id, CustomerDTO customerDTO);

    void deleteById(final Long id);

    Customer findByEmail(final String email);

    List<Customer> findAll();

    Page<Customer> findAllPageable(final Integer page, final Integer linesPerPage,
                                   final String direction, final String orderBy);
}
