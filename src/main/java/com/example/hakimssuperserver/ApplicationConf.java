package com.example.hakimssuperserver;

import com.example.hakimssuperserver.security.JWTparser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:44
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Configuration
public class ApplicationConf {
    @Value("${security.key}")
    private String key;


    @Value("${security.algorithm}")
    private String algorithm;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public JWTparser getJwtparser(){
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.forName(algorithm);
        final byte[] signingKeyBytes = Base64.encodeBase64(key.getBytes());
        return new JWTparser(new SecretKeySpec(signingKeyBytes, signatureAlgorithm.getJcaName()));

    }
}
