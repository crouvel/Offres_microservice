package com.example.offres_microservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class Offer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String title;

    @Column(nullable = true)
    private String description;

    private LocalDateTime debut;


    private LocalDateTime fin;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNbre_postes() {
        return nbre_postes;
    }

    public void setNbre_postes(int nbre_postes) {
        this.nbre_postes = nbre_postes;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public Employeur getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Employeur employeur) {
        this.employeur = employeur;
    }

    public Offer(Long id, String titre, String description, LocalDateTime dateDebut, LocalDateTime dateFin, String lieu, int nbre_postes, int remunerationBase, String avantage/*Long employeur_id*/) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

