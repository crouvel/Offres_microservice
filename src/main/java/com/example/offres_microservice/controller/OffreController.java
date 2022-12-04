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
@RequestMapping("/api/v1/offers")
public class OffreController {

    private OffresProducer offresProducer;
    @Autowired OffreRepository offreRepository;

    // @Autowired
    // private EmployeurRepository employeurRepository;

    public OffreController(OffresProducer orderProducer) {
        this.offresProducer = orderProducer;
    }

    @GetMapping("/all")
    public Flux<Offer> getOffre() {

        return offreRepository.findAll();

    }

    public String notifyOffer(@RequestBody Offer offre) throws InterruptedException {

        offresProducer.sendMessage(offre.getCity() + " : " + offre.getTitle().toUpperCase());

        return "Offer placed successfully ...";
    }

    @GetMapping("/{title}")
    Mono<ResponseEntity<Offer>> getOffer(@PathVariable("title") String title) {
        return offreRepository.findByTitle(title).map(offer -> {
            return new ResponseEntity<>(offer, HttpStatus.OK);
        });
    }

    @PostMapping("/offers")
    Mono<ResponseEntity<Offer>> addOffer(@RequestBody Offer offres) {

        return offreRepository.save(offres).map(offre -> {
            try {
                notifyOffer(offre);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(offre, HttpStatus.CREATED);
        });
    }

}
