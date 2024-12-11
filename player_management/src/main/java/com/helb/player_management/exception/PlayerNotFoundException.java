package com.helb.player_management.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Player not found with ID: " + id); // Message d'erreur 
    }
}
