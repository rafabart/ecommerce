package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@JsonTypeName("creditCard")
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends Payment {

    @Column(nullable = false)
    private Integer installments;
}
