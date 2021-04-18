package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:53
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
