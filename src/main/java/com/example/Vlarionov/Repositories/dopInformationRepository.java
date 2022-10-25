package com.example.Vlarionov.Repositories;

import com.example.Vlarionov.Models.dopInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface dopInformationRepository extends CrudRepository<dopInformation, Long> {
     dopInformation findBydesign (String dopInformation);;
}
