package com.example.hakimssuperserver.domain;

/**
 * Created by Hodei Eceiza
 * Date: 9/17/2021
 * Time: 14:32
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public enum EmailType {
    WELCOME("welcome"),CONFIRM("confirm");
    private final String value;
    EmailType(String valueName) {
            value=valueName;
    }

    @Override
    public String toString() {
        return  value;
    }
}
