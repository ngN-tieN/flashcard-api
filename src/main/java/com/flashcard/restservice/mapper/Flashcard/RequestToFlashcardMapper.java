package com.flashcard.restservice.mapper.Flashcard;

import com.flashcard.restservice.domain.entities.Flashcard;
import com.flashcard.restservice.dto.requests.Flashcard.FlashcardCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring", imports = Instant.class )
public interface RequestToFlashcardMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")

    @Mapping(target = "stack", ignore = true)
    Flashcard toFlashcard(FlashcardCreateRequest flashcardCreateRequest);
}
