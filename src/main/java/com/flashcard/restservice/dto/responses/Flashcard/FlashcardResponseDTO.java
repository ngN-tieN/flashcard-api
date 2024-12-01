package com.flashcard.restservice.dto.responses.Flashcard;


public record FlashcardResponseDTO(Integer id, String question, String answer, Integer stackId) {
}
