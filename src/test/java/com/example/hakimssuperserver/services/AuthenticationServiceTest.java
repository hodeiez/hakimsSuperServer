package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.EmailServiceConf;
import com.example.hakimssuperserver.SecurityConf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;

import javax.servlet.http.HttpServletRequestWrapper;

import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hodei Eceiza
 * Date: 9/14/2021
 * Time: 10:04
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@SpringBootTest
@ContextConfiguration(classes = {EmailServiceConf.class, SecurityConf.class})
class AuthenticationServiceTest {
@Autowired
AuthenticationService authenticationService;
    @Test
    void getMyDetails() {

    }
}