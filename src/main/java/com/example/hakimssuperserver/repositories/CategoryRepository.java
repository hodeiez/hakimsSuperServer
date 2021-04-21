package com.example.hakimssuperserver.repositories;

import com.example.hakimssuperserver.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:50
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryById(Long id);
}
