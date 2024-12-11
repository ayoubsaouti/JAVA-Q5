package com.helb.game_management.service;

import com.helb.game_management.dto.ParticipationDTO;
import com.helb.game_management.entity.Participation;
import com.helb.game_management.repository.ParticipationRepository;
import com.helb.game_management.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final GameRepository gameRepository;

    // Enregistrer la participation d'un joueur
    public Participation registerParticipation(ParticipationDTO participationDTO) {
        // Vérification de l'existence de la partie
        gameRepository.findById(participationDTO.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Créer et sauvegarder la participation
        Participation participation = new Participation(
            null, // L'ID sera généré automatiquement
            participationDTO.getGameId(),
            participationDTO.getPlayerId(),
            participationDTO.getScore(),
            participationDTO.getVictory()
        );

        return participationRepository.save(participation);
    }
}
