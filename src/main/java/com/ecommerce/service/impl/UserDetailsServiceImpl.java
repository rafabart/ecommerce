package com.ecommerce.service.impl;

import com.ecommerce.entity.Customer;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getProfiles());
    }
}
