package com.example.offres_microservice.dto;

import com.example.offres_microservice.model.Offres;
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
