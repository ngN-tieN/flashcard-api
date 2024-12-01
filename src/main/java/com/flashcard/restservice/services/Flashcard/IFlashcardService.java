package com.flashcard.restservice.services.Flashcard;

import com.flashcard.restservice.dto.requests.Flashcard.FlashcardCreateRequest;
import com.flashcard.restservice.dto.requests.Flashcard.FlashcardUpdateRequest;
import com.flashcard.restservice.dto.responses.Flashcard.FlashcardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlashcardService {
    boolean createFlashcard(FlashcardCreateRequest flashcardCreateRequest);
    FlashcardResponseDTO getFlashcardById(Integer id);
    Page<FlashcardResponseDTO> getAllFlashcardsByStackId(Integer stackId, Pageable pageable);
    boolean updateFlashcard(Integer id, FlashcardUpdateRequest flashcardUpdateRequest);
    boolean deleteFlashcard(Integer id);
}
