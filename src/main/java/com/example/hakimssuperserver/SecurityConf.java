package com.example.hakimssuperserver;

import com.example.hakimssuperserver.security.JWTAuthorizationFilter;
import com.example.hakimssuperserver.security.JWTparser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
    private final JWTparser jwtParser;

    public SecurityConf(JWTparser jwtParser) {
        this.jwtParser = jwtParser;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager(), jwtParser);
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup","/login","/category","/category/all","/product","/product/all","/product/getbyid/*").permitAll() //public endpoints
                .antMatchers("/category/add","/category/*","/orderdetails/all","/product/add","/orders/all").hasRole("ADMIN") //endpoints for admin
                .antMatchers("/customer/getmydetails","/customer/update","/orders/add/*","/orders/getbycustomer").hasRole("CUSTOMER") //endpoints for customer
                .antMatchers("/orderdetails/byorderid/*").hasAnyRole("ADMIN","CUSTOMER") //endpoints for admin and for customer
                .antMatchers("/customer","/customer/id","/customer/email","/customer/add","/customer/tryadd","customer/checkemail","/customer/checkcustomer").denyAll() //unusable endpoints
                .and()
                .addFilter(jwtAuthorizationFilter).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
