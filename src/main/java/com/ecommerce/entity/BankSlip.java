package com.ecommerce.entity;

import com.ecommerce.entity.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("bankSlip")
public class BankSlip extends Payment {

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public BankSlip() {
    }

    public BankSlip(Long id, StatusPayment statusPayment, Purchase purchase, Date dueDate, Date paymentDate) {
        super(id, statusPayment, purchase);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
