package com.example.hakimssuperserver.services;

import com.example.hakimssuperserver.domain.EmailReq;
import com.example.hakimssuperserver.domain.EmailServiceAdapter;
import com.example.hakimssuperserver.domain.EmailType;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.models.OrderDetails;
import com.example.hakimssuperserver.models.Orders;
import com.example.hakimssuperserver.models.Product;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import com.example.hakimssuperserver.repositories.OrderDetailsRepository;
import com.example.hakimssuperserver.repositories.OrdersRepository;
import com.example.hakimssuperserver.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final ProductRepository productRepository;

    private final EmailServiceAdapter emailServiceAdapter;

    private final CustomerRepository customerRepository;

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
}
    public ResponseEntity<?> sendOrderConfirmedEmail(Long customerId){
        try{
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->new UsernameNotFoundException("not found username"));
            emailServiceAdapter.sendEmailReq(new EmailReq(customer.getEmail()," "," ",customer.getFirstname(), EmailType.CONFIRM.toString()));
            return ResponseEntity.ok().body("Order confirmation sent");
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
