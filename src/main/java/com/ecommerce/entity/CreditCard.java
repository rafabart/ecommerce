package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends Payment {

    @Column(nullable = false)
    private Integer installments;
}
