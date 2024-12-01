package com.flashcard.restservice.mapper.Flashcard;

import com.flashcard.restservice.domain.entities.Flashcard;
import com.flashcard.restservice.dto.responses.Flashcard.FlashcardResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlashcardToResponseMapper {

    @Mapping(target = "stackId", source = "stack.id")
    FlashcardResponseDTO toDto(Flashcard flashcard);
}