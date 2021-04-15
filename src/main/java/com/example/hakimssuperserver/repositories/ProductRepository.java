package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Category;
import com.example.hakimssuperserver.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:49
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product>findAllByCategoryId(Long categoryId);
}
