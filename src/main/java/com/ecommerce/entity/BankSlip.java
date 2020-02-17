package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@JsonTypeName("bankSlip")
public class BankSlip extends Payment {

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;
}
