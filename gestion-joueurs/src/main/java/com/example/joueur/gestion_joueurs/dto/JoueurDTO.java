package com.example.joueur.gestion_joueurs.dto;

public class JoueurDTO {
    private Long id;
    private String nom;
    private String pseudonyme;
    private String email;
    private int niveau;
    private int pointsTotaux;

    // Constructeurs, Getters et Setters
    public JoueurDTO(Long id, String nom, String pseudonyme, String email, int niveau, int pointsTotaux) {
        this.id = id;
        this.nom = nom;
        this.pseudonyme = pseudonyme;
        this.email = email;
        this.niveau = niveau;
        this.pointsTotaux = pointsTotaux;
    }

    // Getters et Setters
}
