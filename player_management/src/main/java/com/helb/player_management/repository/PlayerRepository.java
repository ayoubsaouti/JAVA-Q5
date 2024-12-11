package com.helb.player_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.helb.player_management.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Le repository pour l'entité Player
    // JpaRepository fournit des méthodes CRUD par défaut pour l'entité Player
}
