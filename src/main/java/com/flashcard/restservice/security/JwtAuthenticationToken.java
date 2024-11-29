package com.flashcard.restservice.security;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String username;


    public JwtAuthenticationToken(String username,  Collection<?> authorities) {
        super(null);
        this.username = username;
        setAuthenticated(true); // Mark as authenticated
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials needed after JWT validation
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

}
