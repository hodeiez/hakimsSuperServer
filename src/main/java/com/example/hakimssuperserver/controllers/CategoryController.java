package com.example.hakimssuperserver.controllers;

import com.example.hakimssuperserver.models.Category;

import com.example.hakimssuperserver.models.Product;
import com.example.hakimssuperserver.repositories.CategoryRepository;

import com.example.hakimssuperserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    //OPEN
    @RequestMapping("")
    public Iterable<Category> showAll(){return categoryRepository.findAll();}
//OPEN
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();}

    //ONLY Admin
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    @ResponseBody
    public Category addProduct(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    //ONLY Admin
    @RequestMapping(path="/{categoryID}/delete")
    public String deleteCategory(@PathVariable("categoryID")Long categoryID){
        List<Product> productsToUpdate = productRepository.findAllByCategoryId(categoryID);
        productsToUpdate.forEach(product -> product.setCategory(null));
        Category categoryToDelete = categoryRepository.findCategoryById(categoryID);
        categoryRepository.delete(categoryToDelete);
        return "category deleted";
    }

}
