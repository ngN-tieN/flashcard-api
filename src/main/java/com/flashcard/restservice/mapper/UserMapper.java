package com.flashcard.restservice.mapper;

import com.flashcard.restservice.domain.entities.User;
import com.flashcard.restservice.dto.requests.User.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring", imports = {Instant.class, ChronoUnit.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)  // Ignore ID because it might be auto-generated
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "accountExpiryDate", expression = "java(Instant.now().plus(730, ChronoUnit.DAYS))")
    @Mapping(target = "lockedStatus", constant = "false")
    @Mapping(target = "passwordExpiryDate", expression = "java(Instant.now().plus(183, ChronoUnit.DAYS))")
    @Mapping(target = "enabled", constant = "true")
    User toUser(RegisterRequest registerRequest);
}
