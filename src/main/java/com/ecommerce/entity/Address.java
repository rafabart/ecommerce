package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String cep;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(nullable = false, name = "city_id")
    private City city;
}