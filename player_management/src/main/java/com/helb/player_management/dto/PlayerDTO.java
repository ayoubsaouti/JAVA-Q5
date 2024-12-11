package com.helb.player_management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name; // Nom du joueur
    private String username; // Pseudonyme du joueur
    private String email; // Email du joueur
    private int level; // Niveau du joueur
    private int totalPoints; // Points totaux du joueur
}
