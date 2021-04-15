package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Customer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:52
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
