package com.helb.game_management.dto;

import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private LocalDate date;
    private String gameType;
    private int maxScore;
    private Long hostId;
}
