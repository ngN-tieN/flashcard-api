package com.flashcard.restservice.services.User;

import com.flashcard.restservice.domain.entities.User;
import com.flashcard.restservice.dto.requests.User.RegisterRequest;

public interface IUserService {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void createUser(RegisterRequest registerDto);
}
