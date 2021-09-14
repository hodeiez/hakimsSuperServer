package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.domain.*;
import com.example.hakimssuperserver.models.Admin;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.AdminRepository;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Hodei Eceiza
 * Date: 9/13/2021
 * Time: 21:06
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@AllArgsConstructor
@Service
public class AuthenticationService {


    private final String SECRET_TOKEN;

    private final CustomerRepository customerRepository;

    private final SecurityServiceAdapter securityServiceAdapter;

    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    private final EmailServiceAdapter emailServiceAdapter;


    //TODO:redefine this logic!!
    public ResponseEntity<String> signUp(SignUpDTO signUpDTO) {
        SignUpReq signUpReq;
        if (!customerRepository.existsByEmail(signUpDTO.getEmail()) || !adminRepository.existsByEmail(signUpDTO.getEmail())) {

            if (signUpDTO.getSecretToken() != null && signUpDTO.getSecretToken().equals(SECRET_TOKEN)) {

                Admin admin = new Admin(signUpDTO.getFirstname(), signUpDTO.getLastname(), signUpDTO.getEmail(), passwordEncoder.encode(signUpDTO.getPassword()));
                adminRepository.save(admin);
                signUpReq = new SignUpReq(signUpDTO.getEmail(), signUpDTO.getPassword(), "ROLE_ADMIN");

            } else {
                Customer customer = new Customer(signUpDTO.getFirstname(), signUpDTO.getLastname(), signUpDTO.getEmail(), signUpDTO.getTelephone(), signUpDTO.getAddress(), signUpDTO.getCity(), signUpDTO.getZip(), passwordEncoder.encode(signUpDTO.getPassword()));
                customerRepository.save(customer);
                signUpReq = new SignUpReq(signUpDTO.getEmail(), signUpDTO.getPassword(), "ROLE_CUSTOMER");

                emailServiceAdapter.sendEmailReq(new EmailReq(signUpDTO.getEmail(), " "," ", signUpDTO.getFirstname()));
            }
            return securityServiceAdapter.sendSignUpReq(signUpReq);
        }
        return new ResponseEntity<>("user is already signed up", HttpStatus.ALREADY_REPORTED);
    }
    public ResponseEntity<String> login(LoginReq loginReq){
        return securityServiceAdapter.sendLoginReq(loginReq);
    }
}
