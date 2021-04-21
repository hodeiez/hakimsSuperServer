package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.models.Orders;
import com.example.hakimssuperserver.repositories.OrdersRepository;
import com.example.hakimssuperserver.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 4/16/2021
 * Time: 13:09
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    public OrdersRepository ordersRepository;
    @Autowired
    public OrdersService ordersService;
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Orders> getAll(){
        return ordersRepository.findAll();
    }

    /*
    1-send a customer id an create an order
     */
    @PostMapping(value="/add/{customerId}", consumes="application/json",produces="application/json")
    @ResponseBody
    public Orders addOrder(@RequestBody List<OrderDetails> orderDetails, @PathVariable Long customerId){
    return ordersService.addOrder(orderDetails,customerId);

    }
    @PostMapping(value="/getbycustomer", consumes="application/json",produces="application/json")
    @ResponseBody
    public List<Orders>getByCustomer(@RequestBody Customer customer){
        return ordersRepository.findAllByCustomer(customer);

    }

}
