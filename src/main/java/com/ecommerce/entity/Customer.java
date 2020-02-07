package com.ecommerce.entity;

import com.ecommerce.entity.enums.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpfOrCnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeCustomer typeCustomer;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable
    private Set<String> phoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases = new ArrayList<>();
}
