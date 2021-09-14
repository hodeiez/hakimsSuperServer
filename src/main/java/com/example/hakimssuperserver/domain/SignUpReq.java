package com.example.hakimssuperserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hodei Eceiza
 * Date: 9/13/2021
 * Time: 21:12
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpReq {
    @JsonProperty(value="username")
    private String email;
    private String password;
    private String roleType;

    public SignUpReq(String email, String password, String roleType) {
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleType() {
        return roleType;
    }
}
