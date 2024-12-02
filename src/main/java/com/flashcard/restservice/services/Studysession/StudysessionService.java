package com.flashcard.restservice.services.Studysession;


import com.flashcard.restservice.domain.entities.Studysession;
import com.flashcard.restservice.domain.repositories.StudysessionRepo;
import com.flashcard.restservice.dto.requests.Studysession.StudysessionCreateRequest;
import com.flashcard.restservice.dto.responses.Studysession.StudysessionResponseDTO;
import com.flashcard.restservice.mapper.Studysession.RequestToStudysessionMapper;
import com.flashcard.restservice.mapper.Studysession.StudysessionToResponseMapper;
import com.flashcard.restservice.utils.AuthenticatedUserUtil.IAuthUserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudysessionService implements IStudysessionService {

    private final StudysessionRepo studysessionRepo;
    private final RequestToStudysessionMapper createMapper;
    private final StudysessionToResponseMapper responseMapper;
    private final IAuthUserUtil authUserUtil;

    @Autowired
    public StudysessionService(StudysessionRepo studysessionRepo,
                               RequestToStudysessionMapper createMapper,
                               StudysessionToResponseMapper responseMapper,
                               IAuthUserUtil authUserUtil) {
        this.studysessionRepo = studysessionRepo;
        this.createMapper = createMapper;
        this.responseMapper = responseMapper;
        this.authUserUtil = authUserUtil;
    }

    @Override
    public boolean createStudysession(StudysessionCreateRequest request) {
        Studysession studysession = createMapper.toStudysession(request);
        studysessionRepo.save(studysession);
        return true;
    }

    @Override
    public Page<StudysessionResponseDTO> getAllStudysessionsByStackId(Integer stackId, Pageable pageable) {
        Page<Studysession> studysessionPage = studysessionRepo.findByStackId(stackId, pageable);
        return new PageImpl<>(studysessionPage.getContent().stream()
                .map(responseMapper::toDto)
                .toList(), pageable, studysessionPage.getTotalElements());
    }

    @Override
    public StudysessionResponseDTO getStudysessionById(Integer id) {
        Studysession studysession = studysessionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Studysession not found with id: " + id));
        return responseMapper.toDto(studysession);
    }



    @Override
    public boolean deleteStudysession(Integer id) {
        Studysession studysession = studysessionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Studysession not found with id: " + id));
        studysessionRepo.delete(studysession);
        return true;
    }
}

