package com.example.hakimssuperserver.controllers;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lisa Ramel
 * Date: 2021-04-16
 * Time: 15:08
 * Project: hakimsSuperServer
 * Copywrite: MIT
 */
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("")
    public Iterable<Customer> showAllCustomers(){
        return customerRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/id")
    public Iterable<Customer> getCustomerById(@RequestParam Long id){
        return customerRepository.findAllById(id);
    }

    @ResponseBody
    @GetMapping("/email")
    public Iterable<Customer> getCustomerByEmail(@RequestParam String email){
        return customerRepository.findAllByEmail(email);
    }

    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PostMapping(value="/tryadd", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer newCustomer(@RequestBody Customer customer){
        if(availableEmail(customer.getEmail())){
            customerRepository.save(customer);
            return customer;
        } else {
            return new Customer();
        }

    }

    public boolean availableEmail(String email){
        Iterable<Customer> names = getCustomerByEmail(email);
        if(names.spliterator().getExactSizeIfKnown() > 0 ){
            return false;
        } else {
            return true;
        }
    }


    @RequestMapping("/checkemail/{email}")
    public boolean availableEmailCheck(@PathVariable String email){
        Iterable<Customer> names = getCustomerByEmail(email);
        if(names.spliterator().getExactSizeIfKnown() > 0 ){
            return false;
        } else {
            return true;
        }
    }



}
