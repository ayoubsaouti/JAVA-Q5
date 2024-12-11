package com.helb.game_management.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cela permet à l'ID d'être généré automatiquement
    private Long id;
    
    private LocalDate date; 
    private String gameType;
    private int maxScore; 

    private Long hostId; // Identifiant de l'hôte de la partie
}
