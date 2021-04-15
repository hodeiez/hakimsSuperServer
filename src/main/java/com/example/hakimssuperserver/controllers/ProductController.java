package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.Product;
import com.example.hakimssuperserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:55
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("")
    public Iterable<Product> showAll(){return productRepository.findAll();}

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Product> getByCategoryId(@RequestParam Long id){
        return productRepository.findAllByCategoryId(id);
    }
//adds a product with a post request and response with the object created
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

}
