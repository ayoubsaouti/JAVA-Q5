package com.example.joueur.gestion_joueurs.repository;

import com.example.joueur.gestion_joueurs.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    boolean existsByPseudonyme(String pseudonyme);
    boolean existsByEmail(String email);
}
