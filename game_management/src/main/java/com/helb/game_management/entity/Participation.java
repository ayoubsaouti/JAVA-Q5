package com.helb.game_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la participation

    @Column(name = "game_id", nullable = false)
    private Long gameId; // ID de la partie à laquelle le joueur participe

    @Column(name = "player_id", nullable = false)
    private Long playerId; // ID du joueur qui participe à la partie

    private int score; // Le score du joueur pour cette partie
    private Boolean victory; // Indique si le joueur a gagné ou non (true/false)

}
