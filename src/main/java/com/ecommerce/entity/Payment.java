package com.ecommerce.entity;

import com.ecommerce.entity.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment implements Serializable {

    @Id
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private StatusPayment statusPayment;

    @MapsId
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
}

