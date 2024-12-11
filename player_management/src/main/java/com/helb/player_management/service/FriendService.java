package com.helb.player_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helb.player_management.dto.FriendDTO; 
import com.helb.player_management.entity.Friend; 
import com.helb.player_management.entity.Player; 
import com.helb.player_management.exception.FriendNotFoundException; 
import com.helb.player_management.repository.FriendRepository; 
import com.helb.player_management.repository.PlayerRepository; 

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final PlayerRepository playerRepository;
    private final FriendRepository friendRepository;

    public FriendService(PlayerRepository playerRepository, FriendRepository friendRepository) {
        this.playerRepository = playerRepository;
        this.friendRepository = friendRepository;
    }

    // Ajouter un ami
    public FriendDTO addFriend(Long playerId, Long friendId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new FriendNotFoundException(playerId)); // le joueur existe pas 

        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new FriendNotFoundException(friendId)); // l'ami n'existe pas 

        // Empêcher de s'ajouter comme ami
        if (playerId.equals(friendId)) {
            throw new IllegalArgumentException("A player cannot add themselves as a friend.");
        }

        // Vérifier si l'ami est déjà dans la liste d'amis du joueur
        boolean friendExists = friendRepository.existsByPlayerAndFriend(player, friend);
        if (friendExists) {
            throw new IllegalArgumentException("This friend is already added.");
        }

        // Créer la relation d'amitié
        Friend friendship = new Friend(player, friend);
        friendRepository.save(friendship);

        return new FriendDTO(friend.getId(), friend.getUsername());
    }

    // Récupérer les amis d'un joueur
    public List<FriendDTO> getFriends(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new FriendNotFoundException(playerId)); // le joueur existe pas 

        // Trouver tous les amis du joueur
        List<Friend> friends = friendRepository.findByPlayerId(playerId);

        //  Convertir les amis en FriendDTO
        return friends.stream()
                .map(friend -> new FriendDTO(friend.getFriend().getId(), friend.getFriend().getUsername()))
                .collect(Collectors.toList());
    }

    // supprimer un ami
    @Transactional
    public void removeFriend(Long playerId, Long friendId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new FriendNotFoundException(playerId)); // le joueur existe pas 

        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new FriendNotFoundException(friendId)); // l'ami n'existe pas 


        //  Supprimer la relation d'amitié entre les deux joueurs
        friendRepository.deleteByPlayerIdAndFriendId(playerId, friendId);
    }
}
