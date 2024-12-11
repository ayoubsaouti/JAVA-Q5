package com.helb.game_management.controller;

import com.helb.game_management.dto.GameDTO;
import com.helb.game_management.entity.Game;
import com.helb.game_management.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // Créer une nouvelle partie
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameDTO gameDTO) {
        Game game = gameService.createGame(gameDTO);
        return ResponseEntity.ok(game); // Retourner la partie créée
    }

    // Récupérer les informations d'une partie spécifique
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game game = gameService.getGame(id);
        return ResponseEntity.ok(game); // Retourner la partie demandée
    }

    // Modifier une partie existante
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        Game game = gameService.updateGame(id, gameDTO);
        return ResponseEntity.ok(game); // Retourner la partie mise à jour
    }

    // Supprimer une partie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build(); // Retourner 204 No Content pour la suppression réussie
    }
}
