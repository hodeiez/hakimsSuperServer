package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:52
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer>findAllById(Long id);
    List<Customer>findAllByEmail(String email);
    List<Customer>findAllByPassword(String email);
}
