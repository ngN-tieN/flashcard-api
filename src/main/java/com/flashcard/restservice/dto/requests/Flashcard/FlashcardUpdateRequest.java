package com.flashcard.restservice.dto.requests.Flashcard;

public record FlashcardUpdateRequest(String question, String answer, Integer stackId) {
}
