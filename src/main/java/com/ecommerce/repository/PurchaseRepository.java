package com.ecommerce.repository;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Page<Purchase> findByCustomer(final Customer customer, final Pageable pageable);
}
