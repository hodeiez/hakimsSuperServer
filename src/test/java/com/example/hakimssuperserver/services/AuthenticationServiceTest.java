package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.ApplicationConf;
import com.example.hakimssuperserver.EmailServiceConf;
import com.example.hakimssuperserver.SecurityConf;
import com.example.hakimssuperserver.domain.EmailServiceAdapter;
import com.example.hakimssuperserver.domain.SecurityServiceAdapter;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.AdminRepository;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import com.example.hakimssuperserver.security.JWTparser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


/**
 * Created by Hodei Eceiza
 * Date: 9/14/2021
 * Time: 10:04
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
//@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {EmailServiceConf.class, ApplicationConf.class})
class AuthenticationServiceTest {



    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailServiceAdapter emailServiceAdapter;
    @Autowired
    JWTparser jWTparser;
    @Autowired
    SecurityServiceAdapter securityServiceAdapter;
    @Autowired
    String SECRET_TOKEN;


    @Test
    void getMyDetailsWhenISendTokenInHeader() {
        AdminRepository adminRepository=mock(AdminRepository.class);

        CustomerRepository customerRepository=mock(CustomerRepository.class);
        Customer expected=new Customer("user",
                "adminlast",
                "test2007@mail.com",
                " ",
                "",
                "",
                " ",
                "password");
        when(customerRepository.findCustomerByEmail(any())).thenReturn(expected);
        AuthenticationService authenticationService=new AuthenticationService(SECRET_TOKEN,customerRepository,securityServiceAdapter,passwordEncoder, adminRepository, emailServiceAdapter,jWTparser);

        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MjAwOEBtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0NVU1RPTUVSIn1dLCJpYXQiOjE2MzE2MjQ0ODEsImV4cCI6MTYzMjE1MDA4MX0.wApmUEsPOCRhzEqH0XeZQ6-tcjVubUrWrrogOeEBmbZlL9RtiF20zyHBx_dMhZt1SAC9D4i9cleojw_ngszWBA");
        assertEquals(expected,authenticationService.getMyDetails(headers).getBody());
    }
}