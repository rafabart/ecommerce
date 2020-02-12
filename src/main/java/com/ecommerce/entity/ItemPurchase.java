package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class ItemPurchase implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private ItemPurchasePK id = new ItemPurchasePK();

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    public void setProduct(final Product product) {
        this.id.setProduct(product);
    }

    public void setPurchase(final Purchase purchase) {
        this.id.setPurchase(purchase);
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public Purchase getPurchase() {
        return this.id.getPurchase();
    }


    public double getSubTotal() {
        return (price - discount) * quantity;
    }
}
