package com.ecommerce.entity;

import com.ecommerce.entity.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonTypeName("creditCard")
public class CreditCard extends Payment {

    @Column(nullable = false)
    private Integer installments;

    public CreditCard() {
    }

    public CreditCard(Long id, StatusPayment statusPayment, Purchase purchase, Integer installments) {
        super(id, statusPayment, purchase);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
