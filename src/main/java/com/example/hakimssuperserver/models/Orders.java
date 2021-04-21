package com.example.hakimssuperserver.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    //private Long customerID;
    @ManyToOne()//removed cascade=CascadeType.ALL so we can save a product without given id
    @JoinColumn(name="customer_id", referencedColumnName="id")
    //  @JsonBackReference
    private Customer customer;
    @OneToMany(mappedBy="orders")
    @JsonManagedReference
    private List<OrderDetails> orderDetails;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
}
