package com.example.offres_microservice.controller;

import com.example.offres_microservice.Producer.OffresProducer;
import com.example.offres_microservice.dto.OffresEvent;
import com.example.offres_microservice.model.Offres;
import com.example.offres_microservice.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OffreController {

    private OffresProducer offresProducer;
    @Autowired OffreRepository offreRepository;

    // @Autowired
    // private EmployeurRepository employeurRepository;

    public OffreController(OffresProducer orderProducer) {
        this.offresProducer = orderProducer;
    }

    @GetMapping("/offers")
    public Flux<Offres> getOffre() {

        return offreRepository.findAll();

    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Offres offre){

        //offre.setId(UUID.randomUUID());

        OffresEvent offresEvent = new OffresEvent();
        offresEvent.setStatus("PENDING");
        offresEvent.setMessage("order status is in pending state");
        offreRepository.save(offre);
        offresEvent.setOffres(offre);

        offresProducer.sendMessage(offresEvent);

        return "Order placed successfully ...";
    }

    /*@PostMapping("/offre")
    public Offres offreChercheur(@Valid @RequestBody Offres offre) {
        return offreRepository.save(offre);
    }*/

    /*@GetMapping("/offres")
    public List<Offre> getAllOffres() {
        return Collections.singletonList(offreRepository.findAll());
    }*/

    /*@GetMapping("/employeurs/{employeurId}/offres")
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

    /*@DeleteMapping("/offres/{id}")
    public Map<String, Boolean> deleteOffre(@PathVariable(value = "id") Long offreId)
            throws ResourceNotFoundException {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + offreId));

        offreRepository.delete(offre);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/


}
