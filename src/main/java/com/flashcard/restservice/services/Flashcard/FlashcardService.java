package com.flashcard.restservice.services.Flashcard;


import com.flashcard.restservice.domain.entities.Flashcard;
import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.domain.repositories.FlashcardRepo;
import com.flashcard.restservice.domain.repositories.StackRepo;
import com.flashcard.restservice.dto.requests.Flashcard.FlashcardCreateRequest;
import com.flashcard.restservice.dto.requests.Flashcard.FlashcardUpdateRequest;
import com.flashcard.restservice.dto.responses.Flashcard.FlashcardResponseDTO;
import com.flashcard.restservice.mapper.Flashcard.FlashcardToResponseMapper;
import com.flashcard.restservice.mapper.Flashcard.RequestToFlashcardMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlashcardService implements IFlashcardService {

    private final FlashcardRepo flashcardRepository;
    private final StackRepo stackRepository;
    private final FlashcardToResponseMapper flashcardToResponseMapper;
    private final RequestToFlashcardMapper requestToFlashcardMapper;
    @Autowired
    public FlashcardService(FlashcardRepo flashcardRepository, StackRepo stackRepository, FlashcardToResponseMapper flashcardToResponseMapper, RequestToFlashcardMapper requestToFlashcardMapper) {
        this.flashcardRepository = flashcardRepository;
        this.stackRepository = stackRepository;
        this.flashcardToResponseMapper = flashcardToResponseMapper;
        this.requestToFlashcardMapper = requestToFlashcardMapper;
    }

    // Create a new Flashcard
    @Override
    public boolean createFlashcard(FlashcardCreateRequest flashcardCreateRequest) {
        Flashcard flashcard = requestToFlashcardMapper.toFlashcard(flashcardCreateRequest);
        Stack stack = stackRepository.findById(flashcardCreateRequest.stackId())
                .orElseThrow(() -> new EntityNotFoundException("Stack not found"));
        flashcard.setStack(stack);
        flashcardRepository.save(flashcard);
        return true;
    }

    // Get all Flashcards with pagination
    private Page<FlashcardResponseDTO> getAllFlashcards(Pageable pageable) {
        Page<Flashcard> flashcardPage = flashcardRepository.findAll(pageable);
        return flashcardPage.map(flashcardToResponseMapper::toDto);
    }

    public Page<FlashcardResponseDTO> getAllFlashcardsByStackId(Integer stackId, Pageable pageable) {
        Page<Flashcard> flashcardPage = flashcardRepository.findByStackId(stackId, pageable);
        return flashcardPage.map(flashcardToResponseMapper::toDto);
    }
    // Get a Flashcard by ID
    @Override
    public FlashcardResponseDTO getFlashcardById(Integer id) {
        var flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
        return flashcardToResponseMapper.toDto(flashcard);
    }

    // Update a Flashcard
    public boolean updateFlashcard(Integer id, FlashcardUpdateRequest flashcardUpdateRequest) {
        var flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
        Stack stack = stackRepository.findById(flashcardUpdateRequest.stackId())
                .orElseThrow(() -> new EntityNotFoundException("Stack not found"));
        flashcard.setStack(stack);
        flashcard.setQuestion(flashcardUpdateRequest.question());
        flashcard.setAnswer(flashcardUpdateRequest.answer());
        flashcardRepository.save(flashcard);
        return true;
    }

    // Delete a Flashcard
    public boolean deleteFlashcard(Integer id) {
        var flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
        flashcardRepository.delete(flashcard);
        return true;
    }
}
