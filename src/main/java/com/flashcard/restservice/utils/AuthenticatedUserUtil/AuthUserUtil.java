package com.flashcard.restservice.utils.AuthenticatedUserUtil;

import com.flashcard.restservice.domain.entities.User;
import com.flashcard.restservice.security.JwtAuthenticationToken;
import com.flashcard.restservice.services.User.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserUtil implements IAuthUserUtil {
    private final IUserService userService;

    @Autowired
    public AuthUserUtil(IUserService userService) {
        this.userService = userService;
    }
    @Override
    public String getCurrentUsername() {
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return (String) authentication.getPrincipal(); // Adjust for other claim names if necessary

    }
    @Override
    public User getCurrentUser(){
        return userService.findByUsername(getCurrentUsername());
    }
}
