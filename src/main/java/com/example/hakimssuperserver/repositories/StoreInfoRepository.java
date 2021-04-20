package com.example.hakimssuperserver.repositories;
import com.example.hakimssuperserver.models.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hodei Eceiza
 * Date: 4/15/2021
 * Time: 22:54
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
public interface StoreInfoRepository extends JpaRepository<StoreInfo,Long> {
}
