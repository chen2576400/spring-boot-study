package com.chengning.repository;


import com.chengning.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    Commodity findByCommodityName(String name);
}
