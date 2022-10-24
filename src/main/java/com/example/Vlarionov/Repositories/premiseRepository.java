package com.example.Vlarionov.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.Vlarionov.Models.Premise;

public interface premiseRepository extends CrudRepository<Premise, Long> {

    Premise findBymeaning (String meaning);
}
