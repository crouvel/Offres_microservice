package com.example.offres_microservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offer")
public class Offer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String title;

    @Column(nullable = true)
    private String description;

    @NotBlank
    private Date debut;

    @NotBlank
    private Date fin;

    @NotBlank
    private String city;

    @NotBlank
    private int nbre_postes;


    private int income;

    @NotBlank
    private String advantage;

    private Long employeur_id;

    public Long getEmployeur_id() {
        return employeur_id;
    }

    public void setEmployeur_id(Long employeur_id) {
        this.employeur_id = employeur_id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employeur employeur;

    public Offer(String titre, String description, Date dateDebut, Date dateFin, String lieu, int nbre_postes, int remunerationBase, String avantage/*Long employeur_id*/) {
        this.title = titre;
        this.description = description;
        this.debut = dateDebut;
        this.fin = dateFin;
        this.city = lieu;
        this.nbre_postes = nbre_postes;
        this.income = remunerationBase;
        this.advantage = avantage;
        //this.employeur_id = employeur_id;
    }

}

