package com.example.offres_microservice.model;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offres")
public class Offres {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    //@NotBlank
    private String titre;

    @Column(nullable = true)
    private String description;

    //@NotBlank
    private Date dateDebut;

    //@NotBlank
    private Date dateFin;

    //@NotBlank
    private String lieu;

    //@NotBlank
    private int nbre_postes;

    //@NotBlank
    private int remunerationBase;

    //@NotBlank
    private String avantage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employeur employeur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public Offre(String titre, String description, Date dateDebut, Date dateFin, String lieu, int nbre_postes, int remunerationBase, String avantage) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.nbre_postes = nbre_postes;
        this.remunerationBase = remunerationBase;
        this.avantage = avantage;
    }

    public Offre(){}*/

}
