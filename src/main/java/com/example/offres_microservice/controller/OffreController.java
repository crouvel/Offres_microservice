package com.example.offres_microservice.controller;

import com.example.offres_microservice.exception.ResourceNotFoundException;
import com.example.offres_microservice.model.Offre;
import com.example.offres_microservice.repository.EmployeurRepository;
import com.example.offres_microservice.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OffreController {
    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EmployeurRepository employeurRepository;

    @GetMapping("/offres")
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    @GetMapping("/employeurs/{employeurId}/offres")
    public ResponseEntity<List<Offre>> getAllOffresByEmployeurId(@PathVariable(value = "employeurId") Long employeurId) throws ResourceNotFoundException {
        if (!employeurRepository.existsById(employeurId)) {
            throw new ResourceNotFoundException("Not found offers list with employeur id = " + employeurId);
        }

        List<Offre> offres = offreRepository.findByEmployeurId(employeurId);
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }

    @GetMapping("/offres/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable(value = "id") Long offreId)
            throws ResourceNotFoundException {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + offreId));
        return ResponseEntity.ok().body(offre);
    }

    @PostMapping("/offre")
    public Offre offreChercheur(@Valid @RequestBody Offre offre) {
        return offreRepository.save(offre);
    }

    /*@PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }*/

    @DeleteMapping("/offres/{id}")
    public Map<String, Boolean> deleteOffre(@PathVariable(value = "id") Long offreId)
            throws ResourceNotFoundException {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + offreId));

        offreRepository.delete(offre);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
