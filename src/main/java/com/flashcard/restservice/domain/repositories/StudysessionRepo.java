package com.flashcard.restservice.domain.repositories;

import com.flashcard.restservice.domain.entities.Studysession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudysessionRepo extends JpaRepository<Studysession, Integer> {
    Page<Studysession> findByStackId(Integer stackId, Pageable pageable);
}
