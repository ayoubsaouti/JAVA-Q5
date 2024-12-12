package com.helb.player_management.service;

import org.springframework.stereotype.Service;

import com.helb.player_management.dto.PlayerDTO; 
import com.helb.player_management.entity.Player;
import com.helb.player_management.exception.PlayerNotFoundException;
import com.helb.player_management.repository.PlayerRepository; 

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Méthode pour obtenir un joueur
    public PlayerDTO getPlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id)); // Lance l'exception si le joueur n'existe pas
        
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getUsername(),
                player.getEmail(),
                player.getLevel(),
                player.getTotalPoints()
        );
    }

    // Méthode pour ajouter un joueur
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        Player player = new Player(
                null,
                playerDTO.getName(),
                playerDTO.getUsername(),
                playerDTO.getEmail(),
                playerDTO.getLevel(),
                playerDTO.getScore()
        );
        player = playerRepository.save(player);
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getUsername(),
                player.getEmail(),
                player.getLevel(),
                player.getTotalPoints()
        );
    }

    // Méthode pour modifier un joueur
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id)); // Lance l'exception si le joueur n'existe pas
        
        player.setName(playerDTO.getName());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        player.setLevel(playerDTO.getLevel());
        player.setTotalPoints(playerDTO.getScore());
        
        player = playerRepository.save(player);
        
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getUsername(),
                player.getEmail(),
                player.getLevel(),
                player.getTotalPoints()
        );
    }

    // Méthode pour supprimer un joueur
    public boolean deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id)); // Lance l'exception si le joueur n'existe pas
        
        playerRepository.delete(player);
        return true;
    }

     // Méthode pour mettre à jour les points et le niveau du joueur après chaque victoire
    public void updatePlayerStats(Long playerId, PlayerDTO playerDTO) {
        // Récupérer le joueur depuis la base de données
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        int newScore = player.getTotalPoints() + 50;  // Ajouter 50 points pour chaque victoire
        player.setTotalPoints(newScore);  // Mettre à jour le score

        // Incrémenter le niveau du joueur
        int newLevel = player.getLevel() + 1;  // Augmenter le niveau de 1
        player.setLevel(newLevel);  // Mettre à jour le niveau

        // Sauvegarder les changements dans la base de données
        playerRepository.save(player);
    }
}
