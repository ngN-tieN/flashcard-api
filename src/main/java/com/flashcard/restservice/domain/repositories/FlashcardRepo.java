package com.flashcard.restservice.domain.repositories;

import com.flashcard.restservice.domain.entities.Flashcard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepo  extends JpaRepository<Flashcard, Integer> {
    Page<Flashcard> findByStackId(Integer stackId, Pageable pageable);

}
