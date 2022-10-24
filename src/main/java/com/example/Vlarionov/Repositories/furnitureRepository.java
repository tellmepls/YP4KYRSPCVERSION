package com.example.Vlarionov.Repositories;

import com.example.Vlarionov.Models.furniture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface furnitureRepository  extends CrudRepository<furniture, Long> {
    public List<furniture> findByType(String type);
    public List<furniture> findByTypeContains(String type);
    public furniture findByid(String id);
    public furniture findByusername(String username);
}
