package com.example.hakimssuperserver.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hodei Eceiza
 * Date: 9/6/2021
 * Time: 16:01
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface EmailServiceAdapter {
    ResponseEntity<String> sendEmailReq(EmailReq emailReq);

}
