package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.models.Orders;
import com.example.hakimssuperserver.models.Product;
import com.example.hakimssuperserver.repositories.OrderDetailsRepository;
import com.example.hakimssuperserver.repositories.OrdersRepository;
import com.example.hakimssuperserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public ProductRepository productRepository;

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
        Orders order=new Orders();
        order.setCustomerID(customerId);
        Date today=new Date();
        order.setOrderDate(today);
        Orders savedOrder=ordersRepository.save(order);

        for(OrderDetails details : orderDetails ){
            Long productID=details.getProductID();
            Optional<Product> product=productRepository.findById(productID);
            details.setProduct(product.get());
            details.setOrdersID(savedOrder.getId());
            orderDetailsRepository.save(details);
        }
        return savedOrder;
    }

}
