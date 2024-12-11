package com.helb.game_management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO {
    private Long gameId;
    private Long playerId;
    private int score;
    private Boolean victory;
}
