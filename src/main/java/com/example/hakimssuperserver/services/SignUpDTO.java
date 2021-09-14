package com.example.hakimssuperserver.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

/**
 * Created by Hodei Eceiza
 * Date: 9/13/2021
 * Time: 21:25
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String address;
    private String city;
    private String zip;
    private String password;
    private String secretToken;

    public SignUpDTO(String firstname, String lastname, String email, String telephone, String address, String city, String zip, String password, String secretToken) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.password = password;
        this.secretToken = secretToken;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretToken() {
        return secretToken;
    }
}
