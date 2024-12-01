package com.flashcard.restservice.dto.requests.Flashcard;

public record FlashcardCreateRequest(String question, String answer, Integer stackId) {
}
