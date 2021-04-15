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
 * Time: 22:27
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Getter
@Setter
@Entity
public class StoreInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String openingTimes;
    private String telephone;
    private String address;
    private String city;
    private String zip;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
}
