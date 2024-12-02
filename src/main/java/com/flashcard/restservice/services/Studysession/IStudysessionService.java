package com.flashcard.restservice.services.Studysession;

import com.flashcard.restservice.dto.requests.Studysession.StudysessionCreateRequest;
import com.flashcard.restservice.dto.responses.Studysession.StudysessionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudysessionService {
    boolean createStudysession(StudysessionCreateRequest request);
    Page<StudysessionResponseDTO> getAllStudysessionsByStackId(Integer stackId, Pageable pageable);
    StudysessionResponseDTO getStudysessionById(Integer id);
    boolean deleteStudysession(Integer id);
}
