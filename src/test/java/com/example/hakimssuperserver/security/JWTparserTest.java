package com.example.hakimssuperserver.security;

import com.example.hakimssuperserver.ApplicationConf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:55
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = ApplicationConf.class)
@TestPropertySource(locations = "classpath:application-test.yml")
//@EnableConfigurationProperties(value = ApplicationConf.class)

@SpringBootTest
class JWTparserTest {

    @Autowired
    ApplicationConf appContext;


    @Test
    void validateToken() {
        String token=  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJob2RkZWk0IiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVTX0FETUlOIn1dLCJpYXQiOjE2MzExMDU0NjYsImV4cCI6MTYzMTEwNTc2Nn0.lyxT9jymt_M-1Cuvc_wgxkf_i2MefHhHQpaM5qiC4JG3NUYoyurSoqi_gGh1BcY2R7qyEIvqOHC53P1IvQ2e3A";

        assertEquals("fda",appContext.getJwtparser().validateToken(token));

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