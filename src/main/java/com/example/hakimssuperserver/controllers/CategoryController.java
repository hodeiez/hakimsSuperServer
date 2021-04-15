package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.Category;

import com.example.hakimssuperserver.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 23:16
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("")
    public Iterable<Category> showAll(){return categoryRepository.findAll();}

    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Category addProduct(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
