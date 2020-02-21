package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
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

    public ItemPurchase() {
    }

    public ItemPurchase(Purchase purchase, Product product, Double discount, Integer quantity, Double price) {
        super();
        this.id.setPurchase(purchase);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - discount) * quantity;
    }

    @JsonIgnore
    public Purchase getPurchase() {
        return this.id.getPurchase();
    }

    public void setPurchase(final Purchase purchase) {
        this.id.setPurchase(purchase);
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public void setProduct(final Product product) {
        this.id.setProduct(product);
    }

    public ItemPurchasePK getId() {
        return id;
    }

    public void setId(ItemPurchasePK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPurchase that = (ItemPurchase) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getName());
        builder.append(", Qte: ");
        builder.append(getQuantity());
        builder.append(", Preço unitário: ");
        builder.append(nf.format(getPrice()));
        builder.append(", Subtotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }
}
