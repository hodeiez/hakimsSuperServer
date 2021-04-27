package com.example.hakimssuperserver.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String telephone;
    private String address;
    private String city;
    private String zip;
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Customer(Long id, String firstname, String lastname, String email, String telephone, String address, String city, String zip, String password, Date createDate, Date modifyDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.password = password;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
