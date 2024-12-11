package com.helb.player_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.helb.player_management.entity.Friend; 
import com.helb.player_management.entity.Player; 

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // Trouver tous les amis d'un joueur
    List<Friend> findByPlayerId(Long playerId);

    // Vérifier si une relation d'amitié existe déjà entre deux joueurs
    boolean existsByPlayerAndFriend(Player player, Player friend);

    // Supprimer une relation d'amitié spécifique
    void deleteByPlayerIdAndFriendId(Long playerId, Long friendId);
}
