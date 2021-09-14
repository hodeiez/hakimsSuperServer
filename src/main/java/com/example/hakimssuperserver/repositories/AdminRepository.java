package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:50
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByEmail(String email);
}
