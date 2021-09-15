package com.example.hakimssuperserver.domain;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> sendLoginReq(LoginReq loginReq) {
        try{
        ResponseEntity<String> response= restTemplate.postForEntity(baseUrl+"/login",loginReq,String.class,String.class);
        TokenDTO token=new TokenDTO(response.getBody());
        return ResponseEntity.ok().body(token);}
        catch(Exception e){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_GATEWAY.toString());
        }
    }

    @Override
    public ResponseEntity<String> sendSignUpReq(SignUpReq signupReq) {
       try {
           ResponseEntity<String> response = restTemplate.postForEntity(baseUrl+"/signup", signupReq, String.class, String.class);
            return response;
       }catch(Exception e){
           return ResponseEntity.badRequest().body(HttpStatus.BAD_GATEWAY.toString());
       }
    }
}
