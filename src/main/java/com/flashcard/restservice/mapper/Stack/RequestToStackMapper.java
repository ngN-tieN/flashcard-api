package com.flashcard.restservice.mapper.Stack;


import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.dto.requests.Stack.StackCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Instant.class, ChronoUnit.class})
public interface RequestToStackMapper {

    // Ignore the ID as it might be auto-generated
    @Mapping(target = "id", ignore = true)

    // Map createdAt, setting it to the current time if not already provided
    @Mapping(target = "createdAt", expression = "java(Instant.now())")

    // Map other fields as needed from the request to the entity (this can be added if needed)
    Stack toStack(StackCreateRequest stackRequest);


}
