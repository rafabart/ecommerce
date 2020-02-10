package com.ecommerce.resource;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.dto.CustomerDTO;
import com.ecommerce.entity.dto.CustomerNewDTO;
import com.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    final private CustomerService customerService;

    @Autowired
    public CustomerResource(final CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Customer> findById(@PathVariable("id") final Long id) {

        final Customer customer = customerService.findById(id);

        return ResponseEntity.ok().body(customer);
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@Valid @RequestBody CustomerNewDTO customerDTO) {

        final Long id = customerService.create(customerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(path = "/{id}", consumes = {"application/json"})
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @Valid @RequestBody CustomerDTO customerDTO) {

        customerService.update(id, customerDTO);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {

        customerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<CustomerDTO>> findAll() {

        final List<Customer> categories = customerService.findAll();

        final List<CustomerDTO> list = categories.stream().map(CustomerDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = "/page", produces = {"application/json"})
    public ResponseEntity<Page<CustomerDTO>> findAllPageable(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") final String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") final String direction) {

        final Page<Customer> categories = customerService.findAllPageable(page, linesPerPage, direction, orderBy);

        final Page<CustomerDTO> list = categories.map(CustomerDTO::new);

        return ResponseEntity.ok().body(list);
    }
}
