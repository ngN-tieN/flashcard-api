package com.flashcard.restservice.dto.responses.Stack;

import java.time.Instant;

public record StackResponseDTO(Integer id, String name, String description, Integer ownerId, Instant createdAt) {
}
