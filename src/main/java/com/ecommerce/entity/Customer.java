package com.ecommerce.entity;

import com.ecommerce.entity.enums.Profile;
import com.ecommerce.entity.enums.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    @JsonIgnore
    private String password;

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

    @CollectionTable
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> profiles = new HashSet<>();


    public Customer() {
        addProfile(Profile.CUSTOMER);
    }

    public Customer(Long id, String name, String email, String password, String cpfOrCnpj, TypeCustomer typeCustomer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpfOrCnpj = cpfOrCnpj;
        this.typeCustomer = typeCustomer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }

    public Set<Profile> getProfiles() {
        return this.profiles.stream().map(profile -> Profile.toEnum(profile)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
