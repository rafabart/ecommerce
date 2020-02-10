package com.ecommerce.entity;

import com.ecommerce.entity.enums.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @CollectionTable
    @ElementCollection
    private Set<String> phoneNumbers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases = new ArrayList<>();
}
