package com.example.hakimssuperserver.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public class JWTAutorizationFilter extends BasicAuthenticationFilter {

    private final JWTparser jwtparser;

    public JWTAutorizationFilter(AuthenticationManager authenticationManager, JWTparser jwtparser) {
        super(authenticationManager);
        this.jwtparser = jwtparser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
    }
}
