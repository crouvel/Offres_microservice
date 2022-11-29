package com.example.offres_microservice.controller;

import com.example.offres_microservice.Producer.OffresProducer;
import com.example.offres_microservice.model.Offer;
import com.example.offres_microservice.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Offer> getOffre() {

        return offreRepository.findAll();

    }


    public String placeOrder(@RequestBody Offer offre) throws InterruptedException {

        //offre.setId(UUID.randomUUID());


        //offresEvent.setStatus("PENDING");
        //offresEvent.setMessage("order status is in pending state");
        //offreRepository.save(offre);
        //offresEvent.setOffre(offre);

        offresProducer.sendMessage(offre.getCity());

        return "Order placed successfully ...";
    }

    @PostMapping("/offers")
    Mono<ResponseEntity<Offer>> addStudent(@RequestBody Offer offres) {
        //offres.setRegisteredOn(System.currentTimeMillis());
        //offres.setStatus(1);
        return offreRepository.save(offres).map(offre -> {
            try {
                placeOrder(offre);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(offre, HttpStatus.CREATED);
        });
    }


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
