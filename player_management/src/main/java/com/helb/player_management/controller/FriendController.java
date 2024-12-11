package com.helb.player_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.helb.player_management.dto.FriendDTO; 
import com.helb.player_management.dto.FriendRequest; 
import com.helb.player_management.service.FriendService; 

import java.util.List;

@RestController
@RequestMapping("/players/{id}/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // Ajouter un ami pour un joueur
    @PostMapping
    public ResponseEntity<FriendDTO> addFriend(@PathVariable Long id, @RequestBody FriendRequest friendRequest) {
        FriendDTO friendDTO = friendService.addFriend(id, friendRequest.getFriendId());
        return ResponseEntity.ok(friendDTO);
    }

    // Récupérer la liste des amis d'un joueur
    @GetMapping
    public ResponseEntity<List<FriendDTO>> getFriends(@PathVariable Long id) {
        List<FriendDTO> friends = friendService.getFriends(id);
        return ResponseEntity.ok(friends);
    }

    // Supprimer un ami pour un joueur
    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long id, @PathVariable Long friendId) {
        friendService.removeFriend(id, friendId);
        return ResponseEntity.ok("Friend removed successfully");
    }
}
