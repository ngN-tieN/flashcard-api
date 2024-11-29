package com.flashcard.restservice.utils;

public interface IJwtUtil {
    String generateJwtToken(String username);
    String extractUsername(String token);
}
