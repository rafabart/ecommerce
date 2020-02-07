package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankSlip extends Payment {

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private Date paymentDate;
}
