package com.example.hakimssuperserver.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public class JWTparser {
    private Key key;


    public JWTparser(Key key) {
        key = key;

    }

    public User validateToken(String token){
        Claims claims;
                return new User("test","test",new ArrayList<>());
    }

}
