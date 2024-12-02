package com.flashcard.restservice.dto.responses.Studysession;

import java.time.Instant;

public record StudysessionResponseDTO (Integer id,
                                      Integer stackId,
                                      Integer userId,
                                      Instant startedAt,
                                      Instant endedAt,
                                      Double score){
}
