package com.flashcard.restservice.services.Stack;

import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.dto.requests.Stack.StackCreateRequest;
import com.flashcard.restservice.dto.requests.Stack.StackUpdateRequest;
import com.flashcard.restservice.dto.responses.Stack.StackResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStackService {
    boolean createStack(StackCreateRequest stackCreateRequest);
    Page<StackResponseDTO>  findAllStacksCurrentOwner(Pageable pageable);
    StackResponseDTO getStackOfCurrentUserById(Integer id);
    boolean updateStack(Integer id, StackUpdateRequest stackRequestDto);
    boolean deleteStack(Integer id);
}
