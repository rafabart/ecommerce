package com.ecommerce.repository;

import com.ecommerce.entity.ItemPurchase;
import com.ecommerce.entity.ItemPurchasePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, ItemPurchasePK> {
}
