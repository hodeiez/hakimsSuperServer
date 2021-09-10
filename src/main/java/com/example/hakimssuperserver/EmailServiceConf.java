package com.example.hakimssuperserver;

import com.example.hakimssuperserver.domain.EmailServiceAdapter;
import com.example.hakimssuperserver.domain.EmailServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Bean
    public EmailServiceAdapter emailServiceAdapter(){
       return new EmailServiceClient(new RestTemplate(),"http://localhost:8082/welcome");
    }
}
