package com.helb.player_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Génère les getters, setters, equals, hashCode et toString
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nom du joueur
    private String username; // Pseudonyme du joueur
    private String email; // Email du joueur
    private int level; // Niveau du joueur
    private int totalPoints; // Points totaux du joueur
}