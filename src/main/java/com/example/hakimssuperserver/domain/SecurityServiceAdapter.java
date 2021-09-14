package com.example.hakimssuperserver.domain;

import org.springframework.http.ResponseEntity;

/**
 * Created by Hodei Eceiza
 * Date: 9/6/2021
 * Time: 15:59
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface SecurityServiceAdapter {
    ResponseEntity<String> sendLoginReq (LoginReq loginReq);
    ResponseEntity<String> sendSignUpReq(SignUpReq signupReq);
}
