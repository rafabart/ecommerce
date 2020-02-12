package com.ecommerce.resource;

import com.ecommerce.entity.Purchase;
import com.ecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseResource {

    final private PurchaseService purchaseService;

    @Autowired
    public PurchaseResource(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id) {

        final Purchase purchase = purchaseService.findById(id);

        return ResponseEntity.ok().body(purchase);
    }


    @GetMapping
    public ResponseEntity<List<Purchase>> findAll() {

        final List<Purchase> purchases = purchaseService.findAll();

        return ResponseEntity.ok().body(purchases);
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@Valid @RequestBody Purchase purchase) {

        final Long id = purchaseService.create(purchase).getId();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
