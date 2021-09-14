package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hodei Eceiza
 * Date: 4/16/2021
 * Time: 15:11
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@CrossOrigin
@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    @Autowired
    public OrderDetailsRepository orderDetailsRepository;
    //ONLY ADMIN
    @GetMapping("/all")
    @ResponseBody
    public Iterable<OrderDetails> getAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

   //ONLY CUSTOMER and ONLY ADMIN
    @GetMapping("/byorderid/{orderId}")
    @ResponseBody
    public Iterable<OrderDetails> getByOrderId(@PathVariable Long orderId){
        return orderDetailsRepository.findAllByOrdersID(orderId);
    }
}
