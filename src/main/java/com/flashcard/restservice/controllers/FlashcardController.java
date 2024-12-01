package com.flashcard.restservice.controllers;

import com.flashcard.restservice.dto.requests.Flashcard.FlashcardCreateRequest;
import com.flashcard.restservice.dto.requests.Flashcard.FlashcardUpdateRequest;
import com.flashcard.restservice.dto.responses.ApiResponse;
import com.flashcard.restservice.dto.responses.Flashcard.FlashcardResponseDTO;
import com.flashcard.restservice.services.Flashcard.IFlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    private final IFlashcardService flashcardService;

    @Autowired
    public FlashcardController(IFlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    // Create a new Flashcard
    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> createFlashcard(@RequestBody FlashcardCreateRequest flashcardRequestDto) {
        boolean res = flashcardService.createFlashcard(flashcardRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Flashcard created successfully", res));
    }

    // Get a Flashcard by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FlashcardResponseDTO>> getFlashcardById(@PathVariable("id") Integer id) {
        FlashcardResponseDTO res = flashcardService.getFlashcardById(id);
        return ResponseEntity.ok(ApiResponse.success("Flashcard retrieved successfully", res));
    }

    // Update a Flashcard by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> updateFlashcard(@PathVariable("id") Integer id,
                                                                @RequestBody FlashcardUpdateRequest flashcardUpdateRequest) {
        boolean res = flashcardService.updateFlashcard(id, flashcardUpdateRequest);
        return ResponseEntity.ok(ApiResponse.success("Flashcard updated successfully", res));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Page<FlashcardResponseDTO>>> getAllFlashcardsByStackId(
            @RequestParam("stackId") Integer stackId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Convert to 0-based page index
        Page<FlashcardResponseDTO> res = flashcardService.getAllFlashcardsByStackId(stackId, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Flashcards retrieved successfully", res));
    }
    // Delete a Flashcard by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteFlashcard(@PathVariable("id") Integer id) {
        boolean res = flashcardService.deleteFlashcard(id);
        return ResponseEntity.ok(ApiResponse.success("Flashcard deleted successfully", res));
    }
}
