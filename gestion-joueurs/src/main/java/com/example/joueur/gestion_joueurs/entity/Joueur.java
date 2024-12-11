package com.example.joueur.gestion_joueurs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true, nullable = false)
    private String pseudonyme;

    @Column(unique = true, nullable = false)
    private String email;

    private int niveau;

    private int pointsTotaux;

    @OneToMany(mappedBy = "joueur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ami> amis = new ArrayList<>();
}
