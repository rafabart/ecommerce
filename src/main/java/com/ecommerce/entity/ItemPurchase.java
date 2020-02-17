package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    public Purchase getPurchase() {
        return this.id.getPurchase();
    }

    public double getSubTotal() {
        return (price - discount) * quantity;
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
