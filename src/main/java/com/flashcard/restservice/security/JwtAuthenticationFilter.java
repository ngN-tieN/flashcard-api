package com.flashcard.restservice.security;

import com.flashcard.restservice.utils.IJwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtUtil jwtUtil;

    public JwtAuthenticationFilter(IJwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String path = request.getRequestURI();
//        return path.startsWith("/auth/");  // Skip filtering for /auth endpoints
//    }
    @Override
    public void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (isBearerToken(authHeader)) {
            String jwt = extractJwtFromHeader(authHeader);
            String username = jwtUtil.extractUsername(jwt);

            if (username != null && isAuthenticationNotSet()) {
                authenticateUser(request, username);
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isBearerToken(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private String extractJwtFromHeader(String authHeader) {
        return authHeader.substring(Integer.parseInt(System.getProperty("JWT_TOKEN_PREFIX")));
    }

    private boolean isAuthenticationNotSet() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void authenticateUser(HttpServletRequest request, String username) {
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(username, null);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }



}

