package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.models.Orders;
import com.example.hakimssuperserver.models.Product;
import com.example.hakimssuperserver.repositories.OrderDetailsRepository;
import com.example.hakimssuperserver.repositories.OrdersRepository;
import com.example.hakimssuperserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Hodei Eceiza
 * Date: 4/18/2021
 * Time: 21:00
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Service
public class OrdersService {
    @Autowired
    public OrdersRepository ordersRepository;
    @Autowired
    public OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public ProductRepository productRepository;


    public Orders addOrder( List<OrderDetails> orderDetails,  Long customerId){
        Orders order=new Orders();
        //order.setCustomerID(customerId); //we will swap this for

        Customer c=new Customer();
        c.setId(customerId);
        order.setCustomer(c);

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
}}
