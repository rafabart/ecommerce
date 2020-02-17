package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "id.purchase")
    private Set<ItemPurchase> itemPurchases = new HashSet<>();


    public double getTotalValue() {

        double sun = 0.0;

        for (ItemPurchase itemPurchase : itemPurchases) {
            sun = sun + itemPurchase.getSubTotal();
        }

        return sun;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getDate()));
        builder.append(", Cliente: ");
        builder.append(getCustomer().getName());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getStatusPayment().getName());
        builder.append("\nDetalhes:\n");
        for (ItemPurchase ip : getItemPurchases()) {
            builder.append(ip.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getTotalValue()));
        return builder.toString();
    }
}
