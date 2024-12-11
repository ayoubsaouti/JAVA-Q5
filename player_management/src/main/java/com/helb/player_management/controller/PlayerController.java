package com.helb.player_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.helb.player_management.dto.PlayerDTO; 
import com.helb.player_management.exception.PlayerNotFoundException; 
import com.helb.player_management.service.PlayerService; 

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Endpoint pour obtenir un joueur
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long id) {
        try {
            PlayerDTO player = playerService.getPlayer(id);
            return ResponseEntity.ok(player);
        } catch (PlayerNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // Retourne 404 si joueur non trouvé
        }
    }

    // Endpoint pour ajouter un joueur
    @PostMapping
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO createdPlayer = playerService.addPlayer(playerDTO);
        return ResponseEntity.ok(createdPlayer);
    }

    // Endpoint pour modifier un joueur
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        try {
            PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDTO);
            return ResponseEntity.ok(updatedPlayer);
        } catch (PlayerNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // Retourne 404 si joueur non trouvé
        }
    }

    // Endpoint pour supprimer un joueur
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        try {
            boolean result = playerService.deletePlayer(id);
            if (result) {
                return ResponseEntity.ok("Player deleted successfully");
            }
            return ResponseEntity.status(404).body("Player not found");
        } catch (PlayerNotFoundException ex) {
            return ResponseEntity.status(404).body("Player not found");
        }
    }
}
