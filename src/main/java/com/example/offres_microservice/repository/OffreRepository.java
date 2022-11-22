package com.example.offres_microservice.repository;

import com.example.offres_microservice.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByEmployeurId(Long employeurId);
    //Boolean existsByUsername(String username);

    //Boolean existsByEmail(String email);
}