package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.models.Orders;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import com.example.hakimssuperserver.repositories.OrdersRepository;
import com.example.hakimssuperserver.services.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 4/16/2021
 * Time: 13:09
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;

    private final OrdersService ordersService;



    //ADMIN
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Orders> getAll(){
        return ordersRepository.findAll();
    }

    /*
    1-send a customer id an create an order
     */
    //ONLY CUSTOMER
    @PostMapping(value="/add/{customerId}", consumes="application/json",produces="application/json")
    @ResponseBody
    public ResponseEntity<?> addOrder(@RequestBody List<OrderDetails> orderDetails, @PathVariable Long customerId){
        try{

    ordersService.addOrder(orderDetails,customerId);
       return  ordersService.sendOrderConfirmedEmail(customerId);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
    //ONLY CUSTOMER
    @PostMapping(value="/getbycustomer", consumes="application/json",produces="application/json")
    @ResponseBody
    public List<Orders>getByCustomer(@RequestBody Customer customer){
        return ordersRepository.findAllByCustomer(customer);

    }

}
