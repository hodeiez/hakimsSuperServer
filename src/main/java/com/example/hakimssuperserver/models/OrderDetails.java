package com.example.hakimssuperserver.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ordersID;
    private Long productID;
    private double productPrice;

    @OneToOne
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordersid", insertable = false, updatable = false, referencedColumnName = "id")
    @JsonBackReference
    private Orders orders;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
}
