package com.example.hakimssuperserver.domain;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hodei Eceiza
 * Date: 9/10/2021
 * Time: 09:08
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public class EmailServiceClient implements EmailServiceAdapter{
    private final RestTemplate restTemplate;
    private final String baseUrl;


    public EmailServiceClient(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public ResponseEntity<String> sendEmailReq(EmailReq emailReq) {
        try{
      ResponseEntity<String> response= restTemplate.postForEntity(baseUrl,emailReq,String.class,String.class);
        return response;}
        catch(Exception e){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_GATEWAY.toString());
        }
    }


}
