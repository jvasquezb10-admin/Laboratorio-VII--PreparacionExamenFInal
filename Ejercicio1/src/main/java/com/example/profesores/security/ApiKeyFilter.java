package com.example.profesores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-Key";
    private final byte[] configuredApiKey;

    public ApiKeyFilter(@Value("${app.security.api-key}") String apiKey) {
        this.configuredApiKey = apiKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String providedApiKey = request.getHeader(API_KEY_HEADER);
        boolean validApiKey = providedApiKey != null
                && MessageDigest.isEqual(
                        configuredApiKey,
                        providedApiKey.getBytes(StandardCharsets.UTF_8)
                );

        if (!validApiKey) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"mensaje\":\"API Key ausente o inválida\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}