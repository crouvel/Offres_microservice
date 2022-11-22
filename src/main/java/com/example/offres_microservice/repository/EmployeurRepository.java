package com.example.offres_microservice.repository;
import com.example.offres_microservice.model.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeurRepository extends JpaRepository<Employeur, Long> {
    //Optional<User> findByUsername(String email);

    //Boolean existsByUsername(String username);

    //Boolean existsByEmail(String email);
}
