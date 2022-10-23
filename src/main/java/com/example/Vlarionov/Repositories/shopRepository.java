package com.example.Vlarionov.Repositories;

import com.example.Vlarionov.Models.shop;
import org.springframework.data.repository.CrudRepository;

public interface shopRepository extends CrudRepository<shop, Long> {

    shop findByaddress(String Address);
    public shop findByid(String id);
}