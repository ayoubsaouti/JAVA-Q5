package com.helb.game_management.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsUpdateDTO {
    private int score;
    private boolean victory;


}
