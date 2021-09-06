package com.example.hakimssuperserver.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hodei Eceiza
 * Date: 9/6/2021
 * Time: 16:11
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReq {
    @JsonProperty(value="username")
    private String email;
    private String password;

    //@JsonCreator
    public LoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
