package com.flashcard.restservice.services;

import com.flashcard.restservice.domain.entities.User;

public interface IUserService {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void save(User user);
}
