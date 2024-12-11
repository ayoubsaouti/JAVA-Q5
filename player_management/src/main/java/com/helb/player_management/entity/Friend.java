package com.helb.player_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @JsonIgnore // Prévenir la sérialisation pour éviter les boucles infinies (lorsque le joueur est sérialisé, il ne sérialisera pas le champ 'friend')
    private Player player;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Player friend;

    // Constructeur à deux joueurs (joueur et ami)
    public Friend(Player player, Player friend) {
        this.player = player;
        this.friend = friend;
    }
}
