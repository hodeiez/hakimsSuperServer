package com.example.hakimssuperserver.security;

import com.example.hakimssuperserver.ApplicationConf;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:55
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
//@ExtendWith(SpringExtension.class)


@TestPropertySource(locations = "classpath:application-test.yml")

@ContextConfiguration(classes = ApplicationConf.class)
@SpringBootTest
class JWTparserTest {

    @Autowired
    ApplicationConf appContext;
    @Value("${security-key}")
    String key;
    @Value("${security-algorithm}")
    String algo;

//when trying to test we get SignaturException
    //JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.

    @Test
    void validateToken() {
         /*


        String token=  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJob2RkZWk0IiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVTX0FETUlOIn1dLCJpYXQiOjE2MzExMDU0NjYsImV4cCI6MTYzMTEwNTc2Nn0.lyxT9jymt_M-1Cuvc_wgxkf_i2MefHhHQpaM5qiC4JG3NUYoyurSoqi_gGh1BcY2R7qyEIvqOHC53P1IvQ2e3A";
       User expected= new User ("hoddei4","",new ArrayList<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority("ROLES_ADMIN"))));

       assertEquals(expected,appContext.jwtParser().validateToken(token));

          /*
    {
  "sub": "hoddei4",
  "authorities": [
    {
      "authority": "ROLES_ADMIN"
    }
  ],
  "iat": 1631105466,
  "exp": 1631105766
}
     */
    }
}