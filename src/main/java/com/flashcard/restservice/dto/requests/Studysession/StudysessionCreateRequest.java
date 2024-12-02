package com.flashcard.restservice.dto.requests.Studysession;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record StudysessionCreateRequest(
        @NotNull Integer stackId,
        @NotNull Integer userId,
        Instant startedAt,
        Instant endedAt,
        Double score
) { }