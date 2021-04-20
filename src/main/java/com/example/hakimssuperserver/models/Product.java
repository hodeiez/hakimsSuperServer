package com.example.hakimssuperserver.models;

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
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String image;
    private double price;

    @ManyToOne() //took out cascade to be able to add or update easier...
    @JoinColumn(name="category_id", referencedColumnName="id")
  //  @JsonBackReference
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
