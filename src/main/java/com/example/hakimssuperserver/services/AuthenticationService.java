package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.domain.*;
import com.example.hakimssuperserver.models.Admin;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.AdminRepository;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import com.example.hakimssuperserver.security.JWTparser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    private final JWTparser jwtParser;


    public ResponseEntity<String> signUp(SignUpDTO signUpDTO) {


        if (emailNotInDB(signUpDTO)) {

            return sendSignUp(signUpDTO);
        }
        return new ResponseEntity<>("user is already signed up", HttpStatus.ALREADY_REPORTED);
    }

    /**
     * check if user exist as customer or admin
     * @param signUpDTO
     * @return
     */
    private boolean emailNotInDB(SignUpDTO signUpDTO) {
        boolean customerExists=customerRepository.existsByEmail(signUpDTO.getEmail());
        boolean adminExists=adminRepository.existsByEmail(signUpDTO.getEmail());
        if(customerExists)
            return false;
        else if(adminExists)
            return false;
        else return !customerExists || !adminExists;
    }

    /**
     * if signupDTO has the secret token we signup as admin, if not we sign up as customer
     * @param signUpDTO
     * @return
     */
    private ResponseEntity<String> sendSignUp(SignUpDTO signUpDTO) {
        SignUpReq signUpReq;
        if (signUpDTO.getSecretToken() != null ) {
                    if(signUpDTO.getSecretToken().equals(SECRET_TOKEN)) {
                        Admin admin = new Admin(signUpDTO.getFirstname(), signUpDTO.getLastname(), signUpDTO.getEmail(), passwordEncoder.encode(signUpDTO.getPassword()));
                        adminRepository.save(admin);
                        signUpReq = new SignUpReq(signUpDTO.getEmail(), signUpDTO.getPassword(), "ROLE_ADMIN");
                    }
                    else
                        return ResponseEntity.badRequest().body("Wrong token, couldn't sign up as Admin");

        } else {
            Customer customer = new Customer(signUpDTO.getFirstname(), signUpDTO.getLastname(), signUpDTO.getEmail(), signUpDTO.getTelephone(), signUpDTO.getAddress(), signUpDTO.getCity(), signUpDTO.getZip(), passwordEncoder.encode(signUpDTO.getPassword()));
            customerRepository.save(customer);
            signUpReq = new SignUpReq(signUpDTO.getEmail(), signUpDTO.getPassword(), "ROLE_CUSTOMER");

            emailServiceAdapter.sendEmailReq(new EmailReq(signUpDTO.getEmail(), " ", " ", signUpDTO.getFirstname(),EmailType.WELCOME.toString()));
        }

        return securityServiceAdapter.sendSignUpReq(signUpReq);

    }

    /**
     * Login redirects to security service
     * @param loginReq
     * @return
     */
    public ResponseEntity<?> login(LoginReq loginReq) {
        return securityServiceAdapter.sendLoginReq(loginReq);
    }

    /**
     * get details by sending the token in Authorization header
     * @param headers
     * @return
     */
    public ResponseEntity<?> getMyDetails(HttpHeaders headers) {
        try {
            String token = Objects.requireNonNull(headers.get("Authorization")).get(0).substring(7);
            String email =jwtParser.validateToken(token).getUsername();
            Customer customer =customerRepository.findCustomerByEmail(email);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            return new ResponseEntity<>("Couldn't get data", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
