package com.flashcard.restservice.services.Stack;

import com.flashcard.restservice.domain.entities.Stack;
import com.flashcard.restservice.domain.repositories.StackRepo;
import com.flashcard.restservice.dto.requests.Stack.StackCreateRequest;
import com.flashcard.restservice.dto.requests.Stack.StackUpdateRequest;
import com.flashcard.restservice.dto.responses.Stack.StackResponseDTO;
import com.flashcard.restservice.mapper.Stack.RequestToStackMapper;
import com.flashcard.restservice.mapper.Stack.StackToResponseMapper;
import com.flashcard.restservice.utils.AuthenticatedUserUtil.IAuthUserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StackService implements IStackService {

    private final StackRepo stackRepository;
    private final RequestToStackMapper requestToStackMapper;
    private final IAuthUserUtil authUserUtil;
    private final StackToResponseMapper stackToResponseMapper;
    @Autowired
    public StackService(StackRepo stackRepository, RequestToStackMapper requestToStackMapper, IAuthUserUtil authUserUtil, StackToResponseMapper stackToResponseMapper) {
        this.stackRepository = stackRepository;
        this.requestToStackMapper = requestToStackMapper;
        this.authUserUtil = authUserUtil;
        this.stackToResponseMapper = stackToResponseMapper;
    }

    @Override
    // Create a new Stack
    public boolean createStack (StackCreateRequest stackCreateRequest) {
        var user = authUserUtil.getCurrentUser();

        var stack = requestToStackMapper.toStack(stackCreateRequest);
        stack.setOwner(user);
        save(stack);
        return true;
    }
    private Stack save(Stack stack) {
        return stackRepository.save(stack);
    }

    // Get all Stacks with pagination
    public Page<Stack> findAllStacks(Pageable pageable) {
        return stackRepository.findAll(pageable);
    }
    public Page<StackResponseDTO> findAllStacksCurrentOwner(Pageable pageable) {
        Page<Stack> stackPage = stackRepository.findAll(pageable);
        List<StackResponseDTO> filteredStacks = stackPage.getContent().stream()
                .filter(stack -> stack.getOwner().getId().equals(authUserUtil.getCurrentUser().getId()))
                .map(stackToResponseMapper::toDto)
                .toList();
        return new PageImpl<>(filteredStacks, pageable, filteredStacks.size());
    }
    // Get a Stack by its ID
    public Stack findStackById(Integer id) {
        return stackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stack not found with id: " + id));

    }
    private Stack findStackOfCurrentUserById(Integer id) {
        Stack stack = stackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stack not found with id: " + id));
        if(!stack.getOwner().getId().equals(authUserUtil.getCurrentUser().getId())) throw new EntityNotFoundException("Stack not found with id: " + id);
        return stack;
    }
    @Override
    public StackResponseDTO getStackOfCurrentUserById(Integer id) {
        return stackToResponseMapper.toDto(findStackById(id));
    }
    // Update a Stack
    @Override
    public boolean updateStack(Integer id, StackUpdateRequest stackRequestDto) {
        Stack existingStack = findStackOfCurrentUserById(id);
        existingStack.setDescription(stackRequestDto.description());
        existingStack.setName(stackRequestDto.name());
        save(existingStack);
        return true;
    }

    // Delete a Stack
    @Override
    public boolean deleteStack(Integer id) {
        Stack stack = findStackOfCurrentUserById(id);
        stackRepository.delete(stack);
        return true;
    }
}
