package com.example.hakimssuperserver.controllers;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /*@GetMapping
    public Iterable<Customer> getCustomerById(@RequestParam Long id){
        return customerRepository.findById(id);
    }*/

    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
}
