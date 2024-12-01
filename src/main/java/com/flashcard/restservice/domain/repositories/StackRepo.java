package com.flashcard.restservice.domain.repositories;

import com.flashcard.restservice.domain.entities.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StackRepo extends JpaRepository<Stack, Integer> {
}
