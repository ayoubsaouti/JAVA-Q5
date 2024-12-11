package com.example.joueur.gestion_joueurs.controller;

import com.example.joueur.gestion_joueurs.dto.JoueurDTO;
import com.example.joueur.gestion_joueurs.service.JoueurService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueurs")
public class JoueurController {

    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @PostMapping
    public ResponseEntity<JoueurDTO> ajouterJoueur(@RequestBody JoueurDTO joueurDTO) {
        return ResponseEntity.ok(joueurService.ajouterJoueur(joueurDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoueurDTO> obtenirJoueur(@PathVariable Long id) {
        return ResponseEntity.ok(joueurService.obtenirJoueur(id));
    }

    // Mettre à jour un joueur
    @PutMapping("/{id}")
    public ResponseEntity<JoueurDTO> mettreAJourJoueur(@PathVariable Long id, @RequestBody JoueurDTO joueurDTO) {
        return ResponseEntity.ok(joueurService.mettreAJourJoueur(id, joueurDTO));
    }
}
