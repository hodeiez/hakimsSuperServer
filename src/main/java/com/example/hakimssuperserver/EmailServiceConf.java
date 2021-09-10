package com.example.hakimssuperserver;

import com.example.hakimssuperserver.domain.EmailServiceAdapter;
import com.example.hakimssuperserver.domain.EmailServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hodei Eceiza
 * Date: 9/10/2021
 * Time: 09:42
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Configuration
public class EmailServiceConf {
    @Value("${super-email-service-address}")
    private String emailServiceAddress;
    @Bean
    public EmailServiceAdapter emailServiceAdapter(){
       return new EmailServiceClient(new RestTemplate(),emailServiceAddress+"/welcome");
    }
}
