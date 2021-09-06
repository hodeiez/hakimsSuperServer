package com.example.hakimssuperserver.domain;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hodei Eceiza
 * Date: 9/6/2021
 * Time: 16:16
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
class SecurityServiceClientTest {

    @Test
    void sendLoginReq() {
        SecurityServiceClient securityServiceClient=new SecurityServiceClient(new RestTemplate(), "http://localhost:8082");
        LoginReq loginReq = new LoginReq("test@mail.com","password");
        securityServiceClient.sendLoginReq(loginReq);
    }
}