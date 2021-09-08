package com.example.hakimssuperserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.*;


import static java.util.stream.Collectors.toList;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */

/**
 * "translates" a jwttoken to user, using the Secretkey as validation
 */
public class JWTparser {

    private Key key;


    public JWTparser( Key key) {
        this.key = key;


    }

    public User validateToken(String token)  {
        Claims claims= Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        //the user name
        String name=(String) claims.get("sub");

        //authorities comes in this format = [{authority=ROLE_ADMIN}], and we need to convert to a List<GrantedAuthority>
        ArrayList<LinkedHashMap> authorities = (ArrayList<LinkedHashMap>) claims.get("authorities");


        List<GrantedAuthority> grantedAuthorities=authorities.stream()
                .map(s->s.get("authority").toString())
                .collect(toList())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());





                //we don't care about the password
                return new User(name,"",grantedAuthorities);
    }

}
