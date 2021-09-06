package com.example.hakimssuperserver.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
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
    void sendLoginReq() throws JsonProcessingException {
        SecurityServiceClient securityServiceClient=new SecurityServiceClient(new RestTemplate(), "http://localhost:8080/test");
        LoginReq loginReq = new LoginReq("test@mail.com","password");
        ObjectMapper o=new ObjectMapper();
        Assertions.assertEquals(o.writeValueAsString(loginReq),securityServiceClient.sendLoginReq(loginReq).getBody());
    }
}