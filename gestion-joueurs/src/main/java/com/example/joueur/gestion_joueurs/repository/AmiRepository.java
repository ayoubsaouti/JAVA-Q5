package com.example.joueur.gestion_joueurs.repository;

import com.example.joueur.gestion_joueurs.entity.Ami;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmiRepository extends JpaRepository<Ami, Long> {
    // Récupérer tous les amis d'un joueur
    List<Ami> findByJoueurId(Long joueurId);

    // Supprimer toutes les relations d'amis pour un joueur
    void deleteByJoueurId(Long joueurId);

    // Supprimer une relation d'ami spécifique
    void deleteByJoueurIdAndAmiId(Long joueurId, Long amiId);
}
