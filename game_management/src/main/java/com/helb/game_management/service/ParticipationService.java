package com.helb.game_management.service;

import com.helb.game_management.dto.ParticipationDTO;
import com.helb.game_management.dto.PlayerStatsUpdateDTO;
import com.helb.game_management.entity.Participation;
import com.helb.game_management.repository.ParticipationRepository;
import com.helb.game_management.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final GameRepository gameRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${player.service.url}") // URL du Service de Gestion des Joueurs
    private String playerServiceUrl;

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

        // Enregistrer la participation dans la base de données
        Participation savedParticipation = participationRepository.save(participation);

        // Mettre à jour les statistiques du joueur après la participation
        updatePlayerStats(participationDTO.getPlayerId(), participationDTO.getScore(), participationDTO.getVictory());

        return savedParticipation;
    }

    // Mettre à jour les points et le niveau du joueur
    private void updatePlayerStats(Long playerId, int score, boolean victory) {
        WebClient webClient = webClientBuilder.baseUrl(playerServiceUrl).build();

        // Créer un DTO pour envoyer les mises à jour des stats du joueur
        PlayerStatsUpdateDTO playerStatsUpdateDTO = new PlayerStatsUpdateDTO(score, victory);

        // Effectuer un appel REST pour mettre à jour les statistiques du joueur
        webClient.put()
                .uri("/{playerId}/stats", playerId)  // Appel à l'endpoint du service des joueurs
                .body(Mono.just(playerStatsUpdateDTO), PlayerStatsUpdateDTO.class)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> System.out.println("Error updating player stats: " + e.getMessage()))
                .subscribe(); // Exécution asynchrone
    }
}
