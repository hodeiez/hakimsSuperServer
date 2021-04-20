package com.example.hakimssuperserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:26
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
/*
    @OneToMany(mappedBy="category")
    @JsonManagedReference
    private List<Product> product;


 */

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }
}
