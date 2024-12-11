package com.example.joueur.gestion_joueurs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Génère les getters, setters, equals, hashCode et toString
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs
public class Ami {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_joueur", nullable = false)
    @JsonIgnore // Empêche la sérialisation pour éviter les boucles infinies
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "id_ami", nullable = false)
    private Joueur ami; // L'objet Joueur correspondant à l'ami
}
