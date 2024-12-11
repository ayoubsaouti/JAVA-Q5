package com.helb.game_management.service;

import com.helb.game_management.dto.GameDTO;
import com.helb.game_management.entity.Game;
import com.helb.game_management.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    // Créer une nouvelle partie
    public Game createGame(GameDTO gameDTO) {
        Game game = new Game(null,gameDTO.getDate(), gameDTO.getGameType(), gameDTO.getMaxScore(), gameDTO.getHostId());
        return gameRepository.save(game);
    }

    // Récupérer une partie par son ID
    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    // Modifier une partie existante
    public Game updateGame(Long id, GameDTO gameDTO) {
        Game game = getGame(id); // Récupère la partie existante
        game.setDate(gameDTO.getDate());
        game.setGameType(gameDTO.getGameType());
        game.setMaxScore(gameDTO.getMaxScore());
        game.setHostId(gameDTO.getHostId());
        return gameRepository.save(game);
    }

    // Supprimer une partie
    public void deleteGame(Long id) {
        Game game = getGame(id); // Vérifie si la partie existe
        gameRepository.delete(game); // Supprime la partie
    }
}
