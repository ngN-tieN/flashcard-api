package com.flashcard.restservice.mapper.Studysession;
import com.flashcard.restservice.domain.entities.Studysession;
import com.flashcard.restservice.dto.requests.Studysession.StudysessionCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestToStudysessionMapper {
    @Mapping(target = "id", ignore = true)

    @Mapping(target = "stack.id", source = "stackId")  // Map stackId to stack object
    @Mapping(target = "user.id", source = "userId")  // Map userId to user object
    Studysession toStudysession(StudysessionCreateRequest request);
}
