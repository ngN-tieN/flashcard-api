package com.flashcard.restservice.controllers;

import com.flashcard.restservice.dto.requests.Studysession.StudysessionCreateRequest;
import com.flashcard.restservice.dto.responses.ApiResponse;
import com.flashcard.restservice.dto.responses.Studysession.StudysessionResponseDTO;
import com.flashcard.restservice.services.Studysession.IStudysessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studysession")
public class StudySessionController {

    private final IStudysessionService studysessionService;

    @Autowired
    public StudySessionController(IStudysessionService studysessionService) {
        this.studysessionService = studysessionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> createStudysession(@RequestBody StudysessionCreateRequest request) {
        Boolean res = studysessionService.createStudysession(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Studysession created successfully", res));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudysessionResponseDTO>>> getAllStudysessionsByStackId(
            @RequestParam("stackId") Integer stackId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<StudysessionResponseDTO> res = studysessionService.getAllStudysessionsByStackId(stackId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Studysessions retrieved successfully", res));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudysessionResponseDTO>> getStudysessionById(@PathVariable("id") Integer id) {
        StudysessionResponseDTO res = studysessionService.getStudysessionById(id);
        return ResponseEntity.ok(ApiResponse.success("Studysession retrieved successfully", res));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteStudysession(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Studysession deleted successfully",
                studysessionService.deleteStudysession(id)));
    }
}
