package com.example.hakimssuperserver.domain;

import com.example.hakimssuperserver.EmailServiceConf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Hodei Eceiza
 * Date: 9/13/2021
 * Time: 10:02
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EmailServiceConf.class)
class EmailServiceClientTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmailServiceAdapter emailServiceMock;

    void sendEmailReq() {


    }
/* ONLY FOR LOCAL TEST

    @Test
    @DisplayName("Test using service in localhost. sent email is success")
    void sendMailSuccess() {
        EmailReq email = new EmailReq("test@mail.com", "test2@mail.com", "test", "name","welcome");


        when(emailServiceMock.sendEmailReq(any(EmailReq.class))).thenReturn(ResponseEntity.ok().body("welcome email sent"));

        EmailServiceClient client = new EmailServiceClient(new RestTemplate(), "http://localhost:8082/welcome");

        assertEquals(emailServiceMock.sendEmailReq(email).getBody(), client.sendEmailReq(email).getBody());
    }

    @Test
    @DisplayName("Test using service in localhost. sent email is wrong")
    void sendEmailToWrongAddress() {
        EmailReq email = new EmailReq("test@mail.com", "test2@mail.com", "test", "name","welcome");
        when(emailServiceMock.sendEmailReq(any(EmailReq.class))).thenReturn(ResponseEntity.ok().body(HttpStatus.BAD_GATEWAY.toString()));

        EmailServiceClient client = new EmailServiceClient(new RestTemplate(), "http://localhost:8082/**");
        client.sendEmailReq(email);
        assertEquals(emailServiceMock.sendEmailReq(email).getBody(), client.sendEmailReq(email).getBody());
    }

 */
}