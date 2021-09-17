package com.example.hakimssuperserver.controllers;
import com.example.hakimssuperserver.domain.EmailReq;
import com.example.hakimssuperserver.domain.EmailServiceAdapter;
import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.repositories.CustomerRepository;
import com.example.hakimssuperserver.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lisa Ramel
 * Date: 2021-04-16
 * Time: 15:08
 * Project: hakimsSuperServer
 * Copywrite: MIT
 */
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final AuthenticationService authenticationService;
    private final CustomerRepository customerRepository;

    private final EmailServiceAdapter emailServiceAdapter;
    //NO NO
    @RequestMapping("")
    public Iterable<Customer> showAllCustomers(){
        return customerRepository.findAll();
    }

    //NO NO
    @ResponseBody
    @GetMapping("/id")
    public Iterable<Customer> getCustomerById(@RequestParam Long id){
        return customerRepository.findAllById(id);
    }

    // NO NO
    @ResponseBody
    @GetMapping("/email")
    public Iterable<Customer> getCustomerByEmail(@RequestParam String email){
        return customerRepository.findAllByEmail(email);
    }
    // NO NO
    //this request will be use for admin to add a customer
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer){

        emailServiceAdapter.sendEmailReq(new EmailReq(customer.getEmail(),"ss@ss"," ", customer.getFirstname(),"welcome"));
        //twillio-> skicka email. om det ar ok-> spara-...
        return customerRepository.save(customer);
    }
    // NO NO
    @PostMapping(value="/tryadd", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer newCustomer(@RequestBody Customer customer){
        if(availableEmail(customer.getEmail())){
            emailServiceAdapter.sendEmailReq(new EmailReq(customer.getEmail(),"ss@ss"," ", customer.getFirstname(),"welcome"));
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

    //NO NO
    @RequestMapping("/checkemail/{email}")
    public boolean availableEmailCheck(@PathVariable String email){
        Iterable<Customer> names = getCustomerByEmail(email);
        if(names.spliterator().getExactSizeIfKnown() > 0 ){
            return false;
        } else {
            return true;
        }
    }
    //NO NO
    //TODO: has to return the token as string
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
    //ONLY CUSTOMER
    @PostMapping(value="/update", consumes="application/json",produces="application/json")
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer){
        Customer savedCustomer=customerRepository.findCustomerByEmail(customer.getEmail());
        customer.setId(savedCustomer.getId());
        customer.setCreateDate(savedCustomer.getCreateDate());
        customer.setPassword(savedCustomer.getPassword());
        return customerRepository.save(customer);
    }
//ONLY CUSTOMER
    @GetMapping("/getmydetails")
    public ResponseEntity<?> getMe(@RequestHeader HttpHeaders headers){
        return authenticationService.getMyDetails(headers);
    }
}
