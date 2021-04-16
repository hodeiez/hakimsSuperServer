package com.example.hakimssuperserver.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:25
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1200)
    private String description;
    private String image;
    private double price;
/*
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="categoryId", referencedColumnName="id")
    @JsonBackReference

 */
    @OneToOne
    @JoinColumn(name="categoryId")
    private Category category;

    public Product(Long id, String title, String description, String image, double price, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public Product() {

    }
}
