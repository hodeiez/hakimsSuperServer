package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Customer;
import com.example.hakimssuperserver.services.AuthenticationService;
import com.example.hakimssuperserver.services.SignUpDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Miwa Guhrés
 * Date: 14/09/2021
 * Time: 21:49
 * Project: hakimsSuperServer
 * Copyrigtht: MIT
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private  CustomerRepository customerRepository;

    /*
    * How to test with Long id
    * */
    @Test
    void findAllById() {
        Customer customer1 = new Customer(1L,"Mi","Gu","test1@gmail.com","123456789","test1vägen 1","test","12345","customer1",null,null);
        customerRepository.save(customer1);
        List<Customer> customers =customerRepository.findAllById(1L);

        assertEquals(Arrays.asList(customer1), customers);



    }

    @Test
    void findAllByEmail() {
        /*Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Mi");
        customer1.setLastname("Gu");
        customer1.setEmail("test1@gmail.com");
        customer1.setTelephone("123456789");
        customer1.setAddress("test1vägen 1");
        customer1.setCity("test");
        customer1.setZip("12345");
        customer1.setPassword("customer1");*/

        Customer customer1 = new Customer("Mi","Gu","test1@gmail.com","123456789","test1vägen 1","test","12345","customer1");
        Customer customer2 = new Customer("Ni","Hu","test2@gmail.com","123456780","test1vägen 2","test","12345","customer2");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        List<Customer> customers =customerRepository.findAllByEmail("test1@gmail.com");

        assertEquals(Arrays.asList(customer1), customers);
        assertNotEquals(Arrays.asList(customer2), customers);
    }

    /*
    * This is not used because we must not find customer by password.
    * (Some people use a same password.)
    * */

    @Test
    void findAllByPassword() {
        Customer customer1 = new Customer("Mi","Gu","test1@gmail.com","123456789","test1vägen 1","test","12345","customer1");
        Customer customer2 = new Customer("Ni","Hu","test2@gmail.com","123456780","test1vägen 2","test","12345","customer1");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        List<Customer> customers =customerRepository.findAllByPassword("customer1");

        assertEquals(Arrays.asList(customer1, customer2), customers);

    }

    @Test
    void findCustomerByEmail() {

        Customer customer1 = new Customer("Mi","Gu","test1@gmail.com","123456789","test1vägen 1","test","12345","customer1");
        customerRepository.save(customer1);
        Customer savedCustomer=customerRepository.findCustomerByEmail(customer1.getEmail());

        assertEquals(savedCustomer,customer1 );
    }

    /*
    * I need help
    * How to test??
    * */

   @Test
    void existsByEmail() {
       SignUpDTO signUpDTO = new SignUpDTO("Mi","Gu","test1@gmail.com","123456789","test1vägen 1","test","12345","customer1","123");
       //customerRepository.save(signUpDTO);
       //boolean customerNotExists=!customerRepository.existsByEmail(signUpDTO.getEmail());
       boolean customerExists=customerRepository.existsByEmail(signUpDTO.getEmail());
       assertEquals(customerExists,true );
    }


}