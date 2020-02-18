package com.ecommerce.entity.dto;

import java.io.Serializable;

public class CredentialDTO implements Serializable {

    private String email;

    private String password;


    public CredentialDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
