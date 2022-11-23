package com.example.offres_microservice.repository;

import com.example.offres_microservice.model.Offres;
//import org.springframework.data.jpa.repository.JpaRepository;
import com.example.offres_microservice.model.Offres;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

public interface OffreRepository extends R2dbcRepository<Offres, Long> {
    List<Offres> findByEmployeurId(Long employeurId);
    //Boolean existsByUsername(String username);

    //Boolean existsByEmail(String email);
    Flux<Offres> findAll();
}