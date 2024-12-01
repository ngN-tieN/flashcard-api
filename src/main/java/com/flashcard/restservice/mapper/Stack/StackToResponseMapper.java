package com.flashcard.restservice.mapper.Stack;

import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.dto.responses.Stack.StackResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StackToResponseMapper {
    @Mapping(target = "ownerId", source = "owner.id")  // Map owner entity to ownerId
    StackResponseDTO toDto(Stack stack);
}
