package com.flashcard.restservice.controllers;

import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.domain.repositories.StackRepo;
import com.flashcard.restservice.dto.requests.Stack.StackCreateRequest;
import com.flashcard.restservice.dto.requests.Stack.StackUpdateRequest;
import com.flashcard.restservice.dto.responses.ApiResponse;
import com.flashcard.restservice.dto.responses.Stack.StackResponseDTO;
import com.flashcard.restservice.mapper.Stack.StackToResponseMapper;
import com.flashcard.restservice.services.Stack.IStackService;
import com.flashcard.restservice.services.Stack.StackService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stack")
public class StackController {

    private final IStackService stackService;
    @Autowired
    public StackController(IStackService stackService, StackToResponseMapper stackToResponseMapper) {
        this.stackService = stackService;
    }

    // Create a new Stack
    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> createStack(@RequestBody StackCreateRequest stackRequestDto) {
        Boolean res = stackService.createStack(stackRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Stack created successfully", res));
    }

    // Get all Stacks with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<Page<StackResponseDTO>>> getAllStacks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Convert to 0-based page index
        var res = stackService.findAllStacksCurrentOwner(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Stacks retrieved successfully", res));
    }

    // Get a Stack by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StackResponseDTO>> getStackById(@PathVariable("id") Integer id) {
        var res = stackService.getStackOfCurrentUserById(id);

        return ResponseEntity.ok(ApiResponse.success("Stack retrieved successfully", res));
    }

    // Update a Stack by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> updateStack(
            @PathVariable("id") Integer id, @RequestBody StackUpdateRequest stackRequestDto) {
        return ResponseEntity.ok(ApiResponse.success("Stack updated successfully",
                stackService.updateStack(id, stackRequestDto)));
    }

    // Delete a Stack by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteStack(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Stack deleted successfully", stackService.deleteStack(id)));
    }
}
