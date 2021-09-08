package com.example.hakimssuperserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Hodei Eceiza
 * Date: 9/8/2021
 * Time: 14:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
