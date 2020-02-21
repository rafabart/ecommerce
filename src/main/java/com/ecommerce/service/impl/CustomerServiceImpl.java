package com.ecommerce.service.impl;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.City;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.dto.CustomerDTO;
import com.ecommerce.entity.dto.CustomerNewDTO;
import com.ecommerce.entity.enums.Profile;
import com.ecommerce.entity.enums.TypeCustomer;
import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.exception.DataIntegrityException;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.security.UserSpringSecurity;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Customer findById(final Long id) {

        UserSpringSecurity user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException();
        }

        final Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Cliente")
        );

        return customer;
    }


    @Transactional
    public Long create(final CustomerNewDTO customerDTO) {

        final Customer customer = fromDTO(customerDTO);

        return customerRepository.save(customer).getId();
    }


    @Transactional
    public void update(final Long id, final CustomerDTO customerDTO) {

        Customer customer = findById(id);

        updateCustomerFromDTO(customer, customerDTO);

        customerRepository.save(customer);
    }


    @Transactional
    public void deleteById(final Long id) {

        findById(id);

        try {

            customerRepository.deleteById(id);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException();
        }
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Page<Customer> findAllPageable(final Integer page, final Integer linesPerPage,
                                          final String direction, final String orderBy) {

        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return customerRepository.findAll(pageRequest);
    }


    public Customer fromDTO(final CustomerDTO customerDTO) {

        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null, null);

        return customer;
    }


    public Customer fromDTO(final CustomerNewDTO customerDTO) {

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCpfOrCnpj(customerDTO.getCpfOrCnpj());
        customer.setTypeCustomer(TypeCustomer.toEnum(customerDTO.getTypeCustomer()));
        customer.getPhoneNumbers().add(customerDTO.getPhoneNumberOne());
        customer.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));

        if (customerDTO.getPhoneNumberTwo() != null) {
            customer.getPhoneNumbers().add(customerDTO.getPhoneNumberTwo());
        }
        if (customerDTO.getPhoneNumberThree() != null) {
            customer.getPhoneNumbers().add(customerDTO.getPhoneNumberThree());
        }

        City city = new City(customerDTO.getCityId(), null, null);

        Address address = new Address();
        address.setStreet(customerDTO.getStreet());
        address.setNumber(customerDTO.getNumber());
        address.setComplement(customerDTO.getComplement());
        address.setDistrict(customerDTO.getDistrict());
        address.setCep(customerDTO.getCep());
        address.setCustomer(customer);
        address.setCity(city);

        customer.getAddresses().add(address);

        return customer;
    }


    private void updateCustomerFromDTO(Customer customer, final CustomerDTO customerDTO) {

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
    }
}
