package com.flashcard.restservice.mapper.Studysession;

import com.flashcard.restservice.domain.entities.Studysession;
import com.flashcard.restservice.dto.responses.Studysession.StudysessionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudysessionToResponseMapper {
    @Mapping(target = "stackId", source = "stack.id")  // Map stack entity to stackId
    @Mapping(target = "userId", source = "user.id")  // Map user entity to userId
    StudysessionResponseDTO toDto(Studysession studysession);
}
