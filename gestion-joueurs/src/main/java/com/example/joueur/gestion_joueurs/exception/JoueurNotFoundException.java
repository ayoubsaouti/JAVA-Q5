package com.example.joueur.gestion_joueurs.exception;

public class JoueurNotFoundException extends RuntimeException {
    public JoueurNotFoundException(String message) {
        super(message);
    }
}
