package com.example.joueur.gestion_joueurs.controller;

import com.example.joueur.gestion_joueurs.dto.AmiRequest;
import com.example.joueur.gestion_joueurs.service.JoueurService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueurs/{id}/amis")
public class AmiController {

    private final JoueurService joueurService;

    public AmiController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @PostMapping
    public ResponseEntity<String> ajouterAmi(@PathVariable Long id, @RequestBody AmiRequest amiRequest) {
        joueurService.ajouterAmi(id, amiRequest.getAmiId());
        return ResponseEntity.ok("Ami ajouté avec succès.");
    }
}
