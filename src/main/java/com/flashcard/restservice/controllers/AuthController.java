package com.flashcard.restservice.controllers;

import com.flashcard.restservice.domain.entities.User;
import com.flashcard.restservice.dto.requests.User.LoginRequest;
import com.flashcard.restservice.dto.requests.User.RegisterRequest;
import com.flashcard.restservice.dto.responses.ApiResponse;
import com.flashcard.restservice.mapper.UserMapper;
import com.flashcard.restservice.services.User.IUserService;
import com.flashcard.restservice.utils.IJwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final IJwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserService userService, UserMapper userMapper, IJwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user in the system.")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody RegisterRequest registerDto) {
        if (userService.existsByUsername(registerDto.username())) {
            var response = ApiResponse.error("Username already exists" );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        userService.createUser(registerDto);
        var response = ApiResponse.success("User registered successfully", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Authenticates a user and returns a JWT token.")
    public ResponseEntity<ApiResponse<?>> loginUser(@RequestBody LoginRequest loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password())
            );
            String jwt = jwtUtil.generateJwtToken(loginDto.username());
            var response = ApiResponse.success(jwt);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            var response = ApiResponse.error("Invalid credentials" );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
