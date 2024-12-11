package com.helb.player_management.exception;

public class FriendNotFoundException extends RuntimeException {
    public FriendNotFoundException(Long friendId) {
        super("Friend with ID " + friendId + " not found.");
    }
}