package com.example.offres_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffresEvent {
    private String message;
    private String status;
    private Offres offres;
}
