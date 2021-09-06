package com.example.hakimssuperserver.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hodei Eceiza
 * Date: 9/6/2021
 * Time: 16:08
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public class SecurityServiceClient implements SecurityServiceAdapter{
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public SecurityServiceClient(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public ResponseEntity<String> sendLoginReq(LoginReq loginReq) {
        return restTemplate.postForEntity(baseUrl,loginReq,String.class,String.class);
    }
}
