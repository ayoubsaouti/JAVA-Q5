package com.helb.game_management.controller;

import com.helb.game_management.dto.ParticipationDTO;
import com.helb.game_management.entity.Participation;
import com.helb.game_management.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    // Enregistrer la participation d'un joueur
    @PostMapping
    public ResponseEntity<Participation> registerParticipation(@RequestBody ParticipationDTO participationDTO) {
        Participation participation = participationService.registerParticipation(participationDTO);
        return ResponseEntity.ok(participation); // Retourne la participation enregistrée
    }
}
