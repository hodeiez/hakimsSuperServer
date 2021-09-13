package com.example.hakimssuperserver.controllers;
import com.example.hakimssuperserver.domain.EmailReq;
import com.example.hakimssuperserver.domain.EmailServiceAdapter;
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
    @Autowired
    private EmailServiceAdapter emailServiceAdapter;

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
    //this request will be use for admin to add a customer
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer){
        //TODO: decide if we want to  send the email to the user.
        emailServiceAdapter.sendEmailReq(new EmailReq(customer.getEmail(),"ss@ss"," ", customer.getFirstname()));
        //twillio-> skicka email. om det ar ok-> spara-...
        return customerRepository.save(customer);
    }

    @PostMapping(value="/tryadd", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer newCustomer(@RequestBody Customer customer){
        if(availableEmail(customer.getEmail())){
            emailServiceAdapter.sendEmailReq(new EmailReq(customer.getEmail(),"ss@ss"," ", customer.getFirstname()));
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

    @RequestMapping("/checkcustomer/{email}/{password}")
    public Customer availableCustomer(@PathVariable String email, @PathVariable String password){
        Iterable<Customer> name = getCustomerByEmail(email);
        for (Customer c : name){
            if(c.getEmail().equals(email) && c.getPassword().equals(password)){
                return c;
            }
        }
        return null;
    }
    @PostMapping(value="/update", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer){
        Customer savedCustomer=customerRepository.findCustomerByEmail(customer.getEmail());
        customer.setId(savedCustomer.getId());
        customer.setCreateDate(savedCustomer.getCreateDate());
        customer.setPassword(savedCustomer.getPassword());
        return customerRepository.save(customer);
    }
}
