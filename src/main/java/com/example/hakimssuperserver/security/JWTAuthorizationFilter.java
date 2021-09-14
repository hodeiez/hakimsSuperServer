package com.example.hakimssuperserver.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

/**
 * checks if the JWT token is valid and "saves" the authentication in securityContext
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTparser jwtparser;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTparser jwtparser) {
        super(authenticationManager);
        this.jwtparser = jwtparser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authenticationToken = null;
        //we expect to have the token in the header with Authorization key
        String header=request.getHeader("Authorization");
        //if the request doesnt have this header we go on running spring security filters
        if(header==null||!header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        else {
            String token;
            try {
                token=header.substring(7);
                //we create a user from token
                User user = jwtparser.validateToken(token);
                if(user!=null) {
                    //we create an authentication with that user
                    authenticationToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                }
                //we "save" in security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                //we run on the filters
                chain.doFilter(request, response);
            }
            catch(Exception e){
                chain.doFilter(request, response);
            }

        }
        }
}
