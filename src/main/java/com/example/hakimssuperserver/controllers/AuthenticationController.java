package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.domain.LoginReq;
import com.example.hakimssuperserver.services.AuthenticationService;
import com.example.hakimssuperserver.services.SignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Hodei Eceiza
 * Date: 9/13/2021
 * Time: 21:50
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@CrossOrigin
@AllArgsConstructor
@RestController
public class AuthenticationController {
//we let them open to everybody
    private final AuthenticationService authenticationService;
//OPEN
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO){
        return authenticationService.signUp(signUpDTO);
    }
    //OPEN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq loginReq){
        return authenticationService.login(loginReq);
    }


}
